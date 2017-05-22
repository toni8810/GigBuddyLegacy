package org.gigbuddy.startup;

import javax.servlet.http.HttpServlet;

public class MySQLReferencing extends HttpServlet {
	private static final long serialVersionUID = 6064636931658675289L;
	
	public void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("JDBC driver registered successfully");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
