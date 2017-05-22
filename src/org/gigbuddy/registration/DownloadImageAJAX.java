package org.gigbuddy.registration;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.coobird.thumbnailator.Thumbnails;

public class DownloadImageAJAX extends HttpServlet {
	private static final long serialVersionUID = 5397078148302172760L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String urlString = req.getParameter("url");
		String email = req.getParameter("email");
		boolean isProfileImage = Boolean.parseBoolean(req.getParameter("isProfileImage"));
		imageUrlIntoDatabase(email,urlString,isProfileImage);
		
	}
	private String downloadImage(String urlString, String userId) throws IOException {
		String extension;
		if (urlString.indexOf('?') > 0) {
			extension = urlString.substring(urlString.indexOf('?')-5,urlString.indexOf('?'));
		}
		else {
			extension = urlString.substring(urlString.length()-5);
			extension = extension.substring(extension.indexOf('.')+1);
		}
		//Production: /home/gigbud5/public_html/userimages/
		//Test: c:/images/
		final String IMAGE_PATH = getServletContext().getInitParameter("imagePath")+userId+"/";
		final String FILE_NAME_WITHOUT_EXTENSION = String.valueOf(new Random().nextInt(1000000));
		extension = extension.substring(extension.indexOf('.')+1);
		URL url = new URL(urlString);
		InputStream in = new BufferedInputStream(url.openStream());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int n = 0;
		while (-1!=(n=in.read(buf)))
		{
			out.write(buf, 0, n);
		}
		out.close();
		in.close();
		(new File(IMAGE_PATH)).mkdir();
		final File image = new File(IMAGE_PATH+FILE_NAME_WITHOUT_EXTENSION+"."+extension);
		byte[] response = out.toByteArray();
		FileOutputStream fos = new FileOutputStream(image);
		fos.write(response);
		fos.close();
		BufferedImage thumbnail = ImageIO.read(image);
		if (thumbnail.getHeight() > 590) {
			thumbnail = Thumbnails.of(image).height(590).asBufferedImage();
			ImageIO.write(thumbnail, extension, image);
		}
		return image.getAbsolutePath();
	}
	private void imageUrlIntoDatabase(String email, String urlString, boolean isProfileImage) throws IOException {
		String path;
		try (Connection c = DriverManager.getConnection(getServletContext().getInitParameter("databaseURL")+"?rewriteBatchedStatements=true",getServletContext().getInitParameter("databaseUsername"),getServletContext().getInitParameter("databasePassword"));
			 PreparedStatement s = c.prepareStatement("INSERT INTO userimages (username,imageURL) VALUES (?,?)");
			 PreparedStatement sQuery = c.prepareStatement("SELECT userId FROM users WHERE username = ?")) {
				 
			sQuery.setString(1, email);
			ResultSet rs = sQuery.executeQuery();
			rs.first();
			path = downloadImage(urlString, String.valueOf(rs.getInt("userId")));
			rs.close();
			
			s.setString(1, email);
			s.setString(2, path);
			s.executeUpdate();
			if (isProfileImage) {
				System.out.println("Profile Image found!!!!!!!!!!!");
				System.out.println(urlString);
				PreparedStatement updateMainImageUrl = c.prepareStatement("UPDATE users SET mainImageURL = ? WHERE username = ?");
				updateMainImageUrl.setString(1, path);
				updateMainImageUrl.setString(2, email);
				updateMainImageUrl.executeUpdate();
				updateMainImageUrl.close();
				System.out.println(path+" updated");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
