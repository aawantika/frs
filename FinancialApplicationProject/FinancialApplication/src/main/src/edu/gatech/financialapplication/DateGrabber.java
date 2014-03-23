package edu.gatech.financialapplication;

import java.util.Calendar;

public class DateGrabber {
	private static final int FIRST_MONTH_IS_ZERO_ADJUSTER = 1;
	private Calendar c;

	public DateGrabber() {
		c = Calendar.getInstance();
	}

	public String createDate() {
		c = Calendar.getInstance();
		String date = "";
		date += buildMonth() + "-";
		date += buildDay() + "-";
		date += c.get(Calendar.YEAR) + "_";
		date += c.get(Calendar.HOUR_OF_DAY) + "_";
		date += c.get(Calendar.MINUTE) + "_";
		date += c.get(Calendar.SECOND) + "_";
		date += c.get(Calendar.MILLISECOND);
		return date;
	}
	
	public String buildMonth() {
		int month = c.get(Calendar.MONTH) + FIRST_MONTH_IS_ZERO_ADJUSTER;
		String monthString = Integer.toString(month);
		if (month < 10) {
			monthString = "0" + month;
		}
		return monthString;
	}
	
	public String buildDay() {
		int day = c.get(Calendar.DAY_OF_MONTH);
		String dayString = Integer.toString(day);
		if (day < 10) {
			dayString = "0" + day;
		}
		return dayString;
	}
	
	public String createDateMS() {
		return Long.toString(System.currentTimeMillis());
	}
}
