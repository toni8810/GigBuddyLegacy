package org.gigbuddy.registration;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
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

public class ProfileImageSetter extends HttpServlet {
	private static final long serialVersionUID = -2394999929962853369L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		String email = req.getParameter("email");
		String id = req.getParameter("id");
		
		try (Connection c = DriverManager.getConnection(getServletContext().getInitParameter("databaseURL")+"?rewriteBatchedStatements=true",getServletContext().getInitParameter("databaseUsername"),getServletContext().getInitParameter("databasePassword"));
			 PreparedStatement sQuery = c.prepareStatement("SELECT mainImageURL,userId FROM users WHERE username = ?")) {
			
			sQuery.setString(1, email);
			ResultSet rs = sQuery.executeQuery();
			rs.first();
			
			//If profile picture has not been updated it has not been downloaded
			if (rs.getString("mainImageURL").contentEquals("/images/defaultProfileImage.jpg")) {
				String path = downloadImage(id, String.valueOf(rs.getInt("userId")));
				if (path != null) {
					//Updating profile picture
					PreparedStatement s = c.prepareStatement("UPDATE users SET mainImageURL = ? WHERE username = ?");
					s.setString(1, path);
					s.setString(2, email);
					s.executeUpdate();
					s.close();
					PreparedStatement sInsertIntoUserImages = c.prepareStatement("INSERT INTO userimages (username,imageURL) VALUES(?,?)");
					sInsertIntoUserImages.setString(1, email);
					sInsertIntoUserImages.setString(2, path);
					sInsertIntoUserImages.executeUpdate();
					sInsertIntoUserImages.close();
				}
			}
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private String downloadImage(String id, String userId) {
		String downloadUrl = "";
		try {
			URL url = new URL("https://graph.facebook.com/"+id+"/picture?width=9999&height=9999&redirect=false");
			URLConnection urlC = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
	                urlC.getInputStream(), "UTF-8"));
			String inputLine;
	        while ((inputLine = in.readLine()) != null) {
	        	if (inputLine.contains("\"is_silhouette\":true")) return null;
	        	if (inputLine.contains("\"url\":")) {
	        		downloadUrl = inputLine.substring(inputLine.indexOf("url\":")+6);
	        		downloadUrl = downloadUrl.substring(0, downloadUrl.indexOf('"'));
	        		downloadUrl = downloadUrl.replace("\\", "");
	        	}
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String extension;
		if (downloadUrl.indexOf('?') > 0) {
			extension = downloadUrl.substring(downloadUrl.indexOf('?')-5,downloadUrl.indexOf('?'));
		}
		else {
			extension = downloadUrl.substring(downloadUrl.length()-5);
			extension = extension.substring(extension.indexOf('.')+1);
		}
		//Production: /home/gigbud5/public_html/userimages/
		//Test: c:/images/
		final String IMAGE_PATH = getServletContext().getInitParameter("imagePath")+userId+"/";
		final String FILE_NAME_WITHOUT_EXTENSION = String.valueOf(new Random().nextInt(1000000));
		extension = extension.substring(extension.indexOf('.')+1);
		try {
			URL url = new URL(downloadUrl);
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
