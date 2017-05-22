package org.gigbuddy.messaging;

import java.util.Properties;

import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Session Bean implementation class MailSenderBean
 */
@Stateless
public class MailSenderBean {

	public void sendEmail(String toEmail, String subject, String message) throws AddressException, MessagingException {
		Properties props = System.getProperties();
		props.put("mail.smtp.host", "mail.gigbuddy.org");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.socketFactory.fallback", "true");
		
		Session mailSession = Session.getDefaultInstance(props,null);
		mailSession.setDebug(true);
		
		Message mailMessage = new MimeMessage(mailSession);
		mailMessage.setFrom(new InternetAddress("support@gigbuddy.org"));
		mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
		mailMessage.setContent(message, "text/html");
		mailMessage.setSubject(subject);
			
		Transport transport = mailSession.getTransport("smtp");
		transport.connect("mail.gigbuddy.org","support@gigbuddy.org","mLiXG5KEoLfP");
		transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
	}

}
