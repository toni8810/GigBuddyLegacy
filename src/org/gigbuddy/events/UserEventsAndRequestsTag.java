package org.gigbuddy.events;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class UserEventsAndRequestsTag extends SimpleTagSupport {
	private String username;
	public void setUsername(String username) {
		this.username = username;
	}
	public void doTag() {
		boolean isFilled;
		try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/gigbud5_realm","gigbud5_toni","DCFGTwHzqOZS");
			 Statement s = c.createStatement()) {
			
			ResultSet rs = s.executeQuery("SELECT reqId,numOfPeople,inOrGroup,numOfPeopleLookingFor,inOrGroupLookingFor,ad,eventTitle,eventDate,eventTime,eventLocation FROM buddyrequests WHERE username='"+username+"'");
			
			JspWriter out = getJspContext().getOut();
			
			isFilled = rs.first();
			
			if (isFilled) {
				out.write("<h1>Your buddy requests: </h1>");
				out.write("<div class='table31'>");
				out.write("<table>");
				out.write("<tbody>");
				out.write("<tr>");
				out.write("<td>Title</td>");
				out.write("<td>Location</td>");
				out.write("<td>Date</td>");
				out.write("<td>Time</td>");
				out.write("<td>You are a(n)</td>");
				out.write("<td>Lookig for a(n)</td>");
				out.write("<td>About your request</td>");
				out.write("<td>Delete</td>");
				out.write("<td class='hidden'>Hidden Id</td>");
				out.write("</tr>");
				
				rs.beforeFirst();
				while (rs.next()) {
					out.write("<tr>");
					out.write("<td>"+rs.getString("eventTitle")+"</td>");
					out.write("<td>"+rs.getString("eventLocation")+"</td>");
					out.write("<td>"+rs.getDate("eventDate")+"</td>");
					out.write("<td>"+rs.getString("eventTime")+"</td>");
					if (rs.getString("inOrGroup").contentEquals("Individual")) out.write("<td>Individual</td>");
					else out.write("<td>Group("+rs.getString("numOfPeople")+")</td>");
					if (rs.getString("inOrGroupLookingFor").contentEquals("Individual")) out.write("<td>Individual</td>");
					else out.write("<td>Group("+rs.getString("numOfPeopleLookingFor")+")</td>");
					out.write("<td>"+rs.getString("ad")+"</td>");
					out.write("<td><a onclick='deleteBuddyRequest(this)' class='button cross'></a></td>");
					out.write("<td class='hidden'>"+rs.getString("reqId")+"</td>");
					out.write("</tr>");
				}
				out.write("</tbody>");
				out.write("</table>");
				out.write("</div>");
			}
			else {
				out.write("<h1>You have not posted any buddy request</h1>");
			}
			rs = s.executeQuery("SELECT title,location,time,date FROM eventsbyusers WHERE username = '"+username+"'");
			isFilled = rs.first();
			rs.beforeFirst();
			if (isFilled) {
				out.write("<h1>Events you posted: </h1>");
				out.write("<div class='table31'>");
				out.write("<table>");
				out.write("<tbody>");
				out.write("<tr>");
				out.write("<td>Title</td>");
				out.write("<td>Location</td>");
				out.write("<td>Date</td>");
				out.write("<td>Time</td>");
				out.write("<td>Delete</td>");
				out.write("</tr>");
				
				while (rs.next()) {
					out.write("<tr>");
					out.write("<td>"+rs.getString("title")+"</td>");
					out.write("<td>"+rs.getString("location")+"</td>");
					out.write("<td>"+rs.getDate("date")+"</td>");
					out.write("<td>"+rs.getString("time")+"</td>");
					out.write("<td><a onclick='deleteEvent(this)' class='button cross'></a></td>");
					out.write("</tr>");
				}
				out.write("</tbody>");
				out.write("</table>");
				out.write("</div>");
			}
			else {
				out.write("<h1>You have not added any event</h1>");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
