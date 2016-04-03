package com.example.paciu.belmondo.Threads;

import java.util.Objects;

/**
 * Created by paciu on 10.03.2016.
 */
public class ThreadAwaitSignal {
    private final Object lock = new Object();

    public void await() throws InterruptedException {
        synchronized (lock){
            lock.wait();
        }
    }

    public void signal() throws InterruptedException{
        synchronized (lock){
            lock.notify();
        }
    }
}

