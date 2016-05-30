package com.example.paciu.belmondo.Logging.LoginClasses.LoginBehaviour;

import android.content.Context;
import android.widget.Toast;

import com.example.paciu.belmondo.R;


/**
 * Created by paciu on 04.04.2016.
 */
public class LoginCancelled implements LoginBehaviour {
    @Override
    public void runLoginState(Context context, LoginData data) {
        Toast.makeText(context, R.string.login_cancelled, Toast.LENGTH_SHORT).show();
    }
}
