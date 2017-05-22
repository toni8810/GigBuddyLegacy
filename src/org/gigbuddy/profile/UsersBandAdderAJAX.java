package org.gigbuddy.profile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UsersBandAdderAJAX extends HttpServlet {

	private static final long serialVersionUID = 7186536450737004486L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		String bandName = req.getParameter("band");
		
		try (Connection c = DriverManager.getConnection(getServletContext().getInitParameter("databaseURL"),getServletContext().getInitParameter("databaseUsername"),getServletContext().getInitParameter("databasePassword"));
			 Statement s = c.createStatement()) {
			
			s.executeUpdate("INSERT INTO bands VALUES('"+req.getUserPrincipal().getName()+"','"+bandName+"')");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
