//REDOPLOY AND TEST THIS CLASS

package org.gigbuddy.tshirt.promotion;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServlet;

import org.gigbuddy.messaging.MailSenderBean;

public class EligibleUserPromotionSender extends HttpServlet {
	private static final long serialVersionUID = 3458401906982893143L;
	
	public void init() {
		long repeat = (1000*60)*60*24;
		Timer timer = new Timer();
		TimerTask tt = new TimerTask() {

			@Override
			public void run() {
				File eligibleLog = new File("/home/gigbud5/public_html/eligibleLog.txt");
				String log = "";
				HashMap<Integer,String> userDetails = new HashMap<>(); 
				try (Connection c = DriverManager.getConnection(getServletContext().getInitParameter("databaseURL"),getServletContext().getInitParameter("databaseUsername"),getServletContext().getInitParameter("databasePassword"));
					 Statement s = c.createStatement();
					 Statement sQuery = c.createStatement();
					 Statement sUpdate = c.createStatement();
					 //Getting the id of users who have not claimed their free t-shirt
					 ResultSet idSet = s.executeQuery("SELECT id FROM tshirt WHERE claimed = "+false)) {
					log += "database connection successful\n";
					while (idSet.next()) {
						log += "Processing user id "+idSet.getInt("id")+"\n";
						//getting username of users who have not claimed their free t-shirt
						ResultSet usernameSet = sQuery.executeQuery("SELECT username FROM users WHERE userId = "+idSet.getInt("id"));
						//checking if user exists
						log += "checking if user with user id "+idSet.getInt("id")+" exist\n";
						if (usernameSet.first()) {
							userDetails.put(idSet.getInt("id"),usernameSet.getString("username"));
							log += usernameSet.getString("username")+" exists\n";
						}
						else {
							//if the user does not exist remove them from tshirt table and from emailing list
							sUpdate.executeUpdate("DELETE FROM tshirt WHERE id = "+idSet.getInt("id"));
							log += usernameSet.getString("username")+" does not exist and removed from tshirt table\n";
						}
						usernameSet.close();
					}
					idSet.close();
					Iterator<Entry<Integer, String>> it = userDetails.entrySet().iterator();
					while (it.hasNext()) {
						Map.Entry<Integer, String> pair = it.next();
						ResultSet isConfirmedSet = s.executeQuery("SELECT isconfirmed,regdate FROM regdetails WHERE username = '"+pair.getValue()+"'");
						isConfirmedSet.first();
						//checking if user confirmed their email address
						if (isConfirmedSet.getBoolean("isconfirmed") == false) {
							//if not do not send them email
							log += pair.getValue()+" has not confirmed their email address\n";
						}
						//if the user has confirmed their email but registered over 7 days ago
						else {
							//getting the date on which the user registered
							java.sql.Date regDateSQL = isConfirmedSet.getDate("regdate");
							Calendar calRegDate = Calendar.getInstance();
							calRegDate.setTime(regDateSQL);
							//adding 7 days to the date user registered
							calRegDate.add(Calendar.DATE, 7);
							//converting calendar to java.util.Date
							java.util.Date tempDate = calRegDate.getTime();
							//converting java.util.Date to java.sql.Date with the date 7 days after registration date
							regDateSQL = new java.sql.Date(tempDate.getTime());
							//getting today's date in java.sql.Date format
							java.sql.Date todaysDate = new java.sql.Date(new java.util.Date().getTime());
							//if user has not claimed their t-shirt for over 7 days delete user from promotion and from tshirt table
							if (todaysDate.after(regDateSQL)) {
								sUpdate.executeUpdate("DELETE FROM tshirt WHERE id = "+pair.getKey());
								log += pair.getValue()+" has not claimed their tshirt for over 7 days\n";
							}
							else {
								MailSenderBean mailSender = new MailSenderBean();
								try {
									mailSender.sendEmail(pair.getValue(), "gigbuddy.org t-shirt promotion", EmailTemplateCreator.createTemplate("Dear "+pair.getValue()+", <br><br>We are delighted to inform you that you are the "+pair.getKey()+"th person to sign up and therefore you are entitled to a free GigBuddy T-Shirt! <br>To claim your t-shirt please click the button below!<br><h3>Please note if you fail to claim your t-shirt within 7 days you will no longer be able to claim it<h3><br><br>", "/home/gigbud5/public_html/ROOT/htmlFiles/email/promotion.html", pair.getKey(), true));
									log += "email successfully sent to "+pair.getValue();
								} catch (MessagingException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
						
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					eligibleLog.createNewFile();
					BufferedWriter bw = new BufferedWriter(new FileWriter(eligibleLog));
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

}
