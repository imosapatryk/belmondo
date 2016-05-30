package com.example.paciu.belmondo.Utils;

/**
 * Created by paciu on 03.05.2016.
 */
public class DurationUtils {
    public static int getDurationInSeconds(String duration, String format){
        if(format.equals("hh:mm:ss:m")){
            String [] split = duration.split(":");
            int hh = Integer.valueOf(split[0]);
            int mm = Integer.valueOf(split[1]);
            int ss = Integer.valueOf(split[2]);
            return hh*3600 + mm * 60 + ss;
        }
        return 0;
    }
}
