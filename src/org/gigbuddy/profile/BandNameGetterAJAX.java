package org.gigbuddy.profile;

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

public class BandNameGetterAJAX extends HttpServlet {
	private static final long serialVersionUID = -1633805686251121024L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/plain");
		String queryPar = req.getParameter("query");
		
		
		try (Connection c = DriverManager.getConnection(getServletContext().getInitParameter("databaseURL"),getServletContext().getInitParameter("databaseUsername"),getServletContext().getInitParameter("databasePassword"));
			 Statement s = c.createStatement();
			 PrintWriter pw = res.getWriter()) {
			
			ResultSet rs = s.executeQuery("SELECT name FROM bandnames WHERE name LIKE '%"+queryPar+"%' LIMIT 10");
			if (rs.first()) {
				pw.write("<ul>");
			}
			rs.beforeFirst();
			while (rs.next()) {
				pw.write("<li><a onClick='writeIntoBandSearchBox(this)'>"+rs.getString("name")+"</a></li>");
			}
			if (rs.first()) {
				pw.write("</ul>");
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
