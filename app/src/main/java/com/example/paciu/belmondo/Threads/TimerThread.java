package com.example.paciu.belmondo.Threads;

import android.content.Context;
import android.os.PowerManager;
import android.os.SystemClock;
import android.widget.TextView;

import java.util.List;

/**
 * Created by paciu on 09.03.2016.
 */
public class TimerThread extends Thread {
    private static long IdCount = 0;

    private volatile boolean running = true;
    private volatile boolean paused = false;
    private Context context;

    private boolean started = false;
    private long start = 0;

    private TimeParts timeParts;

    private Timer.OnTimeElapsed onTimeElapsedListener;

    public TimerThread(Context context, TimeParts timeParts) {
        this.context = context;
        this.timeParts = timeParts;
        IdCount++;
    }

    @Override
    public void run() {
        long end;
        long _100MilisTimeElapsedCount = 0;

        while(running){
            PowerManager.WakeLock wakeLock = ((PowerManager)context.getSystemService(Context.POWER_SERVICE)).newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "TimerThread" + IdCount);
            wakeLock.acquire();
            while(!paused){

                if(!started){
                    start = SystemClock.elapsedRealtime();
                    started = true;
                }
                end = SystemClock.elapsedRealtime();

                if(end - start  >= 100){
                    start = SystemClock.elapsedRealtime();

                    _100MilisTimeElapsedCount++;
                    fillTimePartsFromTimeElapsed100MilisCount(_100MilisTimeElapsedCount);

                    callFullFilledCallbacks(_100MilisTimeElapsedCount);
                }
            }
            wakeLock.release();
        }
    }

    protected void callFullFilledCallbacks(long _100MilisTimeElapsedCount){
        _100milisElapsedCallback();
        secondElapsedCallback(_100MilisTimeElapsedCount);
        minuteElapsedCallback(_100MilisTimeElapsedCount);
        hourElapsedCallback(_100MilisTimeElapsedCount);
    }

    protected void _100milisElapsedCallback(){
        if(onTimeElapsedListener != null){
            onTimeElapsedListener.on100MilisElapsed(timeParts);
        }
    }

    protected void secondElapsedCallback(long _100milisTimeElapsedCount){
        if(secondElapsed(_100milisTimeElapsedCount) && onTimeElapsedListener != null){
            onTimeElapsedListener.onSecondElapsed(timeParts);
        }
    }

    protected void minuteElapsedCallback(long _100milisTimeElapsedCount){
        if(minuteElapsed(_100milisTimeElapsedCount) && onTimeElapsedListener != null){
            onTimeElapsedListener.onMinuteElapsed(timeParts);
        }
    }

    protected void hourElapsedCallback(long _100milisTimeElapsedCount){
        if(hourElapsed(_100milisTimeElapsedCount) && onTimeElapsedListener != null){
            onTimeElapsedListener.onHourElapsed(timeParts);
        }
    }

    protected boolean secondElapsed(long _100milisTimeElapsedCount){
        return _100milisTimeElapsedCount % 10 == 0;
    }

    protected boolean minuteElapsed(long _100milisTimeElapsedCount){
        return _100milisTimeElapsedCount % 600 == 0;
    }

    protected boolean hourElapsed(long _100milisTimeElapsedCount){
        return _100milisTimeElapsedCount % 36000 == 0;
    }



    protected void fillTimePartsFromTimeElapsed100MilisCount(long elapsed){
        timeParts.setMilis((int) elapsed % 10);
        timeParts.setSeconds(((int) elapsed / 10) % 60);
        timeParts.setMinutes(((int) elapsed / 600) % 60);
        timeParts.setHours(((int) elapsed / 36000) % 24);
    }


    public void pauseTimerThread(){
        if(!paused){
            paused = true;
        }
    }

    public void resumeTimerThread(){
        if(paused){
            paused = false;
        }
    }

    @Override
    public void interrupt() {
        running = false;
        paused = true;
        super.interrupt();
    }

    public boolean isPaused(){
        return paused;
    }

    public void setOnTimeElapsedListener(Timer.OnTimeElapsed onTimeElapsed) {
        this.onTimeElapsedListener = onTimeElapsed;
    }
}
