package org.gigbuddy.profile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ImageDeleterAJAX extends HttpServlet {
	private static final long serialVersionUID = 6944132289769320605L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		String url = req.getParameter("imageURL");
		String mainImageURL;
		
		try (Connection c = DriverManager.getConnection(getServletContext().getInitParameter("databaseURL"),getServletContext().getInitParameter("databaseUsername"),getServletContext().getInitParameter("databasePassword"));
			 Statement s = c.createStatement()) {
			
			//creating a statement for executeQuery
			Statement sQuery = c.createStatement();
			//getting the mainImageURL
			ResultSet rs = sQuery.executeQuery("SELECT mainImageURL FROM users WHERE username = '"+req.getUserPrincipal().getName()+"'");
			rs.first();
			mainImageURL = rs.getString("mainImageURL");
			rs.close();
			sQuery.close();
			//if the user deletes the main image change the main image to the default image
			if (mainImageURL.contentEquals(url)) {
				s.executeUpdate("UPDATE users SET mainImageURL = '/images/defaultProfileImage.jpg' WHERE username = '"+req.getUserPrincipal().getName()+"'");
				HttpSession session = req.getSession();
				session.setAttribute("mainImage", "/images/defaultProfileImage.jpg");
			}
			s.executeUpdate("DELETE FROM userimages WHERE username = '"+req.getUserPrincipal().getName()+"' AND imageURL = '"+url+"'");
			System.out.println("Image URL deleted from database");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Path fileToDelete = Paths.get(url.substring(url.indexOf("=")+1));
			Files.delete(fileToDelete);
			System.out.println("File has been deleted!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
