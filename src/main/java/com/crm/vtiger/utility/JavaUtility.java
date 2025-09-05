package com.crm.vtiger.utility;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JavaUtility {
	public static int getRandomNumber() {
		int random = (int)(Math.random()*999);
		return random;
	}
	public String getCurrentDateTime() {
		
		LocalDateTime now = LocalDateTime.now();
		//format 
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMhh_HHmmss");
		String time = now.format(dtf);
		return time;
	}

}
