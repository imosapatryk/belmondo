package com.example.paciu.belmondo.Timer;

/**
 * Created by paciu on 14.05.2016.
 */
public interface Playable {
    void start();
    void stop();
    void pause();
    void resume();
    boolean isPaused();
}
