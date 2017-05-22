package org.gigbuddy.profile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import net.coobird.thumbnailator.Thumbnails;

public class Uploader extends HttpServlet {
	private static final long serialVersionUID = -1474534894043526798L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		boolean isMultiPart = ServletFileUpload.isMultipartContent(req);
		if (isMultiPart == false) return;
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		File file = null;
		Random numberGenerator = new Random();
		String fileName = "";
		String filePath = "/home/gigbud5/public_html/userimages/";
		//String filePath = "c:/Studies/Java/Projects/GigbuddyThings/imgs/";
		String userId = null;
		try (Connection c = DriverManager.getConnection(getServletContext().getInitParameter("databaseURL"),getServletContext().getInitParameter("databaseUsername"),getServletContext().getInitParameter("databasePassword"));
			 Statement s = c.createStatement();
			 ResultSet rs = s.executeQuery("SELECT userId FROM users WHERE username = '"+req.getUserPrincipal().getName()+"'")) {
				
			rs.first();
			userId = rs.getString("userId");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		(new File(filePath+userId)).mkdir();
		filePath = filePath + userId;
		System.out.println(filePath);
		try {
			List<FileItem> fileItems = sfu.parseRequest(req);
			Iterator<FileItem> i = fileItems.iterator();
			while (i.hasNext()) {
				FileItem fi = i.next();
				if (fi.isFormField() == false) {
					fileName = String.valueOf(numberGenerator.nextInt(10000000)) + fi.getName().substring(fi.getName().lastIndexOf("."));
					if (fileName.lastIndexOf("\\") >= 0) {
						file = new File(filePath+"/"+fileName.substring(fileName.lastIndexOf("\\")));
					}
					else {
						file = new File(filePath+"/"+fileName.substring(fileName.lastIndexOf("\\")+1));
					}
					fi.write(file);
				}
			}
			BufferedImage thumbnail = ImageIO.read(file);
			if (thumbnail.getHeight() > 590) {
				String extension = fileName.substring(fileName.indexOf('.')+1);
				thumbnail = Thumbnails.of(file).height(590).asBufferedImage();
				ImageIO.write(thumbnail, extension, file);
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try (Connection c = DriverManager.getConnection(getServletContext().getInitParameter("databaseURL"),getServletContext().getInitParameter("databaseUsername"),getServletContext().getInitParameter("databasePassword"));
			 Statement s = c.createStatement()) {
			
			s.executeUpdate("INSERT INTO userimages (username,imageURL) VALUES ('"+req.getUserPrincipal().getName()+"','"+filePath+"/"+fileName+"')");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			RequestDispatcher rd = req.getRequestDispatcher("profile.jsp");
			rd.forward(req, res);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
