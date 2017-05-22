package org.gigbuddy.registration;

import java.sql.SQLException;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ejb.EJB;
import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gigbuddy.messaging.MailSenderBean;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;


public class Register extends HttpServlet {
	private static final long serialVersionUID = -6843473993098105325L;
	private String message;
	String userId = "";
	
	@EJB
	private MailSenderBean mailSender;
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println(getLocation(req));
		RequestDispatcher rd = req.getRequestDispatcher("messagePage.jsp");
		String gRecaptchaResponse = req.getParameter("g-recaptcha-response");
		boolean isCaptchaRight = VerifyRecaptcha.verify(gRecaptchaResponse);
		
		if (isCaptchaRight == false) {
			req.setAttribute("message", "Whoops!! We are not sure you are a human");
			req.setAttribute("title", "Unsuccesfull Registration");
			rd.forward(req, res);
			return;
		}
		
		
		
		try(Connection c = DriverManager.getConnection(getServletContext().getInitParameter("databaseURL"),getServletContext().getInitParameter("databaseUsername"),getServletContext().getInitParameter("databasePassword"));
			Statement s = c.createStatement()) {
			s.executeUpdate("INSERT INTO users (username,password,location) VALUES ('"+req.getParameter("u")+"','"+req.getParameter("p")+"','"+getLocation(req)+"')");
			s.executeUpdate("INSERT INTO users_roles (username,rolename) VALUES ('"+req.getParameter("u")+"','member')");
			s.executeUpdate("INSERT INTO regdetails (username,regdate) VALUES ('"+req.getParameter("u")+"',NOW())");
			req.setAttribute("message", "Congratulations! You have sucesfully registered! Please be sure to validate your email address by clicking on the validation link that has been emailed to you!!");
			req.setAttribute("title", "You have registered");
			Cookie usernameCookie = new Cookie("username", req.getParameter("u"));
			System.out.println(req.getParameter("u"));
			usernameCookie.setMaxAge(120);
			usernameCookie.setPath("/");
			res.addCookie(usernameCookie);
			}
		catch(SQLException sqle) {
			if (sqle.getMessage().contains("Duplicate entry")) {
				req.setAttribute("message", "Someone has already registered with this email address! Go to the homepage to login or register with a different email address!");
				req.setAttribute("title", "Error");
			}
		}
		try(Connection c = DriverManager.getConnection(getServletContext().getInitParameter("databaseURL"),getServletContext().getInitParameter("databaseUsername"),getServletContext().getInitParameter("databasePassword"));
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT userId FROM users WHERE username = '"+req.getParameter("u")+"'");) {
			
			rs.first();
			userId = rs.getString("userId");
		}
		catch (SQLException sqle) {
			req.setAttribute("message", sqle.getMessage());
			rd.forward(req, res);
			return;
		}
		mailSender = new MailSenderBean();
		setMessage(req.getParameter("u"));
		try {
			mailSender.sendEmail(req.getParameter("u"), "GigBuddy.org", message);
		} catch (MessagingException e1) {
			req.setAttribute("message", e1.getMessage());
			rd.forward(req, res);
			return;
		} 
		
