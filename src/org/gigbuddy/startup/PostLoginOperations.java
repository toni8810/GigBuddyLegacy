package org.gigbuddy.startup;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class PostLoginOperations implements Filter {
	protected FilterConfig filterConfig;
	@Override
	public void destroy() {
		this.filterConfig = null;

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) req;
		HttpSession s = httpReq.getSession(); 
		if (s.getAttribute("mainImage") == null) {
			String username = httpReq.getUserPrincipal().getName();
			try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/gigbud5_realm","gigbud5_toni","DCFGTwHzqOZS");
				 Statement statement = c.createStatement()) {
			
				ResultSet rs = statement.executeQuery("SELECT mainImageURL FROM users WHERE username='"+username+"'");
				
				rs.first();
				if (!rs.getString("mainImageURL").startsWith("/home")) s.setAttribute("mainImage",rs.getString("mainImageURL"));
				else s.setAttribute("mainImage","/getImage.do?fileName="+rs.getString("mainImageURL"));
				
				rs = statement.executeQuery("SELECT messageRead FROM messaging WHERE messageRead = 0 AND username = '"+username+"'");
				if (rs.first() == false) {
					s.setAttribute("numOfUnreadMessages", 0);
				}
				else {
					rs.last();
					s.setAttribute("numOfUnreadMessages", rs.getRow());
				}
				rs.close();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;

	}

}
