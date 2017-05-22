package org.gigbuddy.events;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EventListCreator extends HttpServlet {
	private static final long serialVersionUID = 7971625764218433875L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/plain");
		String typed = req.getParameter("typed");
		String result;
		Set<String> resultSet = new HashSet<>();
		try (Connection c = DriverManager.getConnection(getServletContext().getInitParameter("databaseURL"),getServletContext().getInitParameter("databaseUsername"),getServletContext().getInitParameter("databasePassword"));
			 Statement s = c.createStatement();
			 PrintWriter pw = res.getWriter();
			 ResultSet rs = s.executeQuery("SELECT title FROM events WHERE title LIKE '%"+typed+"%'")) {
			
			
			while (rs.next()) {
				//Maximum 34 characters
				result = rs.getString("title");
				if (result.length() > 34) result = result.substring(0, 34);
				resultSet.add(result);
			}
			Iterator<String> i = resultSet.iterator();
			while(i.hasNext()) {
				pw.write("<li><a onclick='writeEventIntoEventBox(this)'>"+i.next()+"</a></li>");
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
