package org.gigbuddy.events;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteEventAJAX extends HttpServlet {
	private static final long serialVersionUID = -9059291355342681219L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		String title = req.getParameter("titleInput");
		String location = req.getParameter("locationInput");
		String dateString = req.getParameter("dateInput");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date utilDate;
		java.sql.Date date = null;
		try {
			utilDate = sdf.parse(dateString);
			date = new java.sql.Date(utilDate.getTime()); 
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String time = req.getParameter("timeInput");
		System.out.println(title+" "+location+" "+date+" "+time);
		
		try (Connection c = DriverManager.getConnection(getServletContext().getInitParameter("databaseURL"),getServletContext().getInitParameter("databaseUsername"),getServletContext().getInitParameter("databasePassword"));
			 Statement s = c.createStatement()) {
				
			s.executeUpdate("DELETE FROM eventsbyusers WHERE title = '"+title+"' AND location = '"+location+"' AND date = '"+date+"' AND time = '"+time+"'");
			s.executeUpdate("DELETE FROM events WHERE title = '"+title+"' AND location = '"+location+"' AND date = '"+date+"' AND time = '"+time+"'");
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
