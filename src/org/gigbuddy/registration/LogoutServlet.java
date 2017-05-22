package org.gigbuddy.registration;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = -422636494938624988L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		req.getSession().invalidate();
		try {
			res.sendRedirect("/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
