package com.example.paciu.belmondo.SwipeDetector;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by paciu on 31.03.2016.
 */
public class SwipeDetector implements View.OnTouchListener {

    private static float SWIPE_NEEDED_DISTANCE = 30;
    private SwipeListener swipeListener;
    private MotionEvent moveEvent;
    public SwipeDetector(SwipeListener swipeListener) {
        this.swipeListener = swipeListener;
    }

    @Override
    public boolean onTouch(View v, MotionEvent e) {
        if(e.getAction() == MotionEvent.ACTION_CANCEL){
            moveEvent = null;
            return true;
        }
        else if(e.getAction() == MotionEvent.ACTION_MOVE){
            if(moveEvent == null) moveEvent = MotionEvent.obtain(e);
            else if(e.getHistorySize() >  moveEvent.getHistorySize()) moveEvent = MotionEvent.obtain(e);
            return true;
        }
        else if(e.getAction() == MotionEvent.ACTION_UP){
            Log.i("SwipeDetector", "ACTION_UP");
            if(checkSwipeRight(e)) {
                callOnSwipeRight(v, e);
                return true;
            }
            if(checkSwipeLeft(e)){
                Log.i("SwipeLeft", "CallingOnSwipeLeft");
                callOnSwipeLeft(v, e);
                return true;
            }
        }
        return false;
    }

    protected boolean checkSwipeRight(MotionEvent e) {
        return moveEvent != null && checkSwipeInOneDirection(moveEvent, Direction.RIGHT) && distanceBetweenFirstAndLastTouch(moveEvent, e, MotionEvent.AXIS_X) < -SWIPE_NEEDED_DISTANCE;
    }

    protected boolean checkSwipeLeft(MotionEvent e) {
        return moveEvent != null && checkSwipeInOneDirection(moveEvent, Direction.LEFT) && distanceBetweenFirstAndLastTouch(moveEvent, e, MotionEvent.AXIS_X) > SWIPE_NEEDED_DISTANCE;
    }

    protected void callOnSwipeRight(View v, MotionEvent e){
        if(swipeListener != null){
            this.swipeListener.onSwipeRight(v, e);
            moveEvent = null;
        }
    }

    protected void callOnSwipeLeft(View v, MotionEvent e){
        if(swipeListener != null){
            this.swipeListener.onSwipeLeft(v, e);
            moveEvent = null;
        }
    }

    protected float distanceBetweenFirstAndLastTouch(MotionEvent moveEvent, MotionEvent upEvent, int axis){
        return moveEvent.getHistorySize() > 0 ? moveEvent.getHistoricalAxisValue(axis, 0) - upEvent.getAxisValue(axis) : 0;
    }

    protected boolean checkSwipeInOneDirection(MotionEvent e, Direction dir){
        if(Direction.LEFT == dir){
            Log.i("histo", String.valueOf(e.getHistorySize()));
        }
        for(int i = 0; i < e.getHistorySize()-1; i++) {
            switch (dir) {
                case LEFT:{
                    if (e.getHistoricalAxisValue(MotionEvent.AXIS_X, i) < e.getHistoricalAxisValue(MotionEvent.AXIS_X, i + 1))
                        return false;
                    break;
                }
                case RIGHT:{
                    if (e.getHistoricalAxisValue(MotionEvent.AXIS_X, i) > e.getHistoricalAxisValue(MotionEvent.AXIS_X, i + 1))
                        return false;
                    break;
                }
            }
        }
        return e.getHistorySize() > 0;
    }

    public enum Direction{
        LEFT,
        RIGHT
    };

    public interface SwipeListener{
        void onSwipeLeft(View v, MotionEvent e);
        void onSwipeRight(View v, MotionEvent e);
    }
}
