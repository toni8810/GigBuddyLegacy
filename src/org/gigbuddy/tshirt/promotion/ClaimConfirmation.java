package org.gigbuddy.tshirt.promotion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gigbuddy.messaging.MailSenderBean;

public class ClaimConfirmation extends HttpServlet {
       
	private static final long serialVersionUID = -6904092802996973349L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = "";
		try (Connection c = DriverManager.getConnection(getServletContext().getInitParameter("databaseURL"),getServletContext().getInitParameter("databaseUsername"),getServletContext().getInitParameter("databasePassword"));
			 Statement s = c.createStatement();
			 Statement sQuery = c.createStatement()) {
			
			s.executeUpdate("UPDATE tshirt SET claimed = "+true+", name = '"+request.getParameter("name")+"', address = '"+request.getParameter("address")+"', gender = '"+request.getParameter("gender")+"', tshirt_size = '"+request.getParameter("size")+"' WHERE id = "+request.getParameter("userId"));
			ResultSet rs = s.executeQuery("SELECT username FROM users WHERE userId = "+request.getParameter("userId"));
			rs.first();
			email = rs.getString("username");
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("message", "We have received your claim for the free t-shirt. You should receive it within 5 working days");
		MailSenderBean msb = new MailSenderBean();
		try {
			msb.sendEmail(email, "t-shirt confirmation", "We have received your claim for the free t-shirt. You should receive it within 5 working days. Thanks again for signing up");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("messagePage.jsp");
		rd.forward(request, response);
	}

}
