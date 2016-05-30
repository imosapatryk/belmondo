package com.example.paciu.belmondo.ViewsExtends;

/**
 * Created by paciu on 13.03.2016.
 */

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;

import com.example.paciu.belmondo.R;
import com.example.paciu.belmondo.Timer.Playable;

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

    public void clickMethodBasedOnStartPauseResumeObject(Playable playable){
        if (!this.isPlayState()) {
                startOrResumeButtonAndChangeButtonState(playable);
        } else {
           pauseTimerAndChangeButtonState(playable);
        }
    }

    protected void startOrResumeButtonAndChangeButtonState(Playable playable){
        if (playable.isPaused()) {
            playable.resume();
        } else {
            playable.start();
        }
        setPlayState(true);
    }

    protected void pauseTimerAndChangeButtonState(Playable playable){
        playable.pause();
        setPlayState(false);
    }

    public boolean isPlayState(){
        return playState;
    }
}
