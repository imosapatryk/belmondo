package com.example.paciu.belmondo.ViewsExtends;

/**
 * Created by paciu on 13.03.2016.
 */

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;

import com.example.paciu.belmondo.R;

public class FloatingActionButtonPlayPause extends FloatingActionButton {

    private boolean playState = false;

    public FloatingActionButtonPlayPause(Context context) {
        super(context);
    }

    public FloatingActionButtonPlayPause(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FloatingActionButtonPlayPause(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setPlayState(boolean value){
        playState = value;
        if(isPlayState()){
            setImageDrawable(getContext().getDrawable(R.drawable.pause_icon));
        } else {
            setImageDrawable(getContext().getDrawable(R.drawable.play_icon50));
        }
    }

    public void clickMethodBasedOnStartPauseResumeObject(IStartPauseResume startPauseResume){
        if (!this.isPlayState()) {
                startOrResumeButtonAndChangeButtonState(startPauseResume);
        } else {
           pauseTimerAndChangeButtonState(startPauseResume);
        }
    }

    protected void startOrResumeButtonAndChangeButtonState(IStartPauseResume startPauseResume){
        if (startPauseResume.isPaused()) {
            startPauseResume.resume();
        } else {
            startPauseResume.start();
        }
        setPlayState(true);
    }

    protected void pauseTimerAndChangeButtonState(IStartPauseResume startPauseResume){
        startPauseResume.pause();
        setPlayState(false);
    }

    public boolean isPlayState(){
        return playState;
    }
}
