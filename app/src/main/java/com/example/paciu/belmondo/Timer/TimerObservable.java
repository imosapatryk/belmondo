package com.example.paciu.belmondo.Timer;

/**
 * Created by paciu on 12.05.2016.
 */
public interface TimerObservable {
    void addTimerListener(TimerListener timerListener);
    void removeTimerListener(TimerListener timerListener);
    void notifyTimerListeners(TimerEvent event, TimeParts timeParts, TimeParts interval);
}
