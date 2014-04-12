package edu.gatech.financialapplication;

import java.util.Calendar;

/**
 * Class that gets and adjusts the formatting for dates.
 * @author Team 15
 */
public class DateGrabber {
 
	private static final int FIRST_MONTH_IS_ZERO_ADJUSTER = 1;
    private Calendar c;

    /**
     * Public constructor of DateGrabber.
     */
    public DateGrabber() {
        c = Calendar.getInstance();
    }

    /**
     * Creates the date.
     * @return the built date
     */
    public String createDate() {
        String date = "";
        date += buildMonth();
        date += buildDay();
        date += c.get(Calendar.YEAR);
        return date;
    }
    
    /**
     * Builds the month with the zero adjustment.
     * @return the month as a string.
     */
    private String buildMonth() {
        int month = c.get(Calendar.MONTH) + FIRST_MONTH_IS_ZERO_ADJUSTER;
        String monthString = Integer.toString(month);
        if (month < 10) {
            monthString = "0" + month;
        }
        return monthString;
    }

    /**
     * Builds the day.
     * @return the day as a string.
     */
    private String buildDay() {
        int day = c.get(Calendar.DAY_OF_MONTH);
        String dayString = Integer.toString(day);
        if (day < 10) {
            dayString = "0" + day;
        }
        return dayString;
    }
}
