package org.gigbuddy.startup;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServlet;

import org.gigbuddy.events.Event;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;


public class EventScraper extends HttpServlet {
	private static final long serialVersionUID = -7949675392030436183L;
	List<WebElement> events;
	List<Event> eventList = new ArrayList<>();
	WebElement temp;
	Event event;
	public void init() {
		long repeat = (1000*60)*60*24;
		Timer timer = new Timer();
		TimerTask tt = new TimerTask() {

			@Override
			public void run() {
				eventScraper();
			}
			
		};
		timer.schedule(tt, 0, repeat);
		
	}
	private java.sql.Date normalizeDate(String date) {
		//Saturday 22nd October at 7:30 PM
		//Desired format Saturday 22nd October
		String currentYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		StringBuffer sb = new StringBuffer(date.replaceAll("\n", ""));
		int indexOfTH = sb.toString().indexOf("th ");
		if (indexOfTH < 0) {
			indexOfTH = sb.toString().indexOf("st ");
			if (indexOfTH < 0) {
				indexOfTH = sb.toString().indexOf("nd ");
				if (indexOfTH < 0) {
					indexOfTH = sb.toString().indexOf("rd ");
				}
			}
		}
		sb.delete(indexOfTH, indexOfTH+2);
		//Saturday 22 October at 7:30 PM
		//deleting everything after at
		sb.delete(sb.indexOf(" at "), sb.length());
		//append current year
		sb.append(" "+currentYear);
		SimpleDateFormat sdf = new SimpleDateFormat("E dd MMM yyyy");
		java.sql.Date dateSqlDate = null;
		Calendar c = Calendar.getInstance();
		try {
			Date dateDate = sdf.parse(sb.toString());
			c.add(Calendar.MONTH, -1);
			if (dateDate.before(c.getTime())) {
				c.setTime(dateDate);
				c.add(Calendar.YEAR, 1);
				dateDate.setTime(c.getTime().getTime());
			}
			dateSqlDate = new java.sql.Date(dateDate.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dateSqlDate;
		}
	private void eventScraper() {
		eventList.clear();
		HtmlUnitDriver driver = new HtmlUnitDriver(false);
		driver.get("http://www.gigsandtours.com/search?browseorder=soonest&distance=0&availableonly=False&showfavourites=True&se=True&pageSize=30&pageIndex=1");
		//Getting number of pages
		String pageNum = driver.findElement(By.xpath(".//*[@id='search-results']/nav/p")).getText().trim();
		//The last two number is the actual page number
		pageNum = pageNum.substring(pageNum.length()-2).trim();
		int pageNumInt = Integer.parseInt(pageNum);
		//looping through all the pages
		for (int i=2; i<=pageNumInt+1; i++) {
			//getting the number of events
			temp = driver.findElement(By.id("search-results"));
			events = temp.findElements(By.tagName("article"));
			//looping through events
			for (int k=0; k<events.size(); k++) {
				try {
					temp = events.get(k).findElement(By.xpath("div[1]"));
					event = new Event();
					event.setTitle(temp.findElement(By.xpath("h3")).getText());
					//double quote to escape it in sql
					event.setTitle(event.getTitle().replaceAll("'", "''"));
					event.setLocation(temp.findElement(By.xpath("p[1]")).getText());
					event.setLocation(event.getLocation().replaceAll("'", "''"));
					event.setTime(temp.findElement(By.xpath("p[2]")).getText().trim());
					event.setDate(normalizeDate(event.getTime()));
					event.setTime(event.getTime().substring(event.getTime().indexOf(" at ")+4, event.getTime().length()));
					eventList.add(event);
				}
				catch (NoSuchElementException nsee) {
					System.out.println("Skipped one");
				}
			}
			driver.get("http://www.gigsandtours.com/search?browseorder=soonest&distance=0&availableonly=False&showfavourites=True&se=True&pageSize=30&pageIndex="+i);
		}
		driver.close();
		System.out.println("Starting wrintinh data into database");
		Calendar cToday = Calendar.getInstance();
		Calendar cSelectedDate = Calendar.getInstance();
		java.sql.Date dSelectedDate;
		String log = "";
		try (Connection c = DriverManager.getConnection(getServletContext().getInitParameter("databaseURL"),getServletContext().getInitParameter("databaseUsername"),getServletContext().getInitParameter("databasePassword"));
			 Statement s = c.createStatement();
			 Statement sQuery = c.createStatement();
			 ResultSet rs = sQuery.executeQuery("SELECT * FROM eventsbyusers")) {
			
			while(rs.next()) {
				//Deleting dates from eventsbyusers that are sooner then current date
				dSelectedDate = rs.getDate("date");
				cSelectedDate.setTime(dSelectedDate);
				if (cSelectedDate.before(cToday)) {
					s.executeUpdate("DELETE FROM eventsbyusers WHERE date = '"+rs.getString("date")+"'");
				} 
			}
			
			s.executeUpdate("TRUNCATE TABLE events");
			
			for (int i=0; i<eventList.size(); i++) {
				try {
					s.executeUpdate("INSERT INTO events (title,location,date,time) VALUES ('"+eventList.get(i).getTitle()+"','"+eventList.get(i).getLocation()+"','"+eventList.get(i).getDate()+"','"+eventList.get(i).getTime()+"')");
				}
				catch (MySQLIntegrityConstraintViolationException micve) {
					
				}
			}
			ResultSet rsNew = sQuery.executeQuery("SELECT * FROM eventsbyusers");
			while(rsNew.next()) {
				try {
					s.executeUpdate("INSERT INTO events (title,location,date,time) VALUES ('"+rsNew.getString("title")+"','"+rsNew.getString("location")+"','"+rsNew.getString("date")+"','"+rsNew.getString("time")+"')");
				}
				catch (MySQLIntegrityConstraintViolationException micve) {
					System.out.println("Duplicate Entry");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log = e.getMessage();
		}
		System.out.println("Events table updated succesfully");
		
		try {
			File eventScraperLog = new File("/home/gigbud5/public_html/eventScraperLog.txt");
			eventScraperLog.createNewFile();
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date currentDate = new Date();
			BufferedWriter bw = new BufferedWriter(new FileWriter(eventScraperLog));
			if (log.isEmpty()) bw.write("Events table updated successfully" +dateFormat.format(currentDate));
			else bw.write(log);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
