package org.gigbuddy.registration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gigbuddy.messaging.MailSenderBean;

public class WelcomeEmailSenderServlet extends HttpServlet {
	private static final long serialVersionUID = 5007678324680539808L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		String email = req.getParameter("email");
		String password = email;
		MailSenderBean msb = new MailSenderBean();
		try {
			msb.sendEmail(email, "GigBuddy.org! Welcome", getEmailTemplate(email, password));
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Email has been succesfully sent");
	}
	private String getEmailTemplate(String email, String password) {
		//Production: /home/gigbud5/public_html/ROOT/htmlFiles/email/email-to-registered-users.html
		File htmlFile = new File("/home/gigbud5/public_html/ROOT/htmlFiles/email/email-to-registered-users.html");
		String returnEmail = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(htmlFile));
			String inputLine;
			while ((inputLine = br.readLine()) != null) {
				if (inputLine.contains("&lt;user`s username here&gt;")) {
					inputLine = inputLine.replace("&lt;user`s username here&gt;", email+"<br>Your Password is: "+password);
				}
				returnEmail += inputLine+"\n";
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnEmail;
	}
}
