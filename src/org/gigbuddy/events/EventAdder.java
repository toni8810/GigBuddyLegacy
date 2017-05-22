package org.gigbuddy.events;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EventAdder extends HttpServlet {
	private static final long serialVersionUID = 4193841515596062211L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		String title = req.getParameter("title");
		String location = req.getParameter("location");
		String time = req.getParameter("two");
		String date = req.getParameter("date");
		String username = req.getUserPrincipal().getName();
		
		try (Connection c = DriverManager.getConnection(getServletContext().getInitParameter("databaseURL"),getServletContext().getInitParameter("databaseUsername"),getServletContext().getInitParameter("databasePassword"));
			 Statement s = c.createStatement()) {
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date dSelectedDat = sdf.parse(date);
			java.sql.Date sqlDate = new java.sql.Date(dSelectedDat.getTime());
			
			s.executeUpdate("INSERT INTO eventsbyusers (username,title,location,time,date) VALUES ('"+username+"','"+title+"','"+location+"','"+time+"','"+sqlDate+"')");
			s.executeUpdate("INSERT INTO events (title,location,time,date) VALUES ('"+title+"','"+location+"','"+time+"','"+sqlDate+"')");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			req.setAttribute("message", "You have successfully submitted the event!!! Now you will be able find buddies for the event!");
			RequestDispatcher rd = req.getRequestDispatcher("messagePage.jsp");
			rd.forward(req, res);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
