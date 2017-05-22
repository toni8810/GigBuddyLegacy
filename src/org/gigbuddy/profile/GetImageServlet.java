package org.gigbuddy.profile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetImageServlet extends HttpServlet {
	private static final long serialVersionUID = -5177719732151425683L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String fileName = req.getParameter("fileName");
        if(fileName == null || fileName.equals("")){
            throw new ServletException("File Name can't be null or empty");
        }
        File file = new File(fileName);
        System.out.println("----- "+file.getAbsolutePath());
        if(!file.exists()){
            throw new ServletException("File doesn't exists on server.");
        }
        System.out.println("File location on server::"+file.getAbsolutePath());
        InputStream fis = new FileInputStream(file);
        res.setContentType("image/jpeg");  
        res.setContentLength((int) file.length());
         
        ServletOutputStream os = res.getOutputStream();
        byte[] bufferData = new byte[1024];
        int read=0;
        while((read = fis.read(bufferData))!= -1){
            os.write(bufferData, 0, read);
        }
        os.flush();
        os.close();
        fis.close();
        System.out.println("File downloaded at client successfully");
	}

}
