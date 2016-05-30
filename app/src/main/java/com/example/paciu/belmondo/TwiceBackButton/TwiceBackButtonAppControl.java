package com.example.paciu.belmondo.TwiceBackButton;

import android.os.Handler;

/**
 * Created by paciu on 03.04.2016.
 */
public class TwiceBackButtonAppControl {
    private int clickCount = 0;
    private TwiceBackButtonCommandAction commandAction;

    public TwiceBackButtonAppControl(TwiceBackButtonCommandAction twiceBackButtonCommandAction) {
        this.commandAction = twiceBackButtonCommandAction;
    }

    public void setCommandAction(TwiceBackButtonCommandAction twiceBackButtonCommandAction){
        this.commandAction = twiceBackButtonCommandAction;
    }

    public void backPressed() {
        clickCount++;
        if (clickCount == 1) {
            commandAction.executeClickedOnce();
            resetCounterWithDelay();
        } else if (clickCount == 2) {
            commandAction.executeClickedTwice();
        }
    }

    private void resetCounterWithDelay(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                clickCount = 0;
            }
        }, 2000);
    }

    public void backCancel(){
        clickCount = 0;
    }
}
