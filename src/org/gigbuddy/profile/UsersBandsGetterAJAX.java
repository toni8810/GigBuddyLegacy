package org.gigbuddy.profile;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UsersBandsGetterAJAX extends HttpServlet {

	private static final long serialVersionUID = 4454131395088635229L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/plain");
		int bandCounter = 0;
		int[] trOccurences = new int[] {1,4,7,10,13,16,19,22,25,28,31,34,37,40,43,46,49,52,55,58,61,64,67,70,73,76,79,82,85,88};
		
		
		try (Connection c = DriverManager.getConnection(getServletContext().getInitParameter("databaseURL"),getServletContext().getInitParameter("databaseUsername"),getServletContext().getInitParameter("databasePassword"));
			 Statement s = c.createStatement();
			 PrintWriter pw = res.getWriter()) {
			
			ResultSet rs = s.executeQuery("SELECT band FROM bands WHERE username = '"+req.getUserPrincipal().getName()+"'");
			
			while(rs.next()) {
				bandCounter++;
				if (contains(trOccurences,bandCounter)) {
					pw.write("<tr>");
				}
				pw.write("<td onclick='deleteBand(this)'>"+rs.getString("band")+"</td>");
				if (bandCounter % 3 == 0) {
					pw.write("</tr>");
				}
			}
			if (bandCounter % 3 != 0) {
				pw.write("</tr>");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	private boolean contains(int[] intArray, int num) {
		for (int i=0; i<intArray.length; i++) {
			if (intArray[i] == num) return true;
		}
		return false;
	}

}
