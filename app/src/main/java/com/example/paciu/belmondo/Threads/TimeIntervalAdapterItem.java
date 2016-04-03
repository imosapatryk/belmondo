package com.example.paciu.belmondo.Threads;

/**
 * Created by paciu on 12.03.2016.
 */
public class TimeIntervalAdapterItem {

    private int number;
    private String time;
    private boolean animate;

    public TimeIntervalAdapterItem(int number, String time) {
        this.number = number;
        this.time = time;
        this.animate = true;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean ifAnimate() {
        return animate;
    }

    public void setAnimate(boolean animate) {
        this.animate = animate;
    }
}
