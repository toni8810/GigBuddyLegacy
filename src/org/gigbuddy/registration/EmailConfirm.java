//REDEPLOY AND TEST THIS CLASS

package org.gigbuddy.registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gigbuddy.messaging.MailSenderBean;
import org.gigbuddy.tshirt.promotion.EmailTemplateCreator;
import org.gigbuddy.tshirt.promotion.TempTshirtPromotion;

public class EmailConfirm extends HttpServlet implements TempTshirtPromotion {
	private static final long serialVersionUID = 7971791610144744048L;
	int userId;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		userId = Integer.parseInt(req.getParameter("userId"));
		RequestDispatcher rd = req.getRequestDispatcher("messagePage.jsp");
		String username = "";
		
		try(Connection c = DriverManager.getConnection(getServletContext().getInitParameter("databaseURL"),getServletContext().getInitParameter("databaseUsername"),getServletContext().getInitParameter("databasePassword"));
			Statement s = c.createStatement();
			Statement sQuery = c.createStatement();
			ResultSet rs = sQuery.executeQuery("SELECT username FROM users WHERE userId = "+userId)) {
			
			rs.first();
			username = rs.getString("username");
			s.executeUpdate("UPDATE regdetails SET isconfirmed = 1 WHERE username = '"+username+"'");
			req.setAttribute("message", "Congratulations!! You have sucessfully validated your email address!!");
			req.setAttribute("title", "Email Validated!!");
			rd.forward(req, res);
			
		} catch (SQLException e) {
			req.setAttribute("message", "Whoops!! Something happened \n"+e.getMessage());
			req.setAttribute("title", "Error");
			rd.forward(req, res);
			return;
		}
		checkUserId(username);
	}
	@Override
	public void checkUserId(String emailAddress) {
		MailSenderBean mailSender = new MailSenderBean();
		int id = userId; 
		if (id % 7 == 0) {
			try(Connection c = DriverManager.getConnection(getServletContext().getInitParameter("databaseURL"),getServletContext().getInitParameter("databaseUsername"),getServletContext().getInitParameter("databasePassword"));
				Statement s = c.createStatement()) {
				s.executeUpdate("INSERT INTO tshirt (id) VALUES ("+id+")");
			}
			catch(SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		else {
			try {
				mailSender.sendEmail(emailAddress, "GigBuddy.org, Free T-Shirt Promotion", EmailTemplateCreator.createTemplate("Dear "+emailAddress+",<br><br>We regret to inform you that your are the "+id+"th person to sign up and therefore you are not entitled to the free GigBuddy T-Shirt.<br><br>", "/home/gigbud5/public_html/ROOT/htmlFiles/email/promotion.html", id, false));
			} catch (MessagingException e) { 
				// TODO Auto-generated catch block
				e.printStackTrace();   
			}
		}
	}

}
