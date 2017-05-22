package org.gigbuddy.messaging;

import java.sql.Date;

public class ELFunctions {
	public static Date getDate() {
		Date date = new Date(new java.util.Date().getTime());
		return date;
	}
	public static String substring(String string, Integer position1, String position2) {
		string = string.substring(position1, string.indexOf(position2));
		return string;
	}
}
