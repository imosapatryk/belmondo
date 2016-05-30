package com.example.paciu.belmondo.Logging;

import android.content.Intent;

import com.example.paciu.belmondo.Logging.LoginClasses.LoggingController;
import com.example.paciu.belmondo.Logging.LoginClasses.LoginBehaviour.LoginCancelled;
import com.example.paciu.belmondo.Logging.LoginClasses.LoginBehaviour.LoginData;
import com.example.paciu.belmondo.Logging.LoginClasses.LoginBehaviour.LoginError;
import com.example.paciu.belmondo.Logging.LoginClasses.LoginBehaviour.LoginFailed;
import com.example.paciu.belmondo.Logging.LoginClasses.LoginBehaviour.LoginSucceed;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;

/**
 * Created by paciu on 03.04.2016.
 */
public class FacebookLoginController extends LoggingController implements FacebookCallback<LoginResult>{
    private CallbackManager callbackManager;

    public FacebookLoginController() {
        this.callbackManager = CallbackManager.Factory.create();
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        callonSucceedLoginListener(new LoginSucceed(), new LoginData());
    }

    @Override
    public void onCancel() {
        callonCancelledLoginListener(new LoginCancelled());
    }

    @Override
    public void onError(FacebookException error) {
        callonFailedLoginListener(new LoginFailed(), new LoginError(error.getLocalizedMessage()));
    }

    public CallbackManager getCallbackManager() {
        return callbackManager;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        getCallbackManager().onActivityResult(requestCode, resultCode, data);
    }
}
