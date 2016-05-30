package com.example.paciu.belmondo.Logging.LoginClasses.LoginBehaviour;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

import com.example.paciu.belmondo.R;
/**
 * Created by paciu on 04.04.2016.
 */
public class LoginFailed implements LoginBehaviour{

    @Override
    public void runLoginState(Context context, LoginData data) {
        if(data.getLoginError() != null){
            new AlertDialog.Builder(context)
                    .setTitle(R.string.login_error_title)
                    .setMessage(data.getLoginError().getLocalizedErrorMessage())
                    .setPositiveButton(context.getResources().getString(R.string.positive_button_text), null)
                    .show();
        } else {
            Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show();
        }
    }
}
