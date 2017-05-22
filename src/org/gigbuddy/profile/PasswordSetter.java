package org.gigbuddy.profile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PasswordSetter extends HttpServlet {
	private static final long serialVersionUID = -1326732968122685762L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		String password = req.getParameter("password");
		
		try(Connection c = DriverManager.getConnection(getServletContext().getInitParameter("databaseURL"),getServletContext().getInitParameter("databaseUsername"),getServletContext().getInitParameter("databasePassword"));
			Statement s = c.createStatement()) {
			
			s.executeUpdate("UPDATE users SET password = '"+password+"' WHERE username = '"+req.getUserPrincipal().getName()+"'");
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
