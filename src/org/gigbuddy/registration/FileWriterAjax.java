package org.gigbuddy.registration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileWriterAjax
 */
@WebServlet(description = "Used to create and write into a logfile from javascript", urlPatterns = { "/writeIntoLogFile.do" })
public class FileWriterAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String text = request.getParameter("text");
		boolean locationNeeded = Boolean.parseBoolean(request.getParameter("location"));
		//Test Path: c:/Studies/Java/Projects/GigbuddyThings/regLog.txt
		//Production path: /home/gigbud5/public_html/regLog.txt
		File logFile = new File("/home/gigbud5/public_html/regLog.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(logFile,true));
		bw.newLine();
		if (locationNeeded) bw.append(text+" "+new Register().getLocation(request)+"\n");
		else bw.append(text+"\n");
		bw.close();
	}

}
