package org.gigbuddy.tshirt.promotion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class EmailTemplateCreator {
	public static String createTemplate(String text, String htmlFilePath, int id, boolean buttonNeeded) {
		File emailTemplate = new File(htmlFilePath);
		String template = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(emailTemplate));
			String temp;
			while ((temp = br.readLine()) != null) {
				if (temp.contains("*replaceMe*")) {
					temp = temp.trim();
					temp = temp.replace("*replaceMe*", "<h2>"+text+"</h2>");
				}
				if (buttonNeeded == false) {
					if (temp.contains("text-transform: uppercase;\" target=\"_blank\"><span>Claim T-Shirt</span>!</a>")) {
						temp = temp.replace("text-transform: uppercase;", "display:none;");
					}
				}
				else {
					if (temp.contains("id=\"claimTshirtButton\"")) {
						temp = temp.replace("href=\"#\"", "href=\"http://gigbuddy.org/eligibilityCheck.do?id="+id+"\"");
					}
				}
				
				template += temp + "\n";
			}
			
			br.close();
		}
		catch (IOException io) {
			io.printStackTrace();
		}
		return template;
	}
}
