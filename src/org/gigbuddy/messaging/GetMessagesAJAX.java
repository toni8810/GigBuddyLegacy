package org.gigbuddy.messaging;

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

public class GetMessagesAJAX extends HttpServlet {
	private static final long serialVersionUID = -198532677608291791L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		String username = req.getUserPrincipal().getName();
		String usernameSender = req.getParameter("usernameInput");
		
		try (Connection c = DriverManager.getConnection(getServletContext().getInitParameter("databaseURL"),getServletContext().getInitParameter("databaseUsername"),getServletContext().getInitParameter("databasePassword"));
			 Statement s = c.createStatement();
		     ResultSet rs = s.executeQuery("SELECT reqId,message,numOfPeople,sentDate,fromUsername FROM messaging WHERE (username = '"+username+"' AND fromUsername = '"+usernameSender+"') OR (username = '"+usernameSender+"' AND fromUsername = '"+username+"') ORDER BY sentDate DESC")) {
			
			rs.first();
			Statement s2 = c.createStatement();
			ResultSet rs2 = s2.executeQuery("SELECT eventTitle,eventTime,eventDate,eventLocation FROM buddyrequests WHERE reqId = "+rs.getInt("reqId"));
			rs2.first();
			PrintWriter pw = res.getWriter();
			pw.write("<h1 id='"+rs.getString("reqId")+"'>"+rs2.getString("eventTitle")+" "+rs2.getString("eventDate")+" "+rs2.getString("eventTime")+" "+rs2.getString("eventLocation"));
			if (rs.getString("numOfPeople").contentEquals("1")) pw.write(" One person is applying</h1>");
			else pw.write(" "+rs.getString("numOfPeople")+" people applying</h1>"); 
			rs.beforeFirst();
			pw.write("<div id='ballHolder'>");
			pw.write("<div class='ball'></div>");
			pw.write("<div class='ball1'></div>");
			pw.write("</div>");
			pw.write("<textarea rows='3' cols='37' class='textBox' onkeyup='sendMessage(this)'></textarea>");
			while(rs.next()) {
				rs2 = s2.executeQuery("SELECT name FROM users WHERE username = '"+rs.getString("fromUsername")+"'");
				rs2.first();
				pw.write("<div class='nn-alert'>");
				pw.write("<strong>"+rs2.getString("name")+"</strong>");
				pw.write("<strong>"+rs.getDate("sentDate")+"</strong>");
				pw.write("<p>"+rs.getString("message")+"</p>");
				pw.write("</div>");
			}
			rs2.close();
			s2.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
