package com.example.paciu.belmondo.Logging.LoginClasses.LoginBehaviour;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.paciu.belmondo.MainActivity;

/**
, * Created by paciu on 04.04.2016.
 */
public class LoginSucceed implements LoginBehaviour {
    @Override
    public void runLoginState(Context context, LoginData data) {
        startNewActivityAndFinish(context, MainActivity.class);
    }

    protected void startNewActivityAndFinish(Context context, Class<?> cls){
        Intent intent = new Intent(context, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        Activity activity = (Activity)context;
        activity.finish();
    }
}
