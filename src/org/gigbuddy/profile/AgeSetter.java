package org.gigbuddy.profile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AgeSetter extends HttpServlet {
	private static final long serialVersionUID = 4706115705197436013L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		int age = Integer.parseInt(req.getParameter("ageInput"));
		
		try (Connection c = DriverManager.getConnection(getServletContext().getInitParameter("databaseURL"),getServletContext().getInitParameter("databaseUsername"),getServletContext().getInitParameter("databasePassword"));
			 Statement s = c.createStatement()) {
			
			s.executeUpdate("UPDATE users SET age = "+age+" WHERE username = '"+req.getUserPrincipal().getName()+"'");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
