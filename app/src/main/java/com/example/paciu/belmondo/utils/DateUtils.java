package com.example.paciu.belmondo.utils;

import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by paciu on 06.03.2016.
 */
public class DateUtils {
    public static boolean isDateBeforeNow(Date date){
        Date now = new Date();
        return date.before(now);
    }

    public static Date parseDateWithFormat(String dateString, String format) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.parse(dateString);
    }
}
