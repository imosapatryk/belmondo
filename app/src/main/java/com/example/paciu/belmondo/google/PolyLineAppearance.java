package com.example.paciu.belmondo.google;

import android.graphics.Color;

/**
 * Created by paciu on 01.04.2016.
 */
public class PolyLineAppearance {

    private int lineColor = Color.parseColor("#CCf6700a");
    private float lineWidth = 25;

    public void setLineColor(int color){
        this.lineColor = color;
    }

    public int getLineColor(){
        return lineColor;
    }

    public float getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
    }
}
