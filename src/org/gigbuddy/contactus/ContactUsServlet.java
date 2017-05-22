package org.gigbuddy.contactus;

import java.io.IOException;

import javax.ejb.EJB;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gigbuddy.messaging.MailSenderBean;

public class ContactUsServlet extends HttpServlet {
	private static final long serialVersionUID = -857530353606854811L;
	
	@EJB
	private MailSenderBean mailSender;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String message = req.getParameter("message");
		message += "\n\n from "+email;
		
		mailSender = new MailSenderBean();
		RequestDispatcher rd = req.getRequestDispatcher("messagePage.jsp");
		try {
			mailSender.sendEmail("tmsldck@gmail.com", name, message);
			req.setAttribute("message", "We have received your message and we aim to respond within 24 hours");
			rd.forward(req, res);
		} catch (AddressException e) {
			req.setAttribute("message", "Whoops!! Something happened!\nError message: "+e.getMessage());
			rd.forward(req, res);
		} catch (MessagingException e) {
			req.setAttribute("message", "Whoops!! Something happened!\nError message: "+e.getMessage());
			rd.forward(req, res);
		}
	}

}
