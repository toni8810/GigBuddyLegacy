package org.gigbuddy.events;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BuddyRequest extends HttpServlet {
	private static final long serialVersionUID = -3821650022719498255L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		String inOrGroup = req.getParameter("inOrGroup");
		String numOfPeople = req.getParameter("numOfPeople");
		String linOrGroup = req.getParameter("linOrGroup");
		String lNumOfPeople = req.getParameter("lNumOfPeople");
		String ad = req.getParameter("ad");
		String eventTitle = req.getParameter("eventTitle");
		String eventLocation = req.getParameter("eventLocation");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date eventDateTemp = null;
		try {
			eventDateTemp = sdf1.parse(req.getParameter("eventDate"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		java.sql.Date eventDate = new java.sql.Date(eventDateTemp.getTime());
		String eventTime = req.getParameter("eventTime");
		
		
		try (Connection c = DriverManager.getConnection(getServletContext().getInitParameter("databaseURL"),getServletContext().getInitParameter("databaseUsername"),getServletContext().getInitParameter("databasePassword"));
			 Statement s = c.createStatement()) {
			
			s.executeUpdate("INSERT INTO buddyrequests (username,numOfPeople,inOrGroup,numOfPeopleLookingFor,inOrGroupLookingFor,ad,eventTitle,eventDate,eventTime,eventLocation) "+
							 "VALUES ('"+req.getUserPrincipal().getName()+"','"+numOfPeople+"','"+inOrGroup+"','"+lNumOfPeople+"','"+linOrGroup+"','"+ad+"','"+eventTitle+"','"+eventDate+"','"+eventTime+"','"+eventLocation+"') ");
			
			req.setAttribute("message", "You have successfully posted a buddy request");
			
		} catch (SQLException e) {
			req.setAttribute("message", "Whoops something went wrong while posting buddy request");
			e.printStackTrace();
		}
		try {
			RequestDispatcher rd = req.getRequestDispatcher("messagePage.jsp");
			rd.forward(req, res);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
