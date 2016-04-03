package com.example.paciu.belmondo.Threads;

import android.content.Context;

import com.example.paciu.belmondo.ViewsExtends.IStartPauseResume;

/**
 * Created by paciu on 09.03.2016.
 */
public class Timer implements IStartPauseResume {

    private TimerThread timerThread;

    private Context context;

    private OnTimeElapsed onTimeElapsedListener;
    private OnTimerStateChanged onTimerStateChangedListener;

    public Timer(Context context){
        this.context = context;
    }

    public Timer(Context context, OnTimeElapsed onTimeElapsedListener, OnTimerStateChanged onTimerStateChangedListener) {
        this(context);
        this.setOnTimeElapsedListener(onTimeElapsedListener);
        this.setOnTimerStateChangedListener(onTimerStateChangedListener);
    }

    @Override
    public synchronized void start() {
        stop();

        timerThread = new TimerThread(context);
        setOnTimeElapsedListener(onTimeElapsedListener);
        setOnTimerStateChangedListener(onTimerStateChangedListener);

        timerThread.start();
    }

    public synchronized void stop(){
        if(timerThread != null){
            timerThread.interrupt();
            try {
                timerThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public synchronized void pause(){
        if(timerThread != null){
            timerThread.pauseTimerThread();
        }
    }

    @Override
    public synchronized void resume(){
        if(timerThread != null && timerThread.isPaused()){
            timerThread.resumeTimerThread();
        }
    }

    public synchronized void newInterval(){
        if(timerThread != null){
            timerThread.newInterval();
        }
    }

    public synchronized void startNewIntervalIfNotPaused(){
        if(!isPaused()){
            newInterval();
        }
    }

    public void setOnTimeElapsedListener(OnTimeElapsed onTimeElapsedListener) {
        this.onTimeElapsedListener = onTimeElapsedListener;
        if(timerThread != null){
            timerThread.setOnTimeElapsedListener(onTimeElapsedListener);
        }
    }

    public void setOnTimerStateChangedListener(OnTimerStateChanged onTimerStateChangedListener) {
        this.onTimerStateChangedListener = onTimerStateChangedListener;
        if(timerThread != null){
            timerThread.setOnTimerStateChangedListener(onTimerStateChangedListener);
        }
    }

    @Override
    public synchronized boolean isPaused(){
        return timerThread != null && timerThread.isPaused();
    }

    public interface OnTimeElapsed{
        void on100MilisElapsed(TimeParts totalTimeParts, TimeParts intervalTimeParts);
        void onSecondElapsed(TimeParts totalTimeParts, TimeParts intervalTimeParts);
        void onMinuteElapsed(TimeParts totalTimeParts, TimeParts intervalTimeParts);
        void onHourElapsed(TimeParts totalTimeParts, TimeParts intervalTimeParts);
        void onNewIntervalCreated(TimeParts lastTimeInterval);
    }

    public interface OnTimerStateChanged{
        void onTimerStarted(TimeParts timeParts);
        void onTimerStopped(TimeParts totalTimeParts, TimeParts intervalTimeParts);
        void onTimerPaused(TimeParts totalTimeParts, TimeParts intervalTimeParts);
        void onTimerResumed(TimeParts totalTimeParts, TimeParts intervalTimeParts);
    }

}
