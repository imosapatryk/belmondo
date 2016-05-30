package com.example.paciu.belmondo.TwiceBackButton;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by paciu on 23.04.2016.
 */
public class AppFinisherCommand implements TwiceBackButtonCommandAction {

    private Activity activity;
    private String clickedOnceText;

    public AppFinisherCommand(Activity activity, String clickedOnceText) {
        this.activity = activity;
        this.clickedOnceText = clickedOnceText;
    }

    @Override
    public void executeClickedOnce() {
        Toast.makeText(activity, clickedOnceText, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void executeClickedTwice() {
        activity.finish();
    }
}
