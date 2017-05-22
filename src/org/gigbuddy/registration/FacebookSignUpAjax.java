package org.gigbuddy.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.PreparedStatement;

public class FacebookSignUpAjax extends HttpServlet {

	private static final long serialVersionUID = -6540172901862565347L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		ArrayList<String> bands = getBandNames(req.getParameter("bands"));
		long id = Long.parseLong(req.getParameter("id"));
		PrintWriter pw = res.getWriter();
		
		Connection c = null;
		PreparedStatement insertIntoUsers = null;
		PreparedStatement insertIntoUserRoles = null;
		PreparedStatement insertIntoRegDetails = null;
		PreparedStatement insertBandLikes = null;
		try {
			c = DriverManager.getConnection(getServletContext().getInitParameter("databaseURL")+"?rewriteBatchedStatements=true",getServletContext().getInitParameter("databaseUsername"),getServletContext().getInitParameter("databasePassword"));
			c.setAutoCommit(false);
			insertIntoUsers = c.prepareStatement("INSERT INTO users (username,password,location,name) VALUES (?,?,?,?)");
			insertIntoUserRoles = c.prepareStatement("INSERT INTO users_roles (username,rolename) VALUES (?,'member')");
			insertIntoRegDetails = c.prepareStatement("INSERT INTO regdetails (username,regdate,isconfirmed) VALUES (?,NOW(),?)");
			
			if (bands != null) insertBandLikes = c.prepareStatement("INSERT INTO bands VALUES(?,?)");
			
			insertIntoUsers.setString(1, email);
			insertIntoUsers.setString(2, email);
			//Production "+new Register().getLocation(req)+"
			insertIntoUsers.setString(3, new Register().getLocation(req));
			insertIntoUsers.setString(4, name);
			insertIntoUsers.executeUpdate();
				
			insertIntoUserRoles.setString(1, email);
			insertIntoUserRoles.executeUpdate();
				
			insertIntoRegDetails.setString(1, email);
			insertIntoRegDetails.setBoolean(2, true);
			insertIntoRegDetails.executeUpdate();
				
			if (bands != null) {
				for (String bandName: bands) {
					insertBandLikes.setString(1, email);
					insertBandLikes.setString(2, bandName);
					insertBandLikes.addBatch();
				}
				insertBandLikes.executeBatch();
			}
			
			
			c.commit();
			pw.write("Congratulations "+name+"!<br>You have sucessfully signed up! Your username is "+email+" and your password is "+email);
			
			//Create cookie to prevent register pop up from popping up
			Cookie beenHere = new Cookie("beenHere","yes");
			beenHere.setMaxAge(30*60*60*24);
			beenHere.setPath("/");
			res.addCookie(beenHere);
			
			//Create cookie to automatically log people in
			Cookie logInCookie = new Cookie("username",email);
			logInCookie.setMaxAge(60);
			logInCookie.setPath("/");
			res.addCookie(logInCookie);
			}
			catch(SQLException sqle) {
				if ((sqle.getMessage() != null) && (sqle.getMessage().contains("Duplicate entry"))) {
					try {
						c.rollback();
						c.setAutoCommit(true);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					pw.write("Someone has already registered with this facebook profile! Go to the homepage to login. Your email address is "+email+" and your password is (unless you have changed it) "+id+" .Alternatively you can register with a different email address!");
				}
				else {
					pw.write(sqle.getMessage());
				}
			}
			finally {
				try {
					c.setAutoCommit(true);
					if (insertIntoUsers != null) insertIntoUsers.close();
					if (insertIntoUserRoles != null) insertIntoUserRoles.close(); 
					if (insertIntoRegDetails != null) insertIntoRegDetails.close();
					c.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				
			}
		
	}
	private ArrayList<String> getBandNames(String json) {
		if (json == null) return null;
		String[] lines = json.split(",");
		ArrayList<String> returnList = new ArrayList<String>();
		String temp;
		for (int i=0; i<lines.length; i++) {
			if (lines[i].contains("name")) {
				temp = lines[i].substring(lines[i].lastIndexOf(':')+2, lines[i].lastIndexOf('"'));
				returnList.add(temp);
			}
		}
		return returnList;
	}
}
