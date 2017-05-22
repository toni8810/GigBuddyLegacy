package org.gigbuddy.profile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SetMainImageAJAX extends HttpServlet {
	private static final long serialVersionUID = -4121688666495803918L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		//Getting parameters
		String imageURL = req.getParameter("imageURL");
		imageURL = imageURL.substring(imageURL.indexOf("=")+1);
		
		//setting session scope attribute
		session.setAttribute("mainImage", "/getImage.do?fileName="+imageURL);
		
		try (Connection c = DriverManager.getConnection(getServletContext().getInitParameter("databaseURL"),getServletContext().getInitParameter("databaseUsername"),getServletContext().getInitParameter("databasePassword"));
			 Statement s = c.createStatement()) {
			//writing main image into database
			s.executeUpdate("UPDATE users SET mainImageURL = '"+imageURL+"' WHERE username = '"+req.getUserPrincipal().getName()+"'");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
