package org.gigbuddy.profile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class GetUserLocationAndNameTag extends SimpleTagSupport {
	private String username;
	
	public void setUsername(String usName) {
		this.username = usName;
	}
	
	public void doTag() {
		try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/gigbud5_realm","gigbud5_toni","DCFGTwHzqOZS");
			 Statement s = c.createStatement();
			 ResultSet rs = s.executeQuery("SELECT name,location FROM users WHERE username='"+username+"' LIMIT 1")) {
			
			JspWriter out = getJspContext().getOut();
			rs.first();
			if (rs.getString("name").isEmpty()) out.println("<div class='name'>"+username+"</div>");
			else out.println("<div class='name'>"+rs.getString("name")+"</div>");
			if (rs.getString("location").isEmpty()) out.println("<div class='location'><span class='pin'>&#59172;</span>Unknown</div>");
			else out.println("<div class='location'><span class='pin'>&#59172;</span>"+rs.getString("location")+"</div>");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
