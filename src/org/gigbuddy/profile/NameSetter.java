package org.gigbuddy.profile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NameSetter extends HttpServlet {
	private static final long serialVersionUID = -7011212306317400198L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		String name = req.getParameter("name");
		
		try (Connection c = DriverManager.getConnection(getServletContext().getInitParameter("databaseURL"),getServletContext().getInitParameter("databaseUsername"),getServletContext().getInitParameter("databasePassword"));
			 Statement s = c.createStatement()) {
			
			s.executeUpdate("UPDATE users SET name = '"+name+"' WHERE username = '"+req.getUserPrincipal().getName()+"'");
			RequestDispatcher rd = req.getRequestDispatcher("profile.jsp");
			rd.forward(req, res);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
