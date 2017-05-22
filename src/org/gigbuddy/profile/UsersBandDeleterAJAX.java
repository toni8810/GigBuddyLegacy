package org.gigbuddy.profile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UsersBandDeleterAJAX extends HttpServlet {
	
	private static final long serialVersionUID = 3257249497674035110L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) {

		String bandName = req.getParameter("bandName");
		
		try (Connection c = DriverManager.getConnection(getServletContext().getInitParameter("databaseURL"),getServletContext().getInitParameter("databaseUsername"),getServletContext().getInitParameter("databasePassword"));
			 Statement s = c.createStatement()) {
				
			s.executeUpdate("DELETE FROM bands WHERE username = '"+req.getUserPrincipal().getName()+"' AND band = '"+bandName+"'");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
