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
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServlet;

import org.gigbuddy.events.Event;

public class DatabaseCleanerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	public void init() {
		File databaseCleanerLog = new File("/home/gigbud5/public_html/databaseCleanerLog.txt");
		long repeat = (1000*60)*60*26;
		Timer timer = new Timer();
		TimerTask tt = new TimerTask() {

			@Override
			public void run() {
				String log = "";
				ArrayList<Integer> intIds = new ArrayList<>();
				java.sql.Date date;
				java.sql.Date todaysDate = new java.sql.Date(new java.util.Date().getTime());
				Event tempEvent;
				ArrayList<Event> eventsIds = new ArrayList<>();
				
				try (Connection c = DriverManager.getConnection(getServletContext().getInitParameter("databaseURL"),getServletContext().getInitParameter("databaseUsername"),getServletContext().getInitParameter("databasePassword"));
					 Statement s = c.createStatement();
					 Statement sQuery = c.createStatement()) {
					
					//Querying buddyrequests and save ids that are to be deleted
					ResultSet rs = sQuery.executeQuery("SELECT reqId,eventDate FROM buddyrequests");
					while(rs.next()) {
						date = rs.getDate("eventDate");
						//If the event was in the past delete it
						if (date.before(todaysDate)) {
							intIds.add(rs.getInt("reqId"));
						}
					}
					for (int i=0; i<intIds.size(); i++) {
						s.executeUpdate("DELETE FROM buddyrequests WHERE reqId = "+intIds.get(i));
						s.executeUpdate("DELETE FROM messaging WHERE reqId = "+intIds.get(i));
					}
					rs = sQuery.executeQuery("SELECT title,location,time,date FROM eventsbyusers");
					while (rs.next()) {
						date = rs.getDate("date");
						if (date.before(todaysDate)) {
							tempEvent = new Event();
							tempEvent.setDate(date);
							tempEvent.setLocation(rs.getString("location"));
							tempEvent.setTime(rs.getString("time"));
							tempEvent.setTitle(rs.getString("title"));
							eventsIds.add(tempEvent);
						}
					}
					for (int i=0; i<eventsIds.size(); i++) {
						s.executeUpdate("DELETE FROM eventsbyusers WHERE title = '"+eventsIds.get(i).getTitle()+"' AND location = '"+eventsIds.get(i).getLocation()+"' AND time = '"+eventsIds.get(i).getTime()+"' AND date = '"+eventsIds.get(i).getDate()+"'");
					}
					rs.close();
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					Date currentDate = new Date();
					log = "Database successfully cleaned on" +dateFormat.format(currentDate);
				}
				catch(SQLException sqle) {
					sqle.printStackTrace();
					log = sqle.getMessage();
				} 
				try {
					databaseCleanerLog.createNewFile();
					BufferedWriter bw = new BufferedWriter(new FileWriter(databaseCleanerLog));
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
