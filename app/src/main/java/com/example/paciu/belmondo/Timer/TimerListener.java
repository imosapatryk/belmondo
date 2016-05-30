package com.example.paciu.belmondo.Timer;

/**
 * Created by paciu on 12.05.2016.
 */
public interface TimerListener {
    void onTimerStarted(TimeParts timeParts);
    void onTimerStopped(TimeParts totalTimeParts, TimeParts intervalTimeParts);
    void onTimerPaused(TimeParts totalTimeParts, TimeParts intervalTimeParts);
    void onTimerResumed(TimeParts totalTimeParts, TimeParts intervalTimeParts);

    void on100MilisElapsed(TimeParts totalTimeParts, TimeParts intervalTimeParts);
    void onSecondElapsed(TimeParts totalTimeParts, TimeParts intervalTimeParts);
    void onMinuteElapsed(TimeParts totalTimeParts, TimeParts intervalTimeParts);
    void onHourElapsed(TimeParts totalTimeParts, TimeParts intervalTimeParts);
    void onNewIntervalCreated(TimeParts lastTimeInterval);
}
