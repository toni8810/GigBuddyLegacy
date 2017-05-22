package org.gigbuddy.events;

import java.io.Serializable;

public class Event implements Serializable {
	private static final long serialVersionUID = -296074357581048867L;
	private String title;
	private String location;
	private java.sql.Date date;
	private String time;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public java.sql.Date getDate() {
		return date;
	}
	public void setDate(java.sql.Date date) {
		this.date = date;
	}

}
