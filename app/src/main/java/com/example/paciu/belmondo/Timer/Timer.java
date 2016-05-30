package com.example.paciu.belmondo.Timer;

/**
 * Created by paciu on 12.05.2016.
 */
public interface Timer extends Playable, TimerObservable {
    void newInterval();
    void startNewIntervalIfNotPaused();
}
