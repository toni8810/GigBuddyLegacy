package org.gigbuddy.events;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.ejb.EJB;
import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.gigbuddy.messaging.MailSenderBean;

public class MessageSenderServlet extends HttpServlet {
	private static final long serialVersionUID = 2152238086038937769L;
	
	@EJB
	private MailSenderBean mailSender;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String inOrGroup = req.getParameter("inOrGroup");
		String numOfPeople;
		if (inOrGroup.contentEquals("Group")) numOfPeople = req.getParameter("numOfPeople");
		else numOfPeople = "1";
		String message = req.getParameter("message");
		int reqId = Integer.parseInt(req.getParameter("reqId"));
		String username = req.getUserPrincipal().getName();
		String name;
		String receiverUsername;
		java.sql.Date sentDate = new java.sql.Date(new java.util.Date().getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy kk:mm");
		System.out.println(sdf.format(sentDate));
		
		try (Connection c = DriverManager.getConnection(getServletContext().getInitParameter("databaseURL"),getServletContext().getInitParameter("databaseUsername"),getServletContext().getInitParameter("databasePassword"));
			 Statement s = c.createStatement()) {
			
			Statement sQuery = c.createStatement();
			ResultSet rs = sQuery.executeQuery("SELECT username FROM buddyrequests WHERE reqId = "+reqId);
			rs.first();
			receiverUsername = rs.getString("username");
			rs = sQuery.executeQuery("SELECT name FROM users WHERE username = '"+username+"'");
			rs.first();
			name = rs.getString("name");
			
			s.executeUpdate("INSERT INTO messaging (reqId,username,message,numOfPeople,fromName,fromUsername,sentDate) "+
					        "VALUES ("+reqId+",'"+receiverUsername+"','"+message+"','"+numOfPeople+"','"+name+"','"+username+"',NOW())");
			sQuery.close();
			rs.close();
			mailSender = new MailSenderBean();
			
				try {
					mailSender.sendEmail(receiverUsername, "Message from "+name, message);
				} catch (MessagingException e) {
					req.setAttribute("message", "Whoops!! Something happened!\nError message: "+e.getMessage());
					RequestDispatcher rd = req.getRequestDispatcher("messagePage.jsp");
					rd.forward(req, res);
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			HttpSession session = req.getSession();
			session.setAttribute("message", "You have successfully sent the message");
			res.sendRedirect("messagePage.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
