package org.gigbuddy.registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginDetailsGetter extends HttpServlet {
	private static final long serialVersionUID = 949886416125274397L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json");
		String username = req.getParameter("email");
		JsonObject userJson = null;
		
		try (Connection c = DriverManager.getConnection(getServletContext().getInitParameter("databaseURL"),getServletContext().getInitParameter("databaseUsername"),getServletContext().getInitParameter("databasePassword"));
			 PreparedStatement sQuery = c.prepareStatement("SELECT password FROM users WHERE username = ?")) {
			
			sQuery.setString(1, username);
			ResultSet rs = sQuery.executeQuery();
			if (rs.first()) userJson = Json.createObjectBuilder().add("password", rs.getString("password")).build();
			rs.close();
			res.getWriter().print(userJson);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
