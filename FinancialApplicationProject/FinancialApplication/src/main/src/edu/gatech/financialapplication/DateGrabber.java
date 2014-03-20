package edu.gatech.financialapplication;

import java.util.Calendar;

public class DateGrabber {
	Calendar c;
	private static final int FIRST_MONTH_IS_ZERO_ADJUSTER = 1;
	public DateGrabber() {
		c = Calendar.getInstance();
	}
	
	public String createDate() {
		c = Calendar.getInstance();
		String date = "";
		date += (c.get(Calendar.MONTH) + FIRST_MONTH_IS_ZERO_ADJUSTER) + "-";
		date += c.get(Calendar.DAY_OF_MONTH)+ "-";
		date += c.get(Calendar.YEAR) + "_";
		date += c.get(Calendar.HOUR_OF_DAY) + "_";
		date += c.get(Calendar.MINUTE) + "_";
		date += c.get(Calendar.SECOND) + "_";
		date += c.get(Calendar.MILLISECOND); 
		return date;
	}
	
	public String createDateMS() {
		return Long.toString(System.currentTimeMillis());
	}
	
	public static void main(String[] args) {
		DateGrabber g = new DateGrabber();
		System.out.print(g.createDate());
	}
}