		rd.forward(req, res);
	}


	String getLocation(HttpServletRequest request) {
		String ip;
		String location = "unknown";
		final String[] HEADERS_TO_TRY = { 
			    "X-Forwarded-For",
			    "Proxy-Client-IP",
			    "WL-Proxy-Client-IP",
			    "HTTP_X_FORWARDED_FOR",
			    "HTTP_X_FORWARDED",
			    "HTTP_X_CLUSTER_CLIENT_IP",
			    "HTTP_CLIENT_IP",
			    "HTTP_FORWARDED_FOR",
			    "HTTP_FORWARDED",
			    "HTTP_VIA",
			    "REMOTE_ADDR" };
		ip = getClientIpAddress(request, HEADERS_TO_TRY);
		File database = new File("/home/gigbud5/public_html/GeoLite2-City.mmdb");
		try {
			DatabaseReader reader = new DatabaseReader.Builder(database).build();
			InetAddress ipAddress = InetAddress.getByName(ip);
			CityResponse response = reader.city(ipAddress);
			Country country = response.getCountry();
			location = country.getName();               // 'United States'
			City city = response.getCity();
			location = city.getName()+", "+location; // 'Minneapolis'
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeoIp2Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return location;
	}
	private String getClientIpAddress(HttpServletRequest request, String[] HEADERS_TO_TRY) {
	    for (String header : HEADERS_TO_TRY) {
	        String ip = request.getHeader(header);
	        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
	            return ip;
	        }
	    }
	    return request.getRemoteAddr();
	}


	private void setMessage(String username) {
		message = "<!DOCTYPE html '-//w3c//dtd xhtml 1.0 transitional //en' 'http://www.w3.org/tr/xhtml1/dtd/xhtml1-transitional.dtd'><html xmlns='http://www.w3.org/1999/xhtml'> " + "\n" +
				  "<head>" + "\n" +
				  	"<meta http-equiv='Content-Type' content='text/html; charset=utf-8'>" + "\n" +
				  	"<meta name='viewport' content='width=device-width'>" + "\n" +
				  	"<meta http-equiv='X-UA-Compatible' content='IE=9; IE=8; IE=7; IE=EDGE'>" + "\n" +
				  	"<title>Thanks For you registeration</title>" + "\n" +
				  "</head>" + "\n" +
				  
				  "<body style='width: 100% !important;min-width: 100%;-webkit-text-size-adjust: 100%;-ms-text-size-adjust: 100% !important;margin: 0;padding: 0;background-color: #FFFFFF'>" + "\n" +
				  	"<style id='media-query'>" + "\n" +
				  		"#outlook a { " + "\n" +
				  		"padding: 0;" + "\n" +
    					"}"+ "\n" +
    					".ExternalClass {" + "\n" +
    						"width: 100%;" + "\n" +
    					"}" + "\n" +

    					".ExternalClass, " + "\n" +
    					".ExternalClass p," + "\n" +
    					".ExternalClass span,"+ "\n" +
    					".ExternalClass font," + "\n" +
    					".ExternalClass td," + "\n" +
    					".ExternalClass div {" + "\n" +
    						"line-height: 100%;" + "\n" +
    					"}" + "\n" +

    					"#backgroundTable {" + "\n" +
    						"margin: 0;" + "\n" +
    						"padding: 0;" + "\n" +
    						"width: 100% !important;" + "\n" +
    						"line-height: 100% !important;"+ "\n" +
    					"}" + "\n" +
    					".button a {" + "\n" +
    						"display: inline-block;" + "\n" +
    						"text-decoration: none;" + "\n" +
    						"-webkit-text-size-adjust: none;" + "\n" +
    						"text-align: center;" + "\n" +
    					"}" + "\n" +
    					".button a div {" + "\n" +
    						"text-align: center !important;" + "\n" +
    					"}" + "\n" +
    					"body.outlook p {" + "\n" +
    						"display: inline !important;" + "\n" +
    					"}" + "\n" +

						"@media only screen and (max-width: 500px) {" + "\n" +
							"table[class='body'] img {" + "\n" +
								"height: auto !important;" + "\n" +
								"width: 100% !important; }" + "\n" +
							"table[class='body'] img.fullwidth {" + "\n" +
								"max-width: 100% !important; }" + "\n" +
							"table[class='body'] center {"+  "\n" +
								"min-width: 0 !important; } "+ "\n" +
							"table[class='body'] .container {" + "\n" +
								"width: 95% !important; }" + "\n" +
							"table[class='body'] .row {" + "\n" +
								"width: 100% !important;" + "\n" +
								"display: block !important; }"+ "\n" +
							"table[class='body'] .wrapper {" + "\n" +
								"display: block !important;" + "\n" +
								"padding-right: 0 !important; }" + "\n" +
							"table[class='body'] .columns, table[class='body'] .column {" + "\n" +
								"table-layout: fixed !important;" + "\n" +
								"float: none !important;" + "\n" +
								"width: 100% !important;" + "\n" +
								"padding-right: 0px !important;" + "\n" +
								"padding-left: 0px !important;" + "\n" +
								"display: block !important; }" + "\n" +
							"table[class='body'] .wrapper.first .columns, table[class='body'] .wrapper.first .column {" + "\n" +
								"display: table !important; }" + "\n" +
							"table[class='body'] table.columns td, table[class='body'] table.column td, .col { "+ "\n" +
								"width: 100% !important; }" + "\n" +
							"table[class='body'] table.columns td.expander {" + "\n" +
								"width: 1px !important; }" + "\n" +
							"table[class='body'] .right-text-pad, table[class='body'] .text-pad-right {" + "\n" +
								"padding-left: 10px !important; }" + "\n" +
							"table[class='body'] .left-text-pad, table[class='body'] .text-pad-left { " + "\n" +
								"padding-right: 10px !important; }" + "\n" +
							"table[class='body'] .hide-for-small, table[class='body'] .show-for-desktop { " + "\n" +
								"display: none !important; } " + "\n" +
							"table[class='body'] .show-for-small, table[class='body'] .hide-for-desktop { " + "\n" +
								"display: inherit !important; } "+ "\n" +
							".mixed-two-up .col { " + "\n" +
								"width: 100% !important; } } " + "\n" +
						"@media screen and (max-width: 500px) { " + "\n" +
							"div[class='col'] { " + "\n" +
								"width: 100% !important;" + "\n" +
      						"}" + "\n" +
    					"}" + "\n" +

    					"@media screen and (min-width: 501px) { " + "\n" +
    						"table[class='container'] {" + "\n" +
    							"width: 500px !important;" + "\n" +
      						"}" + "\n" +
    					"}" + "\n" +
    				"</style>" + "\n" +
    				"<table class='body' style='border-spacing: 0;border-collapse: collapse;vertical-align: top;height: 100%;width: 100%;table-layout: fixed' cellpadding='0' cellspacing='0' width='100%' border='0'>" + "\n" +
    					"<tbody><tr style='vertical-align: top'>" + "\n" +
    						"<td class='center' style='word-break: break-word;border-collapse: collapse !important;vertical-align: top;text-align: center;background-color: #FFFFFF' align='center' valign='top'>" + "\n" +
    				"<table style='border-spacing: 0;border-collapse: collapse;vertical-align: top;background-color: #2C2D37' cellpadding='0' cellspacing='0' align='center' width='100%' border='0'>" + "\n" +
    					"<tbody><tr style='vertical-align: top'>" + "\n" +
    						"<td style='word-break: break-word;border-collapse: collapse !important;vertical-align: top' width='100%'>" + "\n" +
                    "<!--[if gte mso 9]>" + "\n" +
                    "<table id='outlookholder' border='0' cellspacing='0' cellpadding='0' align='center'><tr><td>" + "\n" +
                    "<![endif]-->" + "\n" +
                    "<!--[if (IE)]>" + "\n" +
                    "<table width='500' align='center' cellpadding='0' cellspacing='0' border='0'>" + "\n" +
                        "<tr>" + "\n" +
                            "<td>" + "\n" +
                    "<![endif]-->" + "\n" +
                    "<table class='container' style='border-spacing: 0;border-collapse: collapse;vertical-align: top;max-width: 500px;margin: 0 auto;text-align: inherit' cellpadding='0' cellspacing='0' align='center' width='100%' border='0'><tbody><tr style='vertical-align: top'><td style='word-break: break-word;border-collapse: collapse !important;vertical-align: top' width='100%'><table class='block-grid two-up' style='border-spacing: 0;border-collapse: collapse;vertical-align: top;width: 100%;max-width: 500px;color: #333;background-color: transparent' cellpadding='0' cellspacing='0' width='100%' bgcolor='transparent'><tbody><tr style='vertical-align: top'><td style='word-break: break-word;border-collapse: collapse !important;vertical-align: top;text-align: center;font-size: 0'><!--[if (gte mso 9)|(IE)]><table width='100%' align='center' bgcolor='transparent' cellpadding='0' cellspacing='0' border='0'><tr><![endif]--><!--[if (gte mso 9)|(IE)]><td valign='top' width='250'><![endif]--><div class='col num6' style='display: inline-block;vertical-align: top;text-align: center;width: 250px'><table style='border-spacing: 0;border-collapse: collapse;vertical-align: top' cellpadding='0' cellspacing='0' align='center' width='100%' border='0'><tbody><tr style='vertical-align: top'><td style='word-break: break-word;border-collapse: collapse !important;vertical-align: top;background-color: transparent;padding-top: 20px;padding-right: 0px;padding-bottom: 5px;padding-left: 0px;border-top: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-left: 0px solid transparent'><table style='border-spacing: 0;border-collapse: collapse;vertical-align: top' cellpadding='0' cellspacing='0' width='100%'>" + "\n" +
                    	"<tbody><tr style='vertical-align: top'>" + "\n" +
                    		"<td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 16px;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif\"><div class='our-class'>" + "\n" +
                    			"<img src='http://gigbuddy.org/images/logo.png' width='150'>" + "\n" +
                    			"</div></td>" + "\n" +
                    			"</tr>" + "\n" +
                    	"</tbody></table></td></tr></tbody></table></div><!--[if (gte mso 9)|(IE)]></td><![endif]--><!--[if (gte mso 9)|(IE)]><td valign='top' width='250'><![endif]--><div class='col num6' style='display: inline-block;vertical-align: top;text-align: center;width: 250px'><table style='border-spacing: 0;border-collapse: collapse;vertical-align: top' cellpadding='0' cellspacing='0' align='center' width='100%' border='0'><tbody><tr style='vertical-align: top'><td style='word-break: break-word;border-collapse: collapse !important;vertical-align: top;background-color: transparent;padding-top: 20px;padding-right: 0px;padding-bottom: 20px;padding-left: 0px;border-top: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-left: 0px solid transparent'><table style='border-spacing: 0;border-collapse: collapse;vertical-align: top' cellpadding='0' cellspacing='0' width='100%'>" + "\n" +
                    		"<tbody><tr style='vertical-align: top'>" + "\n" +
                    			"<td style='word-break: break-word;border-collapse: collapse !important;vertical-align: top;padding-top: 20px;padding-right: 10px;padding-bottom: 20px;padding-left: 10px'>" + "\n" +
                    				"<div style=\"color:#6E6F7A;line-height:150%;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;\">" + "\n" +           
                    				"<div style=\"font-size:12px;line-height:18px;color:#6E6F7A;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;text-align:left;\"><div style='line-height:18px; font-size:12px; text-align: center;'><span style='font-size: 20px; line-height: 30px; color: rgb(255, 255, 153);'><strong>&#65279;Find Your Gig <span style='font-size: 20px; line-height: 30px;'>Buddies</span></strong></span></div></div> " + "\n" +
                    				"</div>" + "\n" +
                    			"</td>" + "\n" +
                    			"</tr>" + "\n" +
                    	"</tbody></table>" + "\n" +
                    	"<table style='border-spacing: 0;border-collapse: collapse;vertical-align: top' cellpadding='0' cellspacing='0' width='100%'> " + "\n" +
                    		"<tbody><tr style='vertical-align: top'>" + "\n" +
                    			"<td style='word-break: break-word;border-collapse: collapse !important;vertical-align: top;padding-top: 10px;padding-right: 10px;padding-bottom: 10px;padding-left: 10px'>" + "\n" +
                    				"<div style=\"color:#555555;line-height:120%;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;\">" + "\n" +            
                    				"<div style=\"font-size:12px;line-height:14px;color:#555555;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;text-align:left;\"><p style='margin: 0;font-size: 12px;line-height: 14px'><span style='color: rgb(255, 255, 255); font-size: 12px; line-height: 14px;'><span style='font-size: 18px; line-height: 21px; color: rgb(255, 204, 153);'>GIGBUDDY</span>.ORG</span></p></div>" + "\n" +
                    				"</div>" + "\n" +
                    			"</td>" + "\n" +
                    			"</tr>" + "\n" +
                    	"</tbody></table>" + "\n" +
                    	"</td></tr></tbody></table></div><!--[if (gte mso 9)|(IE)]></td><![endif]--><!--[if (gte mso 9)|(IE)]></td></tr></table><![endif]--></td></tr></tbody></table></td></tr></tbody></table>" + "\n" +
                    	"<!--[if mso]>" + "\n" +
                    	"</td></tr></table>" + "\n" +
                    	"<![endif]-->" + "\n" +
                    	"<!--[if (IE)]>" + "\n" +
                    	"</td></tr></table>" + "\n" +
                    	"<![endif]-->" + "\n" +
                    	"</td>" + "\n" +
                    	"</tr>" + "\n" +
                    	"</tbody></table>" + "\n" +
                    	"<table style='border-spacing: 0;border-collapse: collapse;vertical-align: top;background-color: #323341' cellpadding='0' cellspacing='0' align='center' width='100%' border='0'>" + "\n" +
                    		"<tbody><tr style='vertical-align: top'>" + "\n" +
                    			"<td style='word-break: break-word;border-collapse: collapse !important;vertical-align: top' width='100%'>" + "\n" +
                    				"<!--[if gte mso 9]>" + "\n" +
                    					"<table id='outlookholder' border='0' cellspacing='0' cellpadding='0' align='center'><tr><td>" + "\n" +
                    				"<![endif]-->" + "\n" +
                    				"<!--[if (IE)]>" + "\n" +
                    					"<table width='500' align='center' cellpadding='0' cellspacing='0' border='0'>" + "\n" +
                    						"<tr>" + "\n" +
                    							"<td>" + "\n" +
                    				"<![endif]-->" + "\n" +
                    "<table class='container' style='border-spacing: 0;border-collapse: collapse;vertical-align: top;max-width: 500px;margin: 0 auto;text-align: inherit' cellpadding='0' cellspacing='0' align='center' width='100%' border='0'><tbody><tr style='vertical-align: top'><td style='word-break: break-word;border-collapse: collapse !important;vertical-align: top' width='100%'><table class='block-grid' style='border-spacing: 0;border-collapse: collapse;vertical-align: top;width: 100%;max-width: 500px;color: #000000;background-color: transparent' cellpadding='0' cellspacing='0' width='100%' bgcolor='transparent'><tbody><tr style='vertical-align: top'><td style='word-break: break-word;border-collapse: collapse !important;vertical-align: top;text-align: center;font-size: 0'><!--[if (gte mso 9)|(IE)]><table width='100%' align='center' bgcolor='transparent' cellpadding='0' cellspacing='0' border='0'><tr><![endif]--><!--[if (gte mso 9)|(IE)]><td valign='top' width='500'><![endif]--><div class='col num12' style='display: inline-block;vertical-align: top;width: 100%'><table style='border-spacing: 0;border-collapse: collapse;vertical-align: top' cellpadding='0' cellspacing='0' align='center' width='100%' border='0'><tbody><tr style='vertical-align: top'><td style='word-break: break-word;border-collapse: collapse !important;vertical-align: top;background-color: transparent;padding-top: 0px;padding-right: 0px;padding-bottom: 0px;padding-left: 0px;border-top: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-left: 0px solid transparent'><table style='border-spacing: 0;border-collapse: collapse;vertical-align: top' cellpadding='0' cellspacing='0' width='100%'>" + "\n" +
                    	"<tbody><tr style='vertical-align: top'>" + "\n" +
                    		"<td style='word-break: break-word;border-collapse: collapse !important;vertical-align: top;padding-top: 10px;padding-right: 10px;padding-bottom: 10px;padding-left: 10px'>" + "\n" +
                    			"<div style=\"color:#555555;line-height:120%;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;\">" + "\n" + 
                    			"<div style=\"font-size:12px;line-height:14px;color:#555555;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;text-align:left;\"><p style='margin: 0;font-size: 12px;line-height: 14px;text-align: center'><span style='font-size: 12px; line-height: 14px; color: rgb(255, 255, 255);'><span style='font-size: 12px; line-height: 14px;' id='_mce_caret' data-mce-bogus='true'><span style='font-size: 22px; line-height: 26px;'>&#65279;We Are Happy To See You There</span></span><br></span></p></div>" + "\n" +
                    			"</div>" + "\n" +
                    		"</td>" + "\n" +
                    		"</tr>" + "\n" +
                    	"</tbody></table>" + "\n" +
                    "<table style='border-spacing: 0;border-collapse: collapse;vertical-align: top' cellpadding='0' cellspacing='0' width='100%'>" + "\n" +
                    	"<tbody><tr style='vertical-align: top'>" + "\n" +
                    		"<td style='word-break: break-word;border-collapse: collapse !important;vertical-align: top;padding-top: 10px;padding-right: 10px;padding-bottom: 10px;padding-left: 10px'>" + "\n" +
                    			"<div style=\"color:#555555;line-height:120%;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;\">" + "\n" +            
                    			"<div style=\"font-size:12px;line-height:14px;color:#555555;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;text-align:left;\"><p style=\"margin: 0;font-size: 14px;line-height: 17px;text-align: center\"><span style=\"font-size: 14px; line-height: 16px;\" id=\"_mce_caret\" data-mce-bogus=\"true\"><span style=\"font-size: 36px; line-height: 43px; color: rgb(255, 255, 153);\">&#65279;Thanks for Registering</span></span><br></p></div>" + "\n" +
                    			"</div>" + "\n" +
                    		"</td>" + "\n" +
                    		"</tr>" + "\n" +
                    	"</tbody></table>" + "\n" +
                    "<table style='border-spacing: 0;border-collapse: collapse;vertical-align: top' cellpadding='0' cellspacing='0' width='100%'>" + "\n" +
                    	"<tbody><tr style='vertical-align: top'>" + "\n" +
                    		"<td style='word-break: break-word;border-collapse: collapse !important;vertical-align: top;padding-top: 10px;padding-right: 10px;padding-bottom: 10px;padding-left: 10px'>" + "\n" +
                    		"<div style=\"color:#555555;line-height:120%;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;\">" + "\n" +            
                    		"<div style=\"font-size:12px;line-height:14px;color:#555555;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;text-align:left;\"><p style=\"margin: 0;font-size: 14px;line-height: 17px\"><span style=\"color: rgb(255, 255, 255); font-size: 14px; line-height: 16px;\">Thanks for becoming a member of our community...</span></p><p style=\"margin: 0;font-size: 14px;line-height: 16px\"><span style=\"color: rgb(255, 255, 255); font-size: 14px; line-height: 16px;\">Your Username : "+username+"</span><br></p><p style=\"margin: 0;font-size: 14px;line-height: 17px\"><span style=\"color: rgb(255, 255, 255); font-size: 14px; line-height: 16px;\">Your validation link: http://gigbuddy.org/validateEmail.do?userId="+userId+"</span></p><p style=\"margin: 0;font-size: 14px;line-height: 16px\"><span style=\"color: rgb(255, 255, 255); font-size: 14px; line-height: 16px;\"><br data-mce-bogus=\"1\"></span></p></div>" + "\n" +
                    		"</div>" + "\n" +
                    		"</td>" + "\n" +
                    		"</tr>" + "\n" +
                    "</tbody></table>" + "\n" +
                    "<table style='border-spacing: 0;border-collapse: collapse;vertical-align: top' width='100%' border='0' cellspacing='0' cellpadding='0'>" + "\n" +
                    	"<tbody><tr style='vertical-align: top'>" + "\n" +
                    		"<td class='button-container' style='word-break: break-word;border-collapse: collapse !important;vertical-align: top;padding-top: 10px;padding-right: 10px;padding-bottom: 10px;padding-left: 10px' align='center'>" + "\n" +
                    			"<table style='border-spacing: 0;border-collapse: collapse;vertical-align: top' width='100%' border='0' cellspacing='0' cellpadding='0' align='center'>" + "\n" +
                    				"<tbody><tr style='vertical-align: top'>" + "\n" +
                    					"<td class='button' style='word-break: break-word;border-collapse: collapse !important;vertical-align: top' width='100%' align='center' valign='middle'>" + "\n" +
                    						"<!--[if mso]>" + "\n" +
                    							"<v:roundrect xmlns:v='urn:schemas-microsoft-com:vml' xmlns:w='urn:schemas-microsoft-com:office:word' href='http://gigbuddy.org/members/index.jsp'" + "\n" +
                    								"style='" + "\n" +
                    								"height:42px;" + "\n" +
                    								"v-text-anchor:middle;" + "\n" +
                    								"width:142px;'" + "\n" +
                    								"arcsize='10%'" + "\n" +
                    								"strokecolor='#3AAEE0'" + "\n" +
                    								"fillcolor='#3AAEE0' >" + "\n" +
                    							"<w:anchorlock/>" + "\n" +
                    							"<center" + "\n" + 
                    							"style='color:#ffffff;'" + "\n" +
                    							"font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;" + "\n" +
                    							"font-size:16px;'>" + "\n" +
                    						"<![endif]-->" + "\n" +
                    						"<!--[if !mso]><!- - --><div style='display: inline-block;" + "\n" +
                    							"border-radius: 4px;" + "\n" + 
                    							"-webkit-border-radius: 4px;" + "\n" + 
                    							"-moz-border-radius: 4px;" + "\n" + 
                    							"max-width: 100%;" + "\n" +
                    							"width: auto;" + "\n" +
                    							"border-top: 0px solid transparent;" + "\n" +
                    							"border-right: 0px solid transparent;" + "\n" +
                    							"border-bottom: 0px solid transparent;" + "\n" +
                    							"border-left: 0px solid transparent;' align='center'>" + "\n" +

              								"<table style='border-spacing: 0;border-collapse: collapse;vertical-align: top;height: 42' width='100%' border='0' cellspacing='0' cellpadding='0'>" + "\n" +
              									"<tbody><tr style='vertical-align: top'><td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;border-radius: 4px; -webkit-border-radius: 4px; -moz-border-radius: 4px; color: #ffffff; background-color: #3AAEE0; padding-top: 5px; padding-right: 20px; padding-bottom: 5px; padding-left: 20px; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif;text-align: center\" valign='middle'><!--<![endif]-->" + "\n" +
              										"<a style='display: inline-block;text-decoration: none;-webkit-text-size-adjust: none;text-align: center;background-color: #3AAEE0;color: #ffffff' href='http://gigbuddy.org/members/index.jsp' target='_blank'> " + "\n" +
              										"<span style='font-size:16px;line-height:32px;'>Login Now</span>" + "\n" +
              										"</a>" + "\n" +
              								"<!--[if !mso]><!- - --></td></tr></tbody></table>" + "\n" +
              								"</div><!--<![endif]-->" + "\n" +
              								"<!--[if mso]>" + "\n" +
              									"</center>" + "\n" +
              									"</v:roundrect>" + "\n" +
              								"<![endif]-->" + "\n" +
              							"</td>" + "\n" +
              						"</tr>" + "\n" +
              					"</tbody></table>" + "\n" +
              				"</td>" + "\n" +
              			"</tr>" + "\n" +
              		"</tbody></table>" + "\n" +
              		"<table style='border-spacing: 0;border-collapse: collapse;vertical-align: top' align='center' width='100%' border='0' cellpadding='0' cellspacing='0'>" + "\n" +
              			"<tbody><tr style='vertical-align: top'>" + "\n" +
              				"<td style='word-break: break-word;border-collapse: collapse !important;vertical-align: top;padding-top: 10px;padding-right: 10px;padding-bottom: 10px;padding-left: 10px' align='center'>" + "\n" +
              					"<div style='height: 10px;'>" + "\n" +
              					"<table style='border-spacing: 0;border-collapse: collapse;vertical-align: top;border-top: 10px solid transparent;width: 100%' align='center' border='0' cellspacing='0'> " + "\n" +
              						"<tbody><tr style='vertical-align: top'>" + "\n" +
              							"<td style='word-break: break-word;border-collapse: collapse !important;vertical-align: top' align='center'></td>" + "\n" +
              							"</tr>" + "\n" +
              					"</tbody></table>" + "\n" +
              					"</div>" + "\n" +
              				"</td>" + "\n" +
              			"</tr>" + "\n" +
              		"</tbody></table></td></tr></tbody></table></div><!--[if (gte mso 9)|(IE)]></td><![endif]--><!--[if (gte mso 9)|(IE)]></td></tr></table><![endif]--></td></tr></tbody></table></td></tr></tbody></table>" + "\n" +
                    "<!--[if mso]>" + "\n" +
                    "</td></tr></table>" + "\n" +
                    "<![endif]-->" + "\n" +
                    "<!--[if (IE)]>" + "\n" +
                    "</td></tr></table>" + "\n" +
                    "<![endif]-->" + "\n" +
                  "</td>" + "\n" +
                "</tr>" + "\n" +
              "</tbody></table>" + "\n" +
              "<table style='border-spacing: 0;border-collapse: collapse;vertical-align: top;background-color: #ffffff' cellpadding='0' cellspacing='0' align='center' width='100%' border='0'>" + "\n" +
                "<tbody><tr style='vertical-align: top'>" + "\n" +
                  "<td style='word-break: break-word;border-collapse: collapse !important;vertical-align: top' width='100%'>" + "\n" +
                    "<!--[if gte mso 9]>" + "\n" +
                    "<table id='outlookholder' border='0' cellspacing='0' cellpadding='0' align='center'><tr><td>" + "\n" +
                    "<![endif]-->" + "\n" +
                    "<!--[if (IE)]>" + "\n" +
                    "<table width='500' align='center' cellpadding='0' cellspacing='0' border='0'>" + "\n" +
                        "<tr>" + "\n" +
                            "<td>" + "\n" +
                    "<![endif]-->" + "\n" +
                    "<table class='container' style='border-spacing: 0;border-collapse: collapse;vertical-align: top;max-width: 500px;margin: 0 auto;text-align: inherit' cellpadding='0' cellspacing='0' align='center' width='100%' border='0'><tbody><tr style='vertical-align: top'><td style='word-break: break-word;border-collapse: collapse !important;vertical-align: top' width='100%'><table class='block-grid' style='border-spacing: 0;border-collapse: collapse;vertical-align: top;width: 100%;max-width: 500px;color: #333;background-color: transparent' cellpadding='0' cellspacing='0' width='100%' bgcolor='transparent'><tbody><tr style='vertical-align: top'><td style='word-break: break-word;border-collapse: collapse !important;vertical-align: top;text-align: center;font-size: 0'><!--[if (gte mso 9)|(IE)]><table width='100%' align='center' bgcolor='transparent' cellpadding='0' cellspacing='0' border='0'><tr><![endif]--><!--[if (gte mso 9)|(IE)]><td valign='top' width='500'><![endif]--><div class='col num12' style='display: inline-block;vertical-align: top;width: 100%'><table style='border-spacing: 0;border-collapse: collapse;vertical-align: top' cellpadding='0' cellspacing='0' align='center' width='100%' border='0'><tbody><tr style='vertical-align: top'><td style='word-break: break-word;border-collapse: collapse !important;vertical-align: top;background-color: transparent;padding-top: 30px;padding-right: 0px;padding-bottom: 30px;padding-left: 0px;border-top: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-left: 0px solid transparent'>" + "\n" +
                    "<table style='border-spacing: 0;border-collapse: collapse;vertical-align: top' width='100%' border='0' cellspacing='0' cellpadding='0'>" + "\n" +
                    "<tbody><tr style='vertical-align: top'>" + "\n" +
                    "<td style='word-break: break-word;border-collapse: collapse !important;vertical-align: top' align='center' valign='top'>" + "\n" +
                    "<table style='border-spacing: 0;border-collapse: collapse;vertical-align: top' border='0' cellspacing='0' cellpadding='0'>" + "\n" +
                    "<tbody><tr style='vertical-align: top'>" + "\n" +
                    "<td style='word-break: break-word;border-collapse: collapse !important;vertical-align: top;text-align: center;padding-top: 10px;padding-right: 10px;padding-bottom: 10px;padding-left: 10px;max-width: 156px' align='center' valign='top'>" + "\n" +

            		"<!--[if (gte mso 9)|(IE)]>" + "\n" +
            		"<table width='166' align='left' border='0' cellspacing='0' cellpadding='0'>" + "\n" +
            		"<tr>" + "\n" +
            		"<td align='left'>" + "\n" +
            		"<![endif]-->" + "\n" +
            		"<table style='border-spacing: 0;border-collapse: collapse;vertical-align: top' width='100%' align='left' cellpadding='0' cellspacing='0' border='0'>" + "\n" +
            		"<tbody><tr style='vertical-align: top'>" + "\n" +
            		"<td style='word-break: break-word;border-collapse: collapse !important;vertical-align: top' align='left' valign='middle'>" + "\n" +


                  	"<table style='border-spacing: 0;border-collapse: collapse;vertical-align: top;padding: 0 5px 5px 0' align='left' border='0' cellspacing='0' cellpadding='0' height='37'>" + "\n" +
                      "<tbody><tr style='vertical-align: top'>" + "\n" +
                          "<td style='word-break: break-word;border-collapse: collapse !important;vertical-align: top' width='37' align='left' valign='middle'>" + "\n" +
                            "<a href='https://www.facebook.com/' title='Facebook' target='_blank'>" + "\n" +
                                "<img style='outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: block;border: none;height: auto;line-height: 100%;max-width: 32px !important' src='http://gigbuddy.org/images/facebookIcon.jpe' alt='Facebook' title='Facebook' width='32'>" + "\n" +
                            "</a>" + "\n" +
                          "</td>" + "\n" +
                      "</tr>" + "\n" +
                  "</tbody></table>" + "\n" +
                  "<table style='border-spacing: 0;border-collapse: collapse;vertical-align: top;padding: 0 5px 5px 0' align='left' border='0' cellspacing='0' cellpadding='0' height='37'>" + "\n" +
                      "<tbody><tr style='vertical-align: top'>" + "\n" +
                          "<td style='word-break: break-word;border-collapse: collapse !important;vertical-align: top' width='37' align='left' valign='middle'>" + "\n" +
                            "<a href='http://twitter.com/' title='Twitter' target='_blank'>" + "\n" +
                                "<img style='outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: block;border: none;height: auto;line-height: 100%;max-width: 32px !important' src='http://gigbuddy.org/images/twitterIcon.png' alt='Twitter' title='Twitter' width='32'>" + "\n" +
                            "</a>" + "\n" +
                          "</td>" + "\n" +
                      "</tr>" + "\n" +
                  "</tbody></table>" + "\n" +
                  "<table style='border-spacing: 0;border-collapse: collapse;vertical-align: top;padding: 0 5px 5px 0' align='left' border='0' cellspacing='0' cellpadding='0' height='37'>" + "\n" +
                      "<tbody><tr style='vertical-align: top'>" + "\n" +
                          "<td style='word-break: break-word;border-collapse: collapse !important;vertical-align: top' width='37' align='left' valign='middle'>" + "\n" +
                            "<a href='http://plus.google.com/' title='Google+' target='_blank'>" + "\n" +
                                "<img style='outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: block;border: none;height: auto;line-height: 100%;max-width: 32px !important' src='images/googleplus.png' alt='Google+' title='Google+' width='32'>" + "\n" +
                            "</a>" + "\n" +
                          "</td>" + "\n" +
                      "</tr>" + "\n" +
                  "</tbody></table>" + "\n" +

                "</td>" + "\n" +
              "</tr>" + "\n" +
            "</tbody></table>" + "\n" +
            "<!--[if (gte mso 9)|(IE)]>" + "\n" +
                "</td>" + "\n" +
              "</tr>" + "\n" +
            "</table>" + "\n" +
            "<![endif]-->" + "\n" +
          "</td>" + "\n" +
        "</tr>" + "\n" +
      "</tbody></table>" + "\n" +
    "</td>" + "\n" +
  "</tr>" + "\n" +
"</tbody></table><table style='border-spacing: 0;border-collapse: collapse;vertical-align: top' cellpadding='0' cellspacing='0' width='100%'>" + "\n" +
  "<tbody><tr style='vertical-align: top'>" + "\n" +
    "<td style='word-break: break-word;border-collapse: collapse !important;vertical-align: top;padding-top: 10px;padding-right: 10px;padding-bottom: 10px;padding-left: 10px'>" + "\n" +
        "<div style=\"color:#555555;line-height:120%;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;\">" + "\n" +            
        	"<div style=\"font-size:12px;line-height:14px;color:#555555;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;text-align:left;\"><p style=\"margin: 0;font-size: 14px;line-height: 17px\"><span style=\"color: rgb(0, 0, 0); font-size: 14px; line-height: 16px;\">&#65279;&#169;2016 By Tamas Ledecki.. Email from: <a style=\"color:#0000FF;text-decoration: underline; color: #000000; font-size: 14px; line-height: 16px;\" title=\"Visit our site\" href=\"http://gigbuddy.org\" target=\"_blank\">gigbuddy.org</a></span><br></p></div>" + "\n" +
        "</div>" + "\n" +
    "</td>" + "\n" +
  "</tr>" + "\n" +
"</tbody></table>" + "\n" +
"</td></tr></tbody></table></div><!--[if (gte mso 9)|(IE)]></td><![endif]--><!--[if (gte mso 9)|(IE)]></td></tr></table><![endif]--></td></tr></tbody></table></td></tr></tbody></table>" + "\n" +
                    "<!--[if mso]>" + "\n" +
                    "</td></tr></table>" + "\n" +
                    "<![endif]-->" + "\n" +
                    "<!--[if (IE)]>" + "\n" +
                    "</td></tr></table>" + "\n" +
                    "<![endif]-->" + "\n" +
                  "</td>" + "\n" +
                "</tr>" + "\n" +
              "</tbody></table>" + "\n" +
          "</td>" + "\n" +
      "</tr>" + "\n" +
  "</tbody></table>" + "\n" +


"</body></html>";
		
	}

}
