package org.gigbuddy.profile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NameGetterAJAX extends HttpServlet {

	private static final long serialVersionUID = -829561453330258099L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String name = "";
		try (Connection c = DriverManager.getConnection(getServletContext().getInitParameter("databaseURL"),getServletContext().getInitParameter("databaseUsername"),getServletContext().getInitParameter("databasePassword"));
			 PreparedStatement ps = c.prepareStatement("SELECT name FROM users WHERE username = ?")) {
			
			ps.setString(1, req.getUserPrincipal().getName());
			ResultSet rs = ps.executeQuery();
			rs.first();
			name = rs.getString("name");
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		res.getWriter().write(name);
	}

}
