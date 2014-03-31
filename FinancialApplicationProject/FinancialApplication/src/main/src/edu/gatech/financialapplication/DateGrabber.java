package edu.gatech.financialapplication;

import java.util.Calendar;

/**
 * Class that gets and adjusts the formatting for dates.
 * @author Team 15
 */
public class DateGrabber {
     /**
      * Adjusts for the zero-th month.
      */
    private static final int FIRST_MONTH_IS_ZERO_ADJUSTER = 1;
    /**
     * The calendar object.
     */
    private Calendar c;

    /**
     * Public constructor of DateGrabber.
     */
    public DateGrabber() {
        c = Calendar.getInstance();
    }

    /**
     * Creates the date (year down to millisecond).
     * @return the built date
     */
    public String createDate() {
        c = Calendar.getInstance();
        String date = "";
        //CHECKSTYLE:OFF
        date += buildMonth() + "-"; //String literal needed
        //CHECKSTYLE:ON
        date += buildDay() + "-";
        //CHECKSTYLE:OFF
        date += c.get(Calendar.YEAR) + "_"; //String literal needed
        //CHECKSTYLE:ON
        date += c.get(Calendar.HOUR_OF_DAY) + "_";
        date += c.get(Calendar.MINUTE) + "_";
        date += c.get(Calendar.SECOND) + "_";
        date += c.get(Calendar.MILLISECOND);
        return date;
    }
    
    /**
     * Builds the month with the zero adjustment.
     * @return the month as a string.
     */
    public String buildMonth() {
        int month = c.get(Calendar.MONTH) + FIRST_MONTH_IS_ZERO_ADJUSTER;
        String monthString = Integer.toString(month);
        if (month < 10) {
        	//CHECKSTYLE:OFF
            monthString = "0" + month; //String literal needed
            //CHECKSTYLE:ON
        }
        return monthString;
    }

    /**
     * Builds the day.
     * @return the day as a string.
     */
    public String buildDay() {
        int day = c.get(Calendar.DAY_OF_MONTH);
        String dayString = Integer.toString(day);
        if (day < 10) {
            dayString = "0" + day;
        }
        return dayString;
    }
     
    /**
     * Gets the exact time in milliseconds.
     * @return the current time in milliseconds as a string
     */
    public String createDateMS() {
        return Long.toString(System.currentTimeMillis());
    }
}
