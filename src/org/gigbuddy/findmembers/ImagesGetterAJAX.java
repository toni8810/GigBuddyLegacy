package org.gigbuddy.findmembers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class ImagesGetterAJAX extends HttpServlet {
	private static final long serialVersionUID = 3688813650544619641L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("We are in");
		int id = Integer.parseInt(req.getParameter("idInput"));
		System.out.println(id);
		String username;
		ArrayList<String> imageURLs = new ArrayList<String>();
		try (Connection c = DriverManager.getConnection(getServletContext().getInitParameter("databaseURL"),getServletContext().getInitParameter("databaseUsername"),getServletContext().getInitParameter("databasePassword"));
			 Statement s = c.createStatement()) {
			
			ResultSet rs = s.executeQuery("SELECT username FROM users WHERE userId = "+id);
			rs.first();
			username = rs.getString("username");
			System.out.println(username);
			rs = s.executeQuery("SELECT imageURL FROM userimages WHERE username = '"+username+"'");
			while (rs.next()) {
				imageURLs.add(rs.getString("imageURL"));
			}
			rs.close();
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	    try {
	    	System.out.println(imageURLs.size());
	    	String json = new Gson().toJson(imageURLs);
			res.setContentType("application/json");
		    res.setCharacterEncoding("UTF-8");
			res.getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
