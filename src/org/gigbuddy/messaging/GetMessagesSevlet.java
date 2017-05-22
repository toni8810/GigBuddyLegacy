package org.gigbuddy.messaging;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetMessagesSevlet extends HttpServlet {
	private static final long serialVersionUID = 4667832602828675404L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		String username = req.getUserPrincipal().getName();
		ArrayList<String> messageSendersName = new ArrayList<String>();
		HttpSession session = req.getSession();
		
		try (Connection c = DriverManager.getConnection(getServletContext().getInitParameter("databaseURL"),getServletContext().getInitParameter("databaseUsername"),getServletContext().getInitParameter("databasePassword"));
		     Statement s = c.createStatement();
			 ResultSet rs = s.executeQuery("SELECT username,fromName,fromUsername FROM messaging WHERE username = '"+username+"'")) {
			 if (rs.first() == false) {
				req.setAttribute("message", "You have no messages");
				RequestDispatcher rd = req.getRequestDispatcher("messagePage.jsp");
				rd.forward(req, res);
				return;
			}
			rs.beforeFirst();
			
			String sendersUsername = "";
			
			while (rs.next()) {
				if (sendersUsername.contentEquals(rs.getString("fromUsername"))) continue;
				else {
					sendersUsername = rs.getString("fromUsername");
					messageSendersName.add(rs.getString("fromName")+","+sendersUsername);
				}
			}
			//Changing messageRead to true which is 1
			Statement sUpdate = c.createStatement();
			sUpdate.executeUpdate("UPDATE messaging SET messageRead = 1 WHERE username = '"+username+"'");
			sUpdate.close();
			session.setAttribute("numOfUnreadMessages", 0);
			req.setAttribute("messageSendersName", messageSendersName);
			RequestDispatcher rd = req.getRequestDispatcher("messages.jsp");
			rd.forward(req, res);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
