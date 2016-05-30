package com.example.paciu.belmondo.Utils;

import android.graphics.Color;

/**
 * Created by paciu on 20.04.2016.
 */
public class ColorUtils {
    public static int changeAlpha(int alpha, int color){
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);

        return Color.argb(alpha, r, g, b);
    }
}
