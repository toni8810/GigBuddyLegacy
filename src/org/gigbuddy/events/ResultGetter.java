package org.gigbuddy.events;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResultGetter extends HttpServlet {
	private static final long serialVersionUID = -1326697531495798970L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/plain");
		
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy");
		
		String location = req.getParameter("locationInput");
		String event = req.getParameter("eventInput");
		
		try (Connection c = DriverManager.getConnection(getServletContext().getInitParameter("databaseURL"),getServletContext().getInitParameter("databaseUsername"),getServletContext().getInitParameter("databasePassword"));
			 Statement s = c.createStatement();
			 Statement sbr = c.createStatement();
			 PrintWriter pw = res.getWriter();
			 ResultSet rs = s.executeQuery("SELECT * FROM events WHERE title LIKE '%"+event+"%' AND location LIKE '%"+location+"%' ORDER BY date");
			 ResultSet rsBuddyRequests = sbr.executeQuery("SELECT eventTitle,eventDate,eventLocation,eventTime FROM buddyrequests")) {
			 
			 int counter = 0;
			 int buddyRequestCounter = 0;
			 rsBuddyRequests.last();
			 String[][] buddyRequestsArray = new String[rsBuddyRequests.getRow()][4];
			 rsBuddyRequests.beforeFirst();
			 while (rsBuddyRequests.next()) {
				buddyRequestsArray[counter][0] = rsBuddyRequests.getString("eventTitle");
				buddyRequestsArray[counter][1] = rsBuddyRequests.getString("eventDate");
				buddyRequestsArray[counter][2] = rsBuddyRequests.getString("eventLocation");
				buddyRequestsArray[counter][3] = rsBuddyRequests.getString("eventTime");
				counter++;
			 }
			 if (rs.first() == false) {
				pw.write("<p class='white'>Whoops! The search came up with no results! If our database does not contain the event you would like to go, please add it by <a href='/members/addEvent.jsp'>clicking here</a></p>");
				Cookie messageCookie = new Cookie("messageCookie", "You have to login in order to add an event!!");
				messageCookie.setMaxAge(10);
				res.addCookie(messageCookie);
				return;
			 }
			 rs.beforeFirst();
			 pw.write("<tr>");
			 pw.write("<td>Title</td>");
			 pw.write("<td>Location</td>");
			 pw.write("<td>Date</td>");
			 pw.write("<td>Time</td>");
			 pw.write("<td>Number Of Buddy Requests</td>");
			 pw.write("<td>Post a request</td>");
			 pw.write("</tr>");
				
			while (rs.next()) {
				for (int i=0; i<buddyRequestsArray.length; i++) {
					if ((buddyRequestsArray[i][0].contentEquals(rs.getString("title")) && (buddyRequestsArray[i][1].contentEquals(rs.getString("date"))))) {
						if ((buddyRequestsArray[i][2].contentEquals(rs.getString("location")) && (buddyRequestsArray[i][0].contentEquals(rs.getString("title"))))) {
							buddyRequestCounter++;
						}
					}
				}
				pw.write("<tr>");
				pw.write("<td>"+rs.getString("title")+"</td>");
				pw.write("<td>"+rs.getString("location")+"</td>");
				pw.write("<td>"+sdf.format(rs.getDate("date"))+"</td>");
				pw.write("<td>"+rs.getString("time")+"</td>");
				pw.write("<td "+(buddyRequestCounter > 0 ? "class='moreThanOneReq' onclick='showRequests(this)'" : "") +">"+buddyRequestCounter+"</td>");
				pw.write("<td class=\"buddyRequest\"><form action=\"/members/takeToServletJSP.do\" onsubmit=\"return setMessageCookie('You have to login in order to post a buddy request!!')\"><input type=\"text\" name=\"title\" value=\""+rs.getString("title")+"\" /><input type=\"text\" name=\"location\" value=\""+rs.getString("location")+"\" /><input type=\"text\" name=\"date\" value=\""+rs.getString("date")+"\" /><input type=\"text\" name=\"time\" value=\""+rs.getString("time")+"\" /><input type=\"submit\" value=\"Post a buddy request for this event\" /></form></td>");
				pw.write("</tr>");
				buddyRequestCounter = 0;
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
