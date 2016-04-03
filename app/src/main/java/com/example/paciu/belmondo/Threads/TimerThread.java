package com.example.paciu.belmondo.Threads;

import android.content.Context;
import android.os.PowerManager;
import android.os.SystemClock;

/**
 * Created by paciu on 09.03.2016.
 */
public class TimerThread extends Thread {

    private static long IdCount = 0;


    private volatile boolean runningGeneralLooop = true;
    private volatile boolean paused = false;
    private volatile boolean signalFromInterrupt = false;

    private Context context;

    private Object pauseSynchronizer = new Object();
    private Object awaitingPausedSynchronizer = new Object();
    private Object methodSynchronizer = new Object();

    private TimeParts totalTimeParts;


    private TimeParts intervalTimeParts;
    private boolean createNewInterval;
    private Object intervalChangeMonitor = new Object();

    private Timer.OnTimeElapsed onTimeElapsedListener;
    private Timer.OnTimerStateChanged onTimerStateChangedListener;

    public TimerThread(Context context) {
        this.context = context;
        this.totalTimeParts = new TimeParts(0, 0, 0, 0);
        this.intervalTimeParts = new TimeParts(0, 0, 0, 0);

        this.createNewInterval = false;
    }

    @Override
    public void run() {
        callOnTimerStartCallback();

        long start = 0, end = 0;
        long _100MilisTimeElapsedCount = 0;
        long _100MilisTimeWhenNewIntervalCreated = 0;

        while (isRunningGeneralLooop()) {
            PowerManager.WakeLock wakeLock = ((PowerManager) context.getSystemService(Context.POWER_SERVICE)).newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "TimerThread");
            wakeLock.acquire();

            start = SystemClock.elapsedRealtime();

            while (!isPaused()) {
                if (end - start >= 100) {
                    start = SystemClock.elapsedRealtime();

                    _100MilisTimeElapsedCount++;
                    _100MilisTimeWhenNewIntervalCreated++;

                    fillTimePartsFromTimeElapsed100MilisCount(_100MilisTimeElapsedCount, _100MilisTimeWhenNewIntervalCreated);
                    callFullFilledOnTimeElapsedCallbacks(_100MilisTimeElapsedCount);
                }

                synchronized (intervalChangeMonitor){
                    if (createNewInterval) {
                        callOnNewIntervalCreated(_100MilisTimeWhenNewIntervalCreated);
                        _100MilisTimeWhenNewIntervalCreated = 0;
                        createNewInterval = false;
                    }
                }
                end = SystemClock.elapsedRealtime();
            }
            wakeLock.release();

            if (!isSignalFromInterrupt() && isPaused()) {
                synchronized (awaitingPausedSynchronizer) {
                    synchronized (pauseSynchronizer) {
                        callOnTimerPausedCallback();
                        pauseSynchronizer.notify();
                    }
                    try {
                        awaitingPausedSynchronizer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        callOnTimerStoppedCallback();
    }

    protected void callOnTimerStartCallback() {
        if (onTimerStateChangedListener != null)
            onTimerStateChangedListener.onTimerStarted(totalTimeParts);
    }

    protected void callOnTimerStoppedCallback() {
        if (onTimerStateChangedListener != null)
            onTimerStateChangedListener.onTimerStopped(totalTimeParts, intervalTimeParts);
    }

    public void pauseTimerThread() {
        synchronized (methodSynchronizer) {
            synchronized (pauseSynchronizer) {
                if (!isPaused() && isRunningGeneralLooop()) {
                    setPaused(true);
                    try {
                        pauseSynchronizer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void resumeTimerThread() {
        synchronized (methodSynchronizer) {
            synchronized (awaitingPausedSynchronizer) {
                if (isPaused() && isRunningGeneralLooop()) {
                    setPaused(false);
                    awaitingPausedSynchronizer.notify();
                }
            }
        }
    }


    @Override
    public void interrupt() {
        synchronized (methodSynchronizer) {
            setRunningGeneralLooop(false);
            setSignalFromInterrupt(true);
            setPaused(true);
            synchronized (awaitingPausedSynchronizer) {
                awaitingPausedSynchronizer.notify();
            }
        }
    }

    private synchronized boolean isRunningGeneralLooop() {
        return runningGeneralLooop;
    }

    private synchronized void setRunningGeneralLooop(boolean runningGeneralLooop) {
        this.runningGeneralLooop = runningGeneralLooop;
    }


    public synchronized boolean isSignalFromInterrupt() {
        return signalFromInterrupt;
    }

    public synchronized void setSignalFromInterrupt(boolean signalFromInterrupt) {
        this.signalFromInterrupt = signalFromInterrupt;
    }

    public synchronized boolean isPaused() {
        return paused;
    }

    private synchronized void setPaused(boolean paused) {
        this.paused = paused;
    }

    protected void callOnNewIntervalCreated(long _100MilisTimeIntervalCount) {
        TimeParts oldTimeInterval = new TimeParts(_100MilisTimeIntervalCount);

        if (onTimeElapsedListener != null) {
            onTimeElapsedListener.onNewIntervalCreated(oldTimeInterval);
        }
    }

    protected void callFullFilledOnTimeElapsedCallbacks(long _100MilisTimeElapsedCount) {
        _100milisElapsedCallback();
        secondElapsedCallback(_100MilisTimeElapsedCount);
        minuteElapsedCallback(_100MilisTimeElapsedCount);
        hourElapsedCallback(_100MilisTimeElapsedCount);
    }

    protected void _100milisElapsedCallback() {
        if (onTimeElapsedListener != null) {
            onTimeElapsedListener.on100MilisElapsed(totalTimeParts, intervalTimeParts);
        }
    }

    protected void secondElapsedCallback(long _100milisTimeElapsedCount) {
        if (secondElapsed(_100milisTimeElapsedCount) && onTimeElapsedListener != null) {
            onTimeElapsedListener.onSecondElapsed(totalTimeParts, intervalTimeParts);
        }
    }

    protected void minuteElapsedCallback(long _100milisTimeElapsedCount) {
        if (minuteElapsed(_100milisTimeElapsedCount) && onTimeElapsedListener != null) {
            onTimeElapsedListener.onMinuteElapsed(totalTimeParts, intervalTimeParts);
        }
    }

    protected void hourElapsedCallback(long _100milisTimeElapsedCount) {
        if (hourElapsed(_100milisTimeElapsedCount) && onTimeElapsedListener != null) {
            onTimeElapsedListener.onHourElapsed(totalTimeParts, intervalTimeParts);
        }
    }

    protected boolean secondElapsed(long _100milisTimeElapsedCount) {
        return _100milisTimeElapsedCount % 10 == 0;
    }

    protected boolean minuteElapsed(long _100milisTimeElapsedCount) {
        return _100milisTimeElapsedCount % 600 == 0;
    }

    protected boolean hourElapsed(long _100milisTimeElapsedCount) {
        return _100milisTimeElapsedCount % 36000 == 0;
    }

    protected void fillTimePartsFromTimeElapsed100MilisCount(long _100MilisTimeElapsedFromStart, long _100MilisTimeWhenNewIntervalCreated) {
        totalTimeParts.fillTimeParts(_100MilisTimeElapsedFromStart);
        intervalTimeParts.fillTimeParts(_100MilisTimeWhenNewIntervalCreated);
    }

    public void newInterval() {
        synchronized (intervalChangeMonitor) {
            intervalTimeParts = new TimeParts(0, 0, 0, 0);
            createNewInterval = true;
        }
    }

    protected void callOnTimerPausedCallback() {
        if (onTimerStateChangedListener != null) {
            onTimerStateChangedListener.onTimerPaused(totalTimeParts, intervalTimeParts);
        }
    }

    public void setOnTimeElapsedListener(Timer.OnTimeElapsed onTimeElapsed) {
        this.onTimeElapsedListener = onTimeElapsed;
    }

    public void setOnTimerStateChangedListener(Timer.OnTimerStateChanged onTimerStateChangedListener) {
        this.onTimerStateChangedListener = onTimerStateChangedListener;
    }
}
