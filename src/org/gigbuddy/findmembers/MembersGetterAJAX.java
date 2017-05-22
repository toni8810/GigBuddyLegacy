package org.gigbuddy.findmembers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MembersGetterAJAX extends HttpServlet {
	private static final long serialVersionUID = -1344435436551915312L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		String name = req.getParameter("nameInput");
		try (Connection c = DriverManager.getConnection(getServletContext().getInitParameter("databaseURL"),getServletContext().getInitParameter("databaseUsername"),getServletContext().getInitParameter("databasePassword"));
			 Statement s = c.createStatement();
			 PrintWriter pw = res.getWriter();
			 ResultSet rs = s.executeQuery("SELECT userId,name,age,location,about,mainImageURL FROM users WHERE name LIKE '%"+name+"%'")) {

			if (rs.first() == false) {
				pw.write("Whoops! The search came up with no results!");
				return;
			 }
			pw.println("<tr>");
			pw.println("<td>Profile picture</td>");
			pw.println("<td>Name</td>");
			pw.println("<td>Location</td>");
			pw.println("<td>Age</td>");
			pw.println("<td>About</td>");
			pw.println("</tr>");
			rs.beforeFirst();
			while (rs.next()) {
				pw.println("<tr id='"+rs.getInt("userId")+"'>");
				if (!rs.getString("mainImageURL").startsWith("/home")) pw.println("<td><img onclick='setAllImagesGlobal(this)' class='profilePic' src='"+rs.getString("mainImageURL")+"' alt='"+rs.getString("mainImageURL")+"' /></td>");
				else pw.println("<td><img onclick='setAllImagesGlobal(this)' class='profilePic' src='/getImage.do?fileName="+rs.getString("mainImageURL")+"' alt='"+rs.getString("mainImageURL")+"' /></td>");
				pw.println("<td>"+rs.getString("name")+"</td>");
				pw.println("<td>"+rs.getString("location")+"</td>");
				pw.println("<td>"+rs.getInt("age")+"</td>");
				pw.println("<td>"+rs.getString("about")+"</td>");
				pw.println("</tr>");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
