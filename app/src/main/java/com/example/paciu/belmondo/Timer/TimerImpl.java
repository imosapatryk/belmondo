package com.example.paciu.belmondo.Timer;

import android.content.Context;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by paciu on 09.03.2016.
 */
public class TimerImpl implements Timer, TimerListener {

    private TimerThread timerThread;
    private Context context;
    private Set<TimerListener> timerListenerSet;

    public TimerImpl(Context context){
        this.context = context;
        timerListenerSet = new HashSet<>();
    }

    public TimerImpl(Context context, TimerListener timerListener) {
        this(context);
        timerListenerSet = new HashSet<>();
        addTimerListener(timerListener);
    }

    @Override
    public synchronized void start() {
        stop();
        timerThread = new TimerThread(context);
        timerThread.setTimerListener(this);
        timerThread.start();
    }

    @Override
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

    @Override
    public synchronized void newInterval(){
        if(timerThread != null){
            timerThread.newInterval();
        }
    }

    @Override
    public synchronized void startNewIntervalIfNotPaused(){
        if(!isPaused()){
            newInterval();
        }
    }

    @Override
    public synchronized boolean isPaused(){
        return timerThread != null && timerThread.isPaused();
    }

    @Override
    public void addTimerListener(TimerListener timerListener) {
        timerListenerSet.add(timerListener);
    }

    @Override
    public void removeTimerListener(TimerListener timerListener) {
        timerListenerSet.remove(timerListener);
    }

    @Override
    public void notifyTimerListeners(TimerEvent event, TimeParts timeParts, TimeParts interval) {
        switch (event){
            case STARTED:{
                onTimerStarted(timeParts);
                break;
            }
            case STOPPED:{
                onTimerStopped(timeParts, interval);
                break;
            }
            case PAUSED:{
                onTimerPaused(timeParts, interval);
                break;
            }
            case RESUMED:{
                onTimerResumed(timeParts, interval);
                break;
            }
            case MILIS:{
                on100MilisElapsed(timeParts, interval);
                break;
            }
            case SECOND:{
                onSecondElapsed(timeParts, interval);
                break;
            }
            case MINUTE:{
                onMinuteElapsed(timeParts, interval);
                break;
            }
            case HOUR:{
                onHourElapsed(timeParts, interval);
                break;
            }
        }
    }

    @Override
    public void onTimerStarted(TimeParts timeParts) {
        for (TimerListener l: timerListenerSet) {
            if(l != null){
                l.onTimerStarted(timeParts);
            }
        }
    }

    @Override
    public void onTimerStopped(TimeParts totalTimeParts, TimeParts intervalTimeParts) {
        for (TimerListener l: timerListenerSet) {
            if(l != null){
                l.onTimerStopped(totalTimeParts, intervalTimeParts);
            }
        }
    }

    @Override
    public void onTimerPaused(TimeParts totalTimeParts, TimeParts intervalTimeParts) {
        for (TimerListener l: timerListenerSet) {
            if(l != null){
                l.onTimerPaused(totalTimeParts, intervalTimeParts);
            }
        }
    }

    @Override
    public void onTimerResumed(TimeParts totalTimeParts, TimeParts intervalTimeParts) {
        for (TimerListener l: timerListenerSet) {
            if(l != null){
                l.onTimerResumed(totalTimeParts, intervalTimeParts);
            }
        }
    }

    @Override
    public void on100MilisElapsed(TimeParts totalTimeParts, TimeParts intervalTimeParts) {
        for (TimerListener l: timerListenerSet) {
            if(l != null){
                l.on100MilisElapsed(totalTimeParts, intervalTimeParts);
            }
        }
    }

    @Override
    public void onSecondElapsed(TimeParts totalTimeParts, TimeParts intervalTimeParts) {
        for (TimerListener l: timerListenerSet) {
            if(l != null){
                l.onSecondElapsed(totalTimeParts, intervalTimeParts);
            }
        }
    }

    @Override
    public void onMinuteElapsed(TimeParts totalTimeParts, TimeParts intervalTimeParts) {
        for (TimerListener l: timerListenerSet) {
            if(l != null){
                l.onMinuteElapsed(totalTimeParts, intervalTimeParts);
            }
        }
    }

    @Override
    public void onHourElapsed(TimeParts totalTimeParts, TimeParts intervalTimeParts) {
        for (TimerListener l: timerListenerSet) {
            if(l != null){
                l.onHourElapsed(totalTimeParts, intervalTimeParts);
            }
        }
    }

    @Override
    public void onNewIntervalCreated(TimeParts lastTimeInterval) {
        for (TimerListener l: timerListenerSet) {
            if(l != null){
                l.onNewIntervalCreated(lastTimeInterval);
            }
        }
    }
}
