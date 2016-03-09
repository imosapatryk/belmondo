package com.example.paciu.belmondo.Threads;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by paciu on 09.03.2016.
 */
public class Timer {

    private TimeParts timeParts;

    private TimerThread timerThread;

    private Context context;

    private OnTimeElapsed onTimeElapsedListener;

    public Timer(Context context) {
        this.context = context;
    }


    public TimeParts getTimeParts() {
        return timeParts;
    }

    public void start() {
        if (timerThread != null)
        {
            timerThread.interrupt();
            try {
                timerThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        timeParts = new TimeParts(0, 0, 0, 0);
        timerThread = new TimerThread(context, timeParts);
        timerThread.setOnTimeElapsedListener(onTimeElapsedListener);
        timerThread.start();

    }

    public void stop(){
        if(timerThread != null && timerThread.isAlive()){
            timerThread.interrupt();
        }
    }

    public void pause(){
        if(timerThread != null && timerThread.isAlive()){
            timerThread.pauseTimerThread();
        }
    }

    public void resume(){
        if(timerThread != null && timerThread.isPaused()){
            timerThread.resumeTimerThread();
        }
    }

    public void setOnTimeElapsedListener(OnTimeElapsed onTimeElapsedListener) {
        this.onTimeElapsedListener = onTimeElapsedListener;
        if(timerThread != null){
            timerThread.setOnTimeElapsedListener(onTimeElapsedListener);
        }
    }

    public interface OnTimeElapsed{
        void on100MilisElapsed(TimeParts timeParts);

        void onSecondElapsed(TimeParts timeParts);

        void onMinuteElapsed(TimeParts timeParts);

        void onHourElapsed(TimeParts timeParts);
    }

}
