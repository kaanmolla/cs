package com.tradeservice.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtils {

    private static final DateUtils instance = new DateUtils();

    private DateUtils() {
    }

    public static DateUtils getInstance() {
        return instance;
    }

    public boolean isBefore(String first, String second) {
        return toDate(first).before(toDate(second));
    }

    public boolean isWeekend(String date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(toDate(date));
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek == 1 || dayOfWeek == 7;
    }

    private Date toDate(String date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

        return new Date();
    }
}
