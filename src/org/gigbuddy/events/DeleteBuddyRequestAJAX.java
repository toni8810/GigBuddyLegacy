package org.gigbuddy.events;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteBuddyRequestAJAX extends HttpServlet {
	private static final long serialVersionUID = -4261561497096541569L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		int id = Integer.parseInt(req.getParameter("idInput"));
		try (Connection c = DriverManager.getConnection(getServletContext().getInitParameter("databaseURL"),getServletContext().getInitParameter("databaseUsername"),getServletContext().getInitParameter("databasePassword"));
			 Statement s = c.createStatement()) {
			
			s.executeUpdate("DELETE FROM buddyrequests WHERE reqId = "+id);
			s.executeUpdate("DELETE FROM messaging WHERE reqId = "+id);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
