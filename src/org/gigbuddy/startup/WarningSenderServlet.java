package org.gigbuddy.startup;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;

public class WarningSenderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void init() {
		File warningSenderLog = new File("/home/gigbud5/public_html/warningSenderLog.txt");
		long repeat = (1000*60)*60*27;
		Timer timer = new Timer();
		TimerTask tt = new TimerTask() {

			@Override
			public void run() {
				String log = "";
				java.sql.Date date;
				java.sql.Date todaysDate = new java.sql.Date(new java.util.Date().getTime());
				ArrayList<String> usersToDelete = new ArrayList<String>();
				String[][] usersToSendWarningTo;
				String userId;
				
				try (Connection c = DriverManager.getConnection(getServletContext().getInitParameter("databaseURL"),getServletContext().getInitParameter("databaseUsername"),getServletContext().getInitParameter("databasePassword"));
					 Statement s = c.createStatement();
					 Statement sQuery = c.createStatement()) {
					
					ArrayList<String> tempUsersToSendWarningTo = new ArrayList<String>();
					
					ResultSet rs = sQuery.executeQuery("SELECT username,regdate FROM regdetails WHERE isconfirmed = 0");
					while (rs.next()) {
						date = rs.getDate("regdate");
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						//Adding 7 days to the date
						cal.add(Calendar.DATE, 7);
						Date tempUtilDate = cal.getTime();
						date = new java.sql.Date(tempUtilDate.getTime());
						
						//Add user to the deleteUser list if user has not validated their email for 7 days
						if (todaysDate.after(date)) usersToDelete.add(rs.getString("username"));
						// otherwise add user to 'send warning' list 
						else tempUsersToSendWarningTo.add(rs.getString("username"));
					}
					usersToSendWarningTo = new String[tempUsersToSendWarningTo.size()][2];
					for (int i=0; i<usersToSendWarningTo.length; i++) {
						rs = sQuery.executeQuery("SELECT userId FROM users WHERE username = '"+tempUsersToSendWarningTo.get(i)+"'");
						rs.first();
						usersToSendWarningTo[i][0] = tempUsersToSendWarningTo.get(i);
						usersToSendWarningTo[i][1] = rs.getString("userId");
					}
					
					//Delete users that have not validated their email
					for (int i=0; i<usersToDelete.size(); i++) {
						s.executeUpdate("DELETE FROM buddyrequests WHERE username = '"+usersToDelete.get(i)+"'");
						s.executeUpdate("DELETE FROM eventsbyusers WHERE username = '"+usersToDelete.get(i)+"'");
						s.executeUpdate("DELETE FROM messaging WHERE username = '"+usersToDelete.get(i)+"' OR fromUsername = '"+usersToDelete.get(i)+"'");
						s.executeUpdate("DELETE FROM regdetails WHERE username = '"+usersToDelete.get(i)+"'");
						rs = sQuery.executeQuery("SELECT userId FROM users WHERE username = '"+usersToDelete.get(i)+"'");
						rs.first();
						userId = rs.getString("userId");
						//If the user had images uploaded delete user`s folder
						if (s.executeUpdate("DELETE FROM userimages WHERE username = '"+usersToDelete.get(i)+"'") > 0) {
							deleteDirectory(new File("/home/gigbud5/public_html/userimages/"+userId));
						}
						s.executeUpdate("DELETE FROM users WHERE username = '"+usersToDelete.get(i)+"'");
						s.executeUpdate("DELETE FROM users_roles WHERE username = '"+usersToDelete.get(i)+"'");
					}
					rs.close();
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					Date currentDate = new Date();
					sendMail(usersToSendWarningTo);
					log = "Warnings have been successfully sent out on" +dateFormat.format(currentDate);
					
				}
				catch(SQLException sqle) {
					sqle.printStackTrace();
					log = sqle.getMessage();
				} catch (AddressException e) {
					log = e.getMessage();
					e.printStackTrace();
				} catch (MessagingException e) {
					log = e.getMessage();
					e.printStackTrace();
				}
				
				try {
					warningSenderLog.createNewFile();
					BufferedWriter bw = new BufferedWriter(new FileWriter(warningSenderLog));
					bw.write(log);
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		};
		timer.schedule(tt, 0, repeat);
	}
	private boolean deleteDirectory(File directory) {
	    if(directory.exists()){
	        File[] files = directory.listFiles();
	        if(null!=files){
	            for(int i=0; i<files.length; i++) {
	                if(files[i].isDirectory()) {
	                    deleteDirectory(files[i]);
	                }
	                else {
	                    files[i].delete();
	                }
	            }
	        }
	    }
	    return(directory.delete());
	}
	private void sendMail(String[][] recipients) throws AddressException, MessagingException {
		Properties props = System.getProperties();
		props.put("mail.smtp.host", "mail.gigbuddy.org");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.socketFactory.fallback", "true");
		
		Session mailSession = Session.getDefaultInstance(props,null);
		mailSession.setDebug(true);
		
		Message mailMessage = new MimeMessage(mailSession);
		mailMessage.setFrom(new InternetAddress("support@gigbuddy.org"));
		mailMessage.setSubject("Verify your email!");
			
		Transport transport = mailSession.getTransport("smtp");
		transport.connect("mail.gigbuddy.org","support@gigbuddy.org","mLiXG5KEoLfP");
		for (int i=0; i<recipients.length; i++) {
			mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients[i][0]));
			mailMessage.setContent("Please verify your email address by clicking http://gigbuddy.org/validateEmail.do?userId="+recipients[i][1], "text/html");
			transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
		}
		
		
	}

}
