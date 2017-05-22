package org.gigbuddy.events;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BuddyRequestsGetterAJAX extends HttpServlet {
	private static final long serialVersionUID = 4701258772511097452L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		String title = req.getParameter("titleInput");
		String date = req.getParameter("dateInput");
		String location = req.getParameter("locationInput");
		String time = req.getParameter("timeInput");
		java.sql.Date sqlDate = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy");
		Date dSelectedDat;
		try {
			dSelectedDat = sdf.parse(date);
			sqlDate = new java.sql.Date(dSelectedDat.getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try (Connection c = DriverManager.getConnection(getServletContext().getInitParameter("databaseURL"),getServletContext().getInitParameter("databaseUsername"),getServletContext().getInitParameter("databasePassword"));
			 Statement s = c.createStatement();
			 ResultSet rs = s.executeQuery("SELECT * FROM buddyrequests WHERE eventTitle = '"+title+"' AND eventDate = '"+sqlDate+"' AND eventTime = '"+time+"' AND eventLocation = '"+location+"'")) {
			
			Statement tempStatement = c.createStatement();
			ResultSet tempSet = null;
			
			PrintWriter pw = res.getWriter();
			pw.write("<tr>");
			pw.write("<td>Profile Picture</td>");
			pw.write("<td>Name</td>");
			pw.write("<td>Number of people looking</td>");
			pw.write("<td>Age</td>");
			pw.write("<td>Ad</td>");
			pw.write("<td>Number of people they are looking for</td>");
			pw.write("<td>Apply</td>");
			pw.write("</tr>");
			while (rs.next()) {
				pw.write("<tr id='"+rs.getInt("reqId")+"'>");
				tempSet = tempStatement.executeQuery("SELECT mainImageURL FROM users WHERE username = '"+rs.getString("username")+"'");
				tempSet.first();
				if (tempSet.getString("mainImageURL").contains("/home")) pw.write("<td><img class='profilePic' src='/getImage.do?fileName="+tempSet.getString("mainImageURL")+"'/></td>");
				else pw.write("<td><img class='profilePic' src='"+tempSet.getString("mainImageURL")+"'/></td>");
				tempSet = tempStatement.executeQuery("SELECT name,age FROM users WHERE username = '"+rs.getString("username")+"'");
				tempSet.first();
				pw.write("<td>"+tempSet.getString("name")+"</td>");
				if (rs.getString("inOrGroup").contentEquals("Individual")) pw.write("<td>1</td>");
				else pw.write("<td>"+rs.getString("numOfPeople")+"</td>");
				pw.write("<td>"+tempSet.getString("age")+"</td>");
				pw.write("<td>"+rs.getString("ad")+"</td>");
				if (rs.getString("inOrGroupLookingFor").contentEquals("Individual")) pw.write("<td>1</td>");
				else pw.write("<td>"+rs.getString("numOfPeopleLookingFor")+"</td>");
				pw.write("<td class='apply' onclick='showContactBox(this)'>Apply</td>");
				pw.write("</tr>");
			}
			tempStatement.close();
			tempSet.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
