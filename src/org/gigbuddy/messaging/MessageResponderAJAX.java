
//Create waiting symbol and check out embedded images in email

package org.gigbuddy.messaging;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ejb.EJB;
import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MessageResponderAJAX extends HttpServlet {
	private static final long serialVersionUID = -7720615022539499046L;
	
	@EJB
	private MailSenderBean mailSender;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String receiverUsername = req.getParameter("receiverUsername");
		int reqId = Integer.parseInt(req.getParameter("reqIdInput"));
		String senderUsername = req.getUserPrincipal().getName();
		String senderName = "";
		String receiverName = "";
		
		try (Connection c = DriverManager.getConnection(getServletContext().getInitParameter("databaseURL"),getServletContext().getInitParameter("databaseUsername"),getServletContext().getInitParameter("databasePassword"));
			 Statement s = c.createStatement()) {
			
			Statement s2 = c.createStatement();
			ResultSet rs = s2.executeQuery("SELECT numOfPeople FROM buddyrequests WHERE reqId = "+reqId);
			rs.first();
			
			Statement s3 = c.createStatement();
			ResultSet rs2 = s3.executeQuery("SELECT name FROM users WHERE username = '"+senderUsername+"'");
			rs2.first();
			senderName = rs2.getString("name");
			rs2 = s3.executeQuery("SELECT name FROM users WHERE username = '"+receiverUsername+"'");
			rs2.first();
			receiverName = rs2.getString("name");
			s.executeUpdate("INSERT INTO messaging (reqId,username,message,numOfPeople,fromName,fromUsername,sentDate) " +
			"VALUES ("+reqId+",'"+receiverUsername+"','"+req.getParameter("messageInput")+"','"+rs.getString("numOfPeople")+"','"+senderName+"','"+senderUsername+"',NOW())");
			
			rs = s2.executeQuery("SELECT reqId,message,numOfPeople,sentDate,fromUsername FROM messaging WHERE (username = '"+senderUsername+"' AND fromUsername = '"+receiverUsername+"') OR (username = '"+receiverUsername+"' AND fromUsername = '"+senderUsername+"') ORDER BY sentDate DESC");
			rs.first();
			rs2 = s3.executeQuery("SELECT eventTitle,eventTime,eventDate,eventLocation FROM buddyrequests WHERE reqId = "+rs.getInt("reqId"));
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
				rs2 = s3.executeQuery("SELECT name FROM users WHERE username = '"+rs.getString("fromUsername")+"'");
				rs2.first();
				pw.write("<div class='nn-alert'>");
				pw.write("<strong>"+rs2.getString("name")+"</strong>");
				pw.write("<strong>"+rs.getDate("sentDate")+"</strong>");
				pw.write("<p>"+rs.getString("message")+"</p>");
				pw.write("</div>");
			}
			rs.close();
			rs2.close();
			s2.close();
			s3.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mailSender = new MailSenderBean();
		try {
			mailSender.sendEmail(receiverUsername, "New message from "+senderName.substring(0, senderName.indexOf(" ")), getMessage(req.getParameter("messageInput"),senderName,receiverName));
		} catch (MessagingException e1) {
			RequestDispatcher rd = req.getRequestDispatcher("messagePage.jsp");
			req.setAttribute("message", "Whoops!! Something happened!\nError message: "+e1.getMessage());
			rd.forward(req, res);
		}
	}

	private String getMessage(String userTypedMessage,String sendersName,String receiversName) {
		String returnMessage = "<!DOCTYPE html \"-//w3c//dtd xhtml 1.0 transitional //en\" \"http://www.w3.org/tr/xhtml1/dtd/xhtml1-transitional.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\"><head>" + "\n" +
				"    <!--[if gte mso 9]><xml>" + "\n" +
				"     <o:OfficeDocumentSettings>" + "\n" +
				"      <o:AllowPNG/>" + "\n" +
				"      <o:PixelsPerInch>96</o:PixelsPerInch>" + "\n" +
				"     </o:OfficeDocumentSettings>" + "\n" +
				"    </xml><![endif]-->" + "\n" +
				"    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">" + "\n" +
				"    <meta name=\"viewport\" content=\"width=device-width\">" + "\n" +
				"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=9; IE=8; IE=7; IE=EDGE\">" + "\n" +
				"    <title>Message Notification</title>" + "\n" +
				"    " + "\n" +
				"    " + "\n" +
				"    " + "\n" +
				"</head>" + "\n" +
				"<body style=\"width: 100% !important;min-width: 100%;-webkit-text-size-adjust: 100%;-ms-text-size-adjust: 100% !important;margin: 0;padding: 0;background-color: #FFFFFF\">" + "\n" +
				"  <style id=\"media-query\">" + "\n" +
				"    /* Client-specific Styles & Reset */" + "\n" +
				"    #outlook a {" + "\n" +
				"        padding: 0;" + "\n" +
				"    }" + "\n" +
				"" + "\n" +
				"    /* .ExternalClass applies to Outlook.com (the artist formerly known as Hotmail) */" + "\n" +
				"    .ExternalClass {" + "\n" +
				"        width: 100%;" + "\n" +
				"    }" + "\n" +
				"" + "\n" +
				"    .ExternalClass," + "\n" +
				"    .ExternalClass p," + "\n" +
				"    .ExternalClass span," + "\n" +
				"    .ExternalClass font," + "\n" +
				"    .ExternalClass td," + "\n" +
				"    .ExternalClass div {" + "\n" +
				"        line-height: 100%;" + "\n" +
				"    }" + "\n" +
				"" + "\n" +
				"    #backgroundTable {" + "\n" +
				"        margin: 0;" + "\n" +
				"        padding: 0;" + "\n" +
				"        width: 100% !important;" + "\n" +
				"        line-height: 100% !important;" + "\n" +
				"    }" + "\n" +
				"" + "\n" +
				"    /* Buttons */" + "\n" +
				"    .button a {" + "\n" +
				"        display: inline-block;" + "\n" +
				"        text-decoration: none;" + "\n" +
				"        -webkit-text-size-adjust: none;" + "\n" +
				"        text-align: center;" + "\n" +
				"    }" + "\n" +
				"" + "\n" +
				"    .button a div {" + "\n" +
				"        text-align: center !important;" + "\n" +
				"    }" + "\n" +
				"" + "\n" +
				"    /* Outlook First */" + "\n" +
				"    body.outlook p {" + "\n" +
				"        display: inline !important;" + "\n" +
				"    }" + "\n" +
				"" + "\n" +
				"    /*  Media Queries */" + "\n" +
				"@media only screen and (max-width: 500px) {" + "\n" +
				"  table[class=\"body\"] img {" + "\n" +
				"    height: auto !important;" + "\n" +
				"    width: 100% !important; }" + "\n" +
				"  table[class=\"body\"] img.fullwidth {" + "\n" +
				"    max-width: 100% !important; }" + "\n" +
				"  table[class=\"body\"] center {" + "\n" +
				"    min-width: 0 !important; }" + "\n" +
				"  table[class=\"body\"] .container {" + "\n" +
				"    width: 95% !important; }" + "\n" +
				"  table[class=\"body\"] .row {" + "\n" +
				"    width: 100% !important;" + "\n" +
				"    display: block !important; }" + "\n" +
				"  table[class=\"body\"] .wrapper {" + "\n" +
				"    display: block !important;" + "\n" +
				"    padding-right: 0 !important; }" + "\n" +
				"  table[class=\"body\"] .columns, table[class=\"body\"] .column {" + "\n" +
				"    table-layout: fixed !important;" + "\n" +
				"    float: none !important;" + "\n" +
				"    width: 100% !important;" + "\n" +
				"    padding-right: 0px !important;" + "\n" +
				"    padding-left: 0px !important;" + "\n" +
				"    display: block !important; }" + "\n" +
				"  table[class=\"body\"] .wrapper.first .columns, table[class=\"body\"] .wrapper.first .column {" + "\n" +
				"    display: table !important; }" + "\n" +
				"  table[class=\"body\"] table.columns td, table[class=\"body\"] table.column td, .col {" + "\n" +
				"    width: 100% !important; }" + "\n" +
				"  table[class=\"body\"] table.columns td.expander {" + "\n" +
				"    width: 1px !important; }" + "\n" +
				"  table[class=\"body\"] .right-text-pad, table[class=\"body\"] .text-pad-right {" + "\n" +
				"    padding-left: 10px !important; }" + "\n" +
				"  table[class=\"body\"] .left-text-pad, table[class=\"body\"] .text-pad-left {" + "\n" +
				"    padding-right: 10px !important; }" + "\n" +
				"  table[class=\"body\"] .hide-for-small, table[class=\"body\"] .show-for-desktop {" + "\n" +
				"    display: none !important; }" + "\n" +
				"  table[class=\"body\"] .show-for-small, table[class=\"body\"] .hide-for-desktop {" + "\n" +
				"    display: inherit !important; }" + "\n" +
				"  .mixed-two-up .col {" + "\n" +
				"    width: 100% !important; } }" + "\n" +
				" @media screen and (max-width: 500px) {" + "\n" +
				"      div[class=\"col\"] {" + "\n" +
				"          width: 100% !important;" + "\n" +
				"      }" + "\n" +
				"    }" + "\n" +
				"" + "\n" +
				"    @media screen and (min-width: 501px) {" + "\n" +
				"      table[class=\"container\"] {" + "\n" +
				"          width: 500px !important;" + "\n" +
				"      }" + "\n" +
				"    }" + "\n" +
				"  </style>" + "\n" +
				"  <table class=\"body\" style=\"border-spacing: 0;border-collapse: collapse;vertical-align: top;height: 100%;width: 100%;table-layout: fixed\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">" + "\n" +
				"      <tbody><tr style=\"vertical-align: top\">" + "\n" +
				"          <td class=\"center\" style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;text-align: center;background-color: #FFFFFF\" align=\"center\" valign=\"top\">" + "\n" +
				"" + "\n" +
				"              <table style=\"border-spacing: 0;border-collapse: collapse;vertical-align: top;background-color: #4D3866\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\" border=\"0\">" + "\n" +
				"                <tbody><tr style=\"vertical-align: top\">" + "\n" +
				"                  <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\" width=\"100%\">" + "\n" +
				"                    <!--[if gte mso 9]>" + "\n" +
				"                    <table id=\"outlookholder\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\"><tr><td>" + "\n" +
				"                    <![endif]-->" + "\n" +
				"                    <!--[if (IE)]>" + "\n" +
				"                    <table width=\"500\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">" + "\n" +
				"                        <tr>" + "\n" +
				"                            <td>" + "\n" +
				"                    <![endif]-->" + "\n" +
				"                    <table class=\"container\" style=\"border-spacing: 0;border-collapse: collapse;vertical-align: top;max-width: 500px;margin: 0 auto;text-align: inherit\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\" border=\"0\"><tbody><tr style=\"vertical-align: top\"><td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\" width=\"100%\"><table class=\"block-grid two-up\" style=\"border-spacing: 0;border-collapse: collapse;vertical-align: top;width: 100%;max-width: 500px;color: #333;background-color: #736D4C\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bgcolor=\"#736D4C\"><tbody><tr style=\"vertical-align: top\"><td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;text-align: center;font-size: 0\"><!--[if (gte mso 9)|(IE)]><table width=\"100%\" align=\"center\" bgcolor=\"#736D4C\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><![endif]--><!--[if (gte mso 9)|(IE)]><td valign=\"top\" width=\"250\"><![endif]--><div class=\"col num6\" style=\"display: inline-block;vertical-align: top;text-align: center;width: 250px\"><table style=\"border-spacing: 0;border-collapse: collapse;vertical-align: top\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\" border=\"0\"><tbody><tr style=\"vertical-align: top\"><td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;background-color: transparent;padding-top: 20px;padding-right: 0px;padding-bottom: 5px;padding-left: 0px;border-top: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-left: 0px solid transparent\"><table style=\"border-spacing: 0;border-collapse: collapse;vertical-align: top\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">" + "\n" +
				"    <tbody><tr style=\"vertical-align: top\">" + "\n" +
				"        <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 16px;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif\"><div class=\"our-class\">" + "\n" +
				"<a href=\"http://gigbuddy.org\">" + "\n" +
				"  <img src=\"http://gigbuddy.org/images/logo.png\" width=\"130\">" + "\n" +
				"  </a>" + "\n" +
				"</div></td>" + "\n" +
				"    </tr>" + "\n" +
				"</tbody></table></td></tr></tbody></table></div><!--[if (gte mso 9)|(IE)]></td><![endif]--><!--[if (gte mso 9)|(IE)]><td valign=\"top\" width=\"250\"><![endif]--><div class=\"col num6\" style=\"display: inline-block;vertical-align: top;text-align: center;width: 250px\"><table style=\"border-spacing: 0;border-collapse: collapse;vertical-align: top\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\" border=\"0\"><tbody><tr style=\"vertical-align: top\"><td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;background-color: transparent;padding-top: 20px;padding-right: 0px;padding-bottom: 20px;padding-left: 0px;border-top: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-left: 0px solid transparent\"><table style=\"border-spacing: 0;border-collapse: collapse;vertical-align: top\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">" + "\n" +
				"  <tbody><tr style=\"vertical-align: top\">" + "\n" +
				"    <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;padding-top: 20px;padding-right: 10px;padding-bottom: 20px;padding-left: 10px\">" + "\n" +
				"        <div style=\"color:#6E6F7A;line-height:150%;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;\">            " + "\n" +
				"        	<div style=\"font-size:12px;line-height:18px;color:#6E6F7A;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;text-align:left;\"><div style=\"line-height:18px; font-size:12px; text-align: center;\"><span style=\"font-size: 20px; line-height: 30px; color: rgb(255, 255, 153);\">&#65279;Find Your Gig Buddies</span></div></div>" + "\n" +
				"        </div>" + "\n" +
				"    </td>" + "\n" +
				"  </tr>" + "\n" +
				"</tbody></table>" + "\n" +
				"<table style=\"border-spacing: 0;border-collapse: collapse;vertical-align: top\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">" + "\n" +
				"  <tbody><tr style=\"vertical-align: top\">" + "\n" +
				"    <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;padding-top: 10px;padding-right: 10px;padding-bottom: 10px;padding-left: 10px\">" + "\n" +
				"        <div style=\"color:#555555;line-height:120%;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;\">            " + "\n" +
				"        	<div style=\"font-size:12px;line-height:14px;color:#555555;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;text-align:left;\"><p style=\"margin: 0;font-size: 14px;line-height: 17px\"><span style=\"color: rgb(255, 255, 255); font-size: 14px; line-height: 16px;\">WWW</span><span style=\"color: rgb(204, 153, 255); font-size: 20px; line-height: 24px;\">.</span><span style=\"color: rgb(255, 255, 153); font-size: 20px; line-height: 24px;\">GIGBUDDY</span><span style=\"color: rgb(204, 153, 255); font-size: 20px; line-height: 24px;\">.</span><span style=\"color: rgb(255, 255, 255); font-size: 14px; line-height: 16px;\">ORG</span></p></div>" + "\n" +
				"        </div>" + "\n" +
				"    </td>" + "\n" +
				"  </tr>" + "\n" +
				"</tbody></table>" + "\n" +
				"</td></tr></tbody></table></div><!--[if (gte mso 9)|(IE)]></td><![endif]--><!--[if (gte mso 9)|(IE)]></td></tr></table><![endif]--></td></tr></tbody></table></td></tr></tbody></table>" + "\n" +
				"                    <!--[if mso]>" + "\n" +
				"                    </td></tr></table>" + "\n" +
				"                    <![endif]-->" + "\n" +
				"                    <!--[if (IE)]>" + "\n" +
				"                    </td></tr></table>" + "\n" +
				"                    <![endif]-->" + "\n" +
				"                  </td>" + "\n" +
				"                </tr>" + "\n" +
				"              </tbody></table>" + "\n" +
				"              <table style=\"border-spacing: 0;border-collapse: collapse;vertical-align: top;background-color: #467B8C\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\" border=\"0\">" + "\n" +
				"                <tbody><tr style=\"vertical-align: top\">" + "\n" +
				"                  <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\" width=\"100%\">" + "\n" +
				"                    <!--[if gte mso 9]>" + "\n" +
				"                    <table id=\"outlookholder\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\"><tr><td>" + "\n" +
				"                    <![endif]-->" + "\n" +
				"                    <!--[if (IE)]>" + "\n" +
				"                    <table width=\"500\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">" + "\n" +
				"                        <tr>" + "\n" +
				"                            <td>" + "\n" +
				"                    <![endif]-->" + "\n" +
				"                    <table class=\"container\" style=\"border-spacing: 0;border-collapse: collapse;vertical-align: top;max-width: 500px;margin: 0 auto;text-align: inherit\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\" border=\"0\"><tbody><tr style=\"vertical-align: top\"><td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\" width=\"100%\"><table class=\"block-grid\" style=\"border-spacing: 0;border-collapse: collapse;vertical-align: top;width: 100%;max-width: 500px;color: #000000;background-color: transparent\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bgcolor=\"transparent\"><tbody><tr style=\"vertical-align: top\"><td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;text-align: center;font-size: 0\"><!--[if (gte mso 9)|(IE)]><table width=\"100%\" align=\"center\" bgcolor=\"transparent\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><![endif]--><!--[if (gte mso 9)|(IE)]><td valign=\"top\" width=\"500\"><![endif]--><div class=\"col num12\" style=\"display: inline-block;vertical-align: top;width: 100%\"><table style=\"border-spacing: 0;border-collapse: collapse;vertical-align: top\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\" border=\"0\"><tbody><tr style=\"vertical-align: top\"><td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;background-color: transparent;padding-top: 0px;padding-right: 0px;padding-bottom: 0px;padding-left: 0px;border-top: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-left: 0px solid transparent\"><table style=\"border-spacing: 0;border-collapse: collapse;vertical-align: top\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">" + "\n" +
				"  <tbody><tr style=\"vertical-align: top\">" + "\n" +
				"    <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;padding-top: 30px;padding-right: 0px;padding-bottom: 30px;padding-left: 0px\">" + "\n" +
				"        <div style=\"color:#ffffff;line-height:120%;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;\">            " + "\n" +
				"        	<div style=\"font-size:12px;line-height:14px;color:#ffffff;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;text-align:left;\"><p style=\"margin: 0;text-align: center;font-size: 12px;line-height: 14px\"><span style=\"font-size: 28px; line-height: 33px;\"><b><span style=\"font-size: 28px; line-height: 33px;\" id=\"_mce_caret\" data-mce-bogus=\"true\"><span style=\"color: rgb(0, 0, 0); font-size: 20px; line-height: 24px;\">&#65279;A new message from "+sendersName+"</span></span><br></b></span></p></div>" + "\n" +
				"        </div>" + "\n" +
				"    </td>" + "\n" +
				"  </tr>" + "\n" +
				"</tbody></table>" + "\n" +
				"<table style=\"border-spacing: 0;border-collapse: collapse;vertical-align: top\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">" + "\n" +
				"  <tbody><tr style=\"vertical-align: top\">" + "\n" +
				"    <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;padding-top: 10px;padding-right: 10px;padding-bottom: 10px;padding-left: 10px\">" + "\n" +
				"        <div style=\"color:#555555;line-height:120%;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;\">            " + "\n" +
				"        	<div style=\"font-size:12px;line-height:14px;color:#555555;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;text-align:left;\"><p style=\"margin: 0;text-align: left;font-size: 12px;line-height: 14px\"><span style=\"font-size: 12px; line-height: 14px;\"><strong><span style=\"color: rgb(0, 0, 0); font-size: 12px; line-height: 14px;\"><span style=\"font-size: 14px; line-height: 16px;\">Hey "+receiversName+" you have a new message form "+sendersName+"</span></span></strong></span></p><p style=\"margin: 0;text-align: left;font-size: 12px;line-height: 14px\">&nbsp;<br></p><p style=\"margin: 0;text-align: left;font-size: 12px;line-height: 14px\"><span style=\"font-size: 12px; line-height: 14px;\"><span style=\"font-size: 14px; line-height: 16px;\"></span><strong><span style=\"color: rgb(0, 0, 0); font-size: 12px; line-height: 14px;\"><span style=\"font-size: 14px; line-height: 16px;\">&nbsp;</span></span></strong><span style=\"color: rgb(0, 0, 0); font-size: 12px; line-height: 14px;\"><span style=\"font-size: 14px; line-height: 16px;\">Message:.</span></span></span><br></p><p style=\"margin: 0;font-size: 12px;line-height: 14px; color:white;text-align: center\">&nbsp; "+userTypedMessage+"<br></p><p style=\"margin: 0;font-size: 12px;line-height: 14px;text-align: left\">&nbsp;<br></p></div>" + "\n" +
				"        </div>" + "\n" +
				"    </td>" + "\n" +
				"  </tr>" + "\n" +
				"</tbody></table>" + "\n" +
				"<table style=\"border-spacing: 0;border-collapse: collapse;vertical-align: top\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" + "\n" +
				"  <tbody><tr style=\"vertical-align: top\">" + "\n" +
				"    <td class=\"button-container\" style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;padding-top: 10px;padding-right: 10px;padding-bottom: 10px;padding-left: 10px\" align=\"center\">" + "\n" +
				"      <table style=\"border-spacing: 0;border-collapse: collapse;vertical-align: top\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">" + "\n" +
				"        <tbody><tr style=\"vertical-align: top\">" + "\n" +
				"          <td class=\"button\" style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\" width=\"100%\" align=\"center\" valign=\"middle\">" + "\n" +
				"              <!--[if mso]>" + "\n" +
				"                <v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" href=\"http://gigbuddy.org/members/showMessages.do\"" + "\n" +
				"                  style=\"" + "\n" +
				"                    height:42px;" + "\n" +
				"                    v-text-anchor:middle;" + "\n" +
				"                    width:144px;\"" + "\n" +
				"                    arcsize=\"10%\"" + "\n" +
				"                    strokecolor=\"#736D4C\"" + "\n" +
				"                    fillcolor=\"#736D4C\" >" + "\n" +
				"                <w:anchorlock/>" + "\n" +
				"                  <center " + "\n" +
				"                    style=\"color:#ffffff;" + "\n" +
				"                      font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;" + "\n" +
				"                      font-size:16px;\">" + "\n" +
				"              <![endif]-->" + "\n" +
				"             <!--[if !mso]><!- - --><div style=\"display: inline-block;" + "\n" +
				"              border-radius: 4px; " + "\n" +
				"              -webkit-border-radius: 4px; " + "\n" +
				"              -moz-border-radius: 4px; " + "\n" +
				"              max-width: 100%;" + "\n" +
				"              width: auto;" + "\n" +
				"              border-top: 0px solid transparent;" + "\n" +
				"              border-right: 0px solid transparent;" + "\n" +
				"              border-bottom: 0px solid transparent;" + "\n" +
				"              border-left: 0px solid transparent;\" align=\"center\">" + "\n" +
				"" + "\n" +
				"              <table style=\"border-spacing: 0;border-collapse: collapse;vertical-align: top;height: 42\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" + "\n" +
				"                <tbody><tr style=\"vertical-align: top\"><td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;border-radius: 4px;                   -webkit-border-radius: 4px;                   -moz-border-radius: 4px;                  color: #ffffff;                  background-color: #736D4C;                  padding-top: 5px;                   padding-right: 20px;                  padding-bottom: 5px;                  padding-left: 20px;                  font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif;text-align: center\" valign=\"middle\"><!--<![endif]-->" + "\n" +
				"                  <a style=\"display: inline-block;text-decoration: none;-webkit-text-size-adjust: none;text-align: center;background-color: #736D4C;color: #ffffff\" href=\"http://gigbuddy.org/members/showMessages.do\" target=\"_blank\">" + "\n" +
				"                        <span style=\"font-size:16px;line-height:32px;\">Reply Now</span>" + "\n" +
				"                  </a>" + "\n" +
				"                <!--[if !mso]><!- - --></td></tr></tbody></table>" + "\n" +
				"              </div><!--<![endif]-->" + "\n" +
				"              <!--[if mso]>" + "\n" +
				"                    </center>" + "\n" +
				"                </v:roundrect>" + "\n" +
				"              <![endif]-->" + "\n" +
				"          </td>" + "\n" +
				"        </tr>" + "\n" +
				"      </tbody></table>" + "\n" +
				"    </td>" + "\n" +
				"  </tr>" + "\n" +
				"</tbody></table>" + "\n" +
				"</td></tr></tbody></table></div><!--[if (gte mso 9)|(IE)]></td><![endif]--><!--[if (gte mso 9)|(IE)]></td></tr></table><![endif]--></td></tr></tbody></table></td></tr></tbody></table>" + "\n" +
				"                    <!--[if mso]>" + "\n" +
				"                    </td></tr></table>" + "\n" +
				"                    <![endif]-->" + "\n" +
				"                    <!--[if (IE)]>" + "\n" +
				"                    </td></tr></table>" + "\n" +
				"                    <![endif]-->" + "\n" +
				"                  </td>" + "\n" +
				"                </tr>" + "\n" +
				"              </tbody></table>" + "\n" +
				"              <table style=\"border-spacing: 0;border-collapse: collapse;vertical-align: top;background-color: #ffffff\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\" border=\"0\">" + "\n" +
				"                <tbody><tr style=\"vertical-align: top\">" + "\n" +
				"                  <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\" width=\"100%\">" + "\n" +
				"                    <!--[if gte mso 9]>" + "\n" +
				"                    <table id=\"outlookholder\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\"><tr><td>" + "\n" +
				"                    <![endif]-->" + "\n" +
				"                    <!--[if (IE)]>" + "\n" +
				"                    <table width=\"500\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">" + "\n" +
				"                        <tr>" + "\n" +
				"                            <td>" + "\n" +
				"                    <![endif]-->" + "\n" +
				"                    <table class=\"container\" style=\"border-spacing: 0;border-collapse: collapse;vertical-align: top;max-width: 500px;margin: 0 auto;text-align: inherit\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\" border=\"0\"><tbody><tr style=\"vertical-align: top\"><td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\" width=\"100%\"><table class=\"block-grid\" style=\"border-spacing: 0;border-collapse: collapse;vertical-align: top;width: 100%;max-width: 500px;color: #333;background-color: transparent\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bgcolor=\"transparent\"><tbody><tr style=\"vertical-align: top\"><td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;text-align: center;font-size: 0\"><!--[if (gte mso 9)|(IE)]><table width=\"100%\" align=\"center\" bgcolor=\"transparent\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><![endif]--><!--[if (gte mso 9)|(IE)]><td valign=\"top\" width=\"500\"><![endif]--><div class=\"col num12\" style=\"display: inline-block;vertical-align: top;width: 100%\"><table style=\"border-spacing: 0;border-collapse: collapse;vertical-align: top\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\" border=\"0\"><tbody><tr style=\"vertical-align: top\"><td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;background-color: transparent;padding-top: 30px;padding-right: 0px;padding-bottom: 30px;padding-left: 0px;border-top: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-left: 0px solid transparent\">" + "\n" +
				"<table style=\"border-spacing: 0;border-collapse: collapse;vertical-align: top\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" + "\n" +
				"  <tbody><tr style=\"vertical-align: top\">" + "\n" +
				"    <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\" align=\"center\" valign=\"top\">" + "\n" +
				"      <table style=\"border-spacing: 0;border-collapse: collapse;vertical-align: top\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" + "\n" +
				"        <tbody><tr style=\"vertical-align: top\">" + "\n" +
				"          <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;text-align: center;padding-top: 10px;padding-right: 10px;padding-bottom: 10px;padding-left: 10px;max-width: 156px\" align=\"center\" valign=\"top\">" + "\n" +
				"" + "\n" +
				"            <!--[if (gte mso 9)|(IE)]>" + "\n" +
				"            <table width=\"166\" align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" + "\n" +
				"              <tr>" + "\n" +
				"                <td align=\"left\">" + "\n" +
				"            <![endif]-->" + "\n" +
				"            <table style=\"border-spacing: 0;border-collapse: collapse;vertical-align: top\" width=\"100%\" align=\"left\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">" + "\n" +
				"              <tbody><tr style=\"vertical-align: top\">" + "\n" +
				"                <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\" align=\"left\" valign=\"middle\">" + "\n" +
				"" + "\n" +
				"" + "\n" +
				"                  <table style=\"border-spacing: 0;border-collapse: collapse;vertical-align: top;padding: 0 5px 0 0\" align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"37\">" + "\n" +
				"                      <tbody><tr style=\"vertical-align: top\">" + "\n" +
				"                          <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\" width=\"37\" align=\"left\" valign=\"middle\">" + "\n" +
				"                            <a href=\"https://www.facebook.com/\" title=\"Facebook\" target=\"_blank\">" + "\n" +
				"                                <img style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: block;border: none;height: auto;line-height: 100%;max-width: 32px !important\" src=\"http://gigbuddy.org/images/facebookIcon.jpe\" alt=\"Facebook\" title=\"Facebook\" width=\"32\">" + "\n" +
				"                            </a>" + "\n" +
				"                          </td>" + "\n" +
				"                      </tr>" + "\n" +
				"                  </tbody></table>" + "\n" +
				"                  <table style=\"border-spacing: 0;border-collapse: collapse;vertical-align: top;padding: 0 5px 0 0\" align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"37\">" + "\n" +
				"                      <tbody><tr style=\"vertical-align: top\">" + "\n" +
				"                          <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\" width=\"37\" align=\"left\" valign=\"middle\">" + "\n" +
				"                            <a href=\"http://twitter.com/\" title=\"Twitter\" target=\"_blank\">" + "\n" +
				"                                <img style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: block;border: none;height: auto;line-height: 100%;max-width: 32px !important\" src=\"http://gigbuddy.org/images/twitterIcon.png\" alt=\"Twitter\" title=\"Twitter\" width=\"32\">" + "\n" +
				"                            </a>" + "\n" +
				"                          </td>" + "\n" +
				"                      </tr>" + "\n" +
				"                  </tbody></table>" + "\n" +
				"                  <table style=\"border-spacing: 0;border-collapse: collapse;vertical-align: top;padding: 0 5px 0 0\" align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"37\">" + "\n" +
				"                      <tbody><tr style=\"vertical-align: top\">" + "\n" +
				"                          <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\" width=\"37\" align=\"left\" valign=\"middle\">" + "\n" +
				"                            <a href=\"http://plus.google.com/\" title=\"Google+\" target=\"_blank\">" + "\n" +
				"                                <img style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: block;border: none;height: auto;line-height: 100%;max-width: 32px !important\" src=\"images/googleplus.png\" alt=\"Google+\" title=\"Google+\" width=\"32\">" + "\n" +
				"                            </a>" + "\n" +
				"                          </td>" + "\n" +
				"                      </tr>" + "\n" +
				"                  </tbody></table>" + "\n" +
				"" + "\n" +
				"                </td>" + "\n" +
				"              </tr>" + "\n" +
				"            </tbody></table>" + "\n" +
				"            <!--[if (gte mso 9)|(IE)]>" + "\n" +
				"                </td>" + "\n" +
				"              </tr>" + "\n" +
				"            </table>" + "\n" +
				"            <![endif]-->" + "\n" +
				"          </td>" + "\n" +
				"        </tr>" + "\n" +
				"      </tbody></table>" + "\n" +
				"    </td>" + "\n" +
				"  </tr>" + "\n" +
				"</tbody></table><table style=\"border-spacing: 0;border-collapse: collapse;vertical-align: top\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">" + "\n" +
				"  <tbody><tr style=\"vertical-align: top\">" + "\n" +
				"    <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;padding-top: 10px;padding-right: 10px;padding-bottom: 10px;padding-left: 10px\">" + "\n" +
				"        <div style=\"color:#555555;line-height:120%;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;\">            " + "\n" +
				"        	<div style=\"font-size:12px;line-height:14px;color:#555555;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;text-align:left;\"><p style=\"margin: 0;font-size: 14px;line-height: 17px\">&#65279;&#169;2016 By Tamas Ledecki.. Email from: <a style=\"color:#0000FF;text-decoration: underline; font-size: 14px; line-height: 16px;\" title=\"Visit Our Site\" href=\"http://www.gigbuddy.org\" target=\"_blank\">gigbuddy.org</a></p></div>" + "\n" +
				"        </div>" + "\n" +
				"    </td>" + "\n" +
				"  </tr>" + "\n" +
				"</tbody></table>" + "\n" +
				"</td></tr></tbody></table></div><!--[if (gte mso 9)|(IE)]></td><![endif]--><!--[if (gte mso 9)|(IE)]></td></tr></table><![endif]--></td></tr></tbody></table></td></tr></tbody></table>" + "\n" +
				"                    <!--[if mso]>" + "\n" +
				"                    </td></tr></table>" + "\n" +
				"                    <![endif]-->" + "\n" +
				"                    <!--[if (IE)]>" + "\n" +
				"                    </td></tr></table>" + "\n" +
				"                    <![endif]-->" + "\n" +
				"                  </td>" + "\n" +
				"                </tr>" + "\n" +
				"              </tbody></table>" + "\n" +
				"          </td>" + "\n" +
				"      </tr>" + "\n" +
				"  </tbody></table>" + "\n" +
				"" + "\n" +
				"" + "\n" +
				"</body></html>";
		return returnMessage;
	}

}
