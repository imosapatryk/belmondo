package com.example.paciu.belmondo.Logging.LoginOffline;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;

import com.example.paciu.belmondo.ProfileChooseActivity;

/**
 * Created by paciu on 14.05.2016.
 */
public class BelmondoLoginOfflineCommand implements LoginCommand {
    private Fragment fragment;
    private Context context;

    public BelmondoLoginOfflineCommand(Fragment fragment, Context context) {
        this.fragment = fragment;
        this.context = context;
    }

    @Override
    public void login() {
        Intent intent = new Intent(context, ProfileChooseActivity.class);
        if(fragment != null) {
            fragment.startActivityForResult(intent, LoginOfflineButtonBelmondo.LOGGING_OFFLINE_REQUEST);
        }
        else {
            ((Activity)context).startActivityForResult(intent, LoginOfflineButtonBelmondo.LOGGING_OFFLINE_REQUEST);
        }
    }
}
