package com.example.paciu.belmondo.Logging.LoginClasses;


import com.example.paciu.belmondo.Logging.LoginClasses.LoginBehaviour.LoginBehaviour;
import com.example.paciu.belmondo.Logging.LoginClasses.LoginBehaviour.LoginData;
import com.example.paciu.belmondo.Logging.LoginClasses.LoginBehaviour.LoginError;

/**
 * Created by paciu on 04.04.2016.
 */
public class MainSystemLogging extends LoggingController implements LoggingListener{

    private LoggingController indirectSystemLoggingController;

    public MainSystemLogging(LoggingListener loggingResultListener) {
        this.setLoggingListener(loggingResultListener);
    }

    @Override
    public void onLoginSucced(LoginBehaviour loginBehaviour, LoginData data) {
        callonSucceedLoginListener(loginBehaviour, data);
    }

    @Override
    public void onLoginFailed(LoginBehaviour loginBehaviour, LoginError error) {
        callonFailedLoginListener(loginBehaviour, error);
    }

    @Override
    public void onLoginCancelled(LoginBehaviour loginBehaviour) {
        callonCancelledLoginListener(loginBehaviour);
    }

    public LoggingController getIndirectSystemLoginController() {
        return indirectSystemLoggingController;
    }

    public void setIndirectSystemLoginController(LoggingController indirectSystemLoggingController) {
        this.indirectSystemLoggingController = indirectSystemLoggingController;
        indirectSystemLoggingController.setLoggingListener(this);
    }
}
