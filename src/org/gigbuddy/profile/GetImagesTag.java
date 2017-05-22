package org.gigbuddy.profile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class GetImagesTag extends SimpleTagSupport {
	private String username;
	
	public void setUsername(String usName) {
		this.username = usName;
	}
	
	public void doTag() {
		
		try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/gigbud5_realm","gigbud5_toni","DCFGTwHzqOZS");
			 Statement s = c.createStatement();
			 ResultSet rs = s.executeQuery("SELECT imageURL,id FROM userimages WHERE username='"+username+"'")) {
			
			JspWriter out = getJspContext().getOut();
				
			if (rs.first() == false) {
				out.println("<li><img class='profileImage' src='../images/defaultProfileImage.jpg' alt='defaultProfileImage' /></li>");
				return;
			}
			rs.beforeFirst();
			while (rs.next()) {
				System.out.println(rs.getString("imageURL"));
				out.println("<li><img class='profileImage' onclick='showImages(this.src)' src='/getImage.do?fileName="+rs.getString("imageURL")+"' alt='"+rs.getString("imageURL").substring(8, rs.getString("imageURL").length()-3)+"' id='"+rs.getInt("id")+"' /></li>");
			}
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
