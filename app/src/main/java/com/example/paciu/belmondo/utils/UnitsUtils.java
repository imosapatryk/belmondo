package com.example.paciu.belmondo.utils;

import android.content.Context;

/**
 * Created by paciu on 15.03.2016.
 */
public class UnitsUtils {

    public static int dpToInt(Context context, int dp){
        return Math.round(context.getResources().getDisplayMetrics().density * dp);
    }
}
