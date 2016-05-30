package com.example.paciu.belmondo.Utils;

import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public static int getAge(String date, String format){
        try{
            Date d = parseDateWithFormat(date, format);
            Date now = new Date();
            long timeBetween = now.getTime() - d.getTime();
            double yearsBetween = timeBetween / 3.156e+10;
            return (int)Math.floor(yearsBetween);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
