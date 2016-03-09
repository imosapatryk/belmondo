package com.example.paciu.belmondo.Threads;

/**
 * Created by paciu on 09.03.2016.
 */
public class TimeParts {

    private int milis;
    private int seconds;
    private int minutes;
    private int hours;

    public TimeParts(int milis, int seconds, int minutes, int hours) {
        this.milis = milis;
        this.seconds = seconds;
        this.minutes = minutes;
        this.hours = hours;
    }

    public int getMilis() {
        return milis;
    }

    public void setMilis(int milis) {
        this.milis = milis;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    @Override
    public String toString() {
        return  getTimeStringWithoutMilis()  +
                ":" + milis;
    }

    public String getTimeStringWithoutMilis(){
        return hours +
                ":" + (minutes < 10 ? 0 : "") + minutes +
                ":" + (seconds < 10 ? 0 : "") + seconds;
    }

    public String getMilisString(){
        return String.valueOf(milis);
    }
}
