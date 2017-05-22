package org.gigbuddy.tshirt.promotion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserEligibilityValidator extends HttpServlet {
	private static final long serialVersionUID = 7687482106934250193L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		RequestDispatcher rd;
		
		try (Connection c = DriverManager.getConnection(getServletContext().getInitParameter("databaseURL"),getServletContext().getInitParameter("databaseUsername"),getServletContext().getInitParameter("databasePassword"));
			 Statement s = c.createStatement()) {
			
			//checking if user still has an account
			ResultSet rs = s.executeQuery("SELECT userId FROM users WHERE userId = "+id);
			if (rs.first() == false) {
				rd = request.getRequestDispatcher("messagePage.jsp");
				//take user to message page saying: Whoops You do not seem to have an account anymore. Please create another one
				request.setAttribute("message", "Whoops You do not seem to have an account anymore. Please create another one!");
				rd.forward(request, response);
			}
			rs.close();
			//Checking if user has already claimed their t-shirt
			rs = s.executeQuery("SELECT claimed FROM tshirt WHERE id = "+id);
			rs.first();
			if (rs.getBoolean("claimed") == true) {
				rd = request.getRequestDispatcher("messagePage.jsp");
				//take user to the message page saying according our records you have already claimed your t-shirt
				request.setAttribute("message", "According to our records you have already claimed your t-shirt");
				rd.forward(request, response);
			}
			rs.close();
			//T-shirt has not been claimed yet forward user to claimTshirt.jsp page
			request.setAttribute("userId", id);
			rd = request.getRequestDispatcher("claimTshirt.jsp");
			rd.forward(request, response);
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
	}

}
