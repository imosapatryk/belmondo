package com.example.paciu.belmondo.Logging.LoginOffline;

import android.app.Fragment;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.example.paciu.belmondo.Logging.LoginClasses.LoggingController;
import com.example.paciu.belmondo.Logging.LoginClasses.LoggingListener;
import com.example.paciu.belmondo.Logging.LoginClasses.LoginBehaviour.LoginBehaviour;
import com.example.paciu.belmondo.Logging.LoginClasses.LoginBehaviour.LoginCancelled;
import com.example.paciu.belmondo.Logging.LoginClasses.LoginBehaviour.LoginData;
import com.example.paciu.belmondo.Logging.LoginClasses.LoginBehaviour.LoginError;
import com.example.paciu.belmondo.Logging.LoginClasses.LoginBehaviour.LoginFailed;
import com.example.paciu.belmondo.Logging.LoginClasses.LoginBehaviour.LoginSucceed;
import com.example.paciu.belmondo.Profile.Profile;
import com.example.paciu.belmondo.Profile.ProfileModelAdapter;

/**
 * Created by paciu on 05.04.2016.
 */
public class LoginOfflineButtonBelmondo extends ButtonWithInternalClickBase {
    public static int LOGGING_OFFLINE_REQUEST = 1000;

    public enum LoginResultEnum{
        SUCCEED,
        FAILED,
        CANCELLED;

        public static int toInteger(LoginResultEnum loginResultEnum){
            switch(loginResultEnum){
                case SUCCEED: return 10;
                case FAILED: return 2;
                case CANCELLED: return 30;
            }
            return 1;
        }

        public static LoginResultEnum fromInteger(int index){
            switch (index){
                case 10: return SUCCEED;
                case 20: return FAILED;
                case 30: return CANCELLED;
            }
            return CANCELLED;
        }
    };

    private LoginBehaviour succeedLoginBehaviour;
    private LoginBehaviour failedLoginBehaviour;
    private LoginBehaviour cancelledLoginBehaviour;

    private LoggingOfflineController loginOfflineController;
    private LoggingListener loggingListener;
    private Fragment fragment;

    public LoginOfflineButtonBelmondo(Context context) {
        super(context);
        setupAllComponents();
    }

    public LoginOfflineButtonBelmondo(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupAllComponents();
    }

    public LoginOfflineButtonBelmondo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupAllComponents();
    }

    public LoginOfflineButtonBelmondo(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setupAllComponents();

    }

    protected void setupAllComponents(){
        setUpInternalListenerAndLoginOfflineInternalController();
        setupInitBehaviours();
    }

    protected void setUpInternalListenerAndLoginOfflineInternalController(){
        setInternalOnClickListener(new LoginClickListener());
        loginOfflineController = new LoggingOfflineController();
    }

    protected void setupInitBehaviours(){
        setSucceedLoginBehaviour(new LoginSucceed());
        setFailedLoginBehaviour(new LoginFailed());
        setCancelledLoginBehaviour(new LoginCancelled());
    }

    public LoggingListener getLoggingListener() {
        return loggingListener;
    }

    public void setLoggingListener(LoggingListener loggingListener) {
        this.loggingListener = loggingListener;
    }

    public LoginBehaviour getSucceedLoginBehaviour() {
        return succeedLoginBehaviour;
    }

    public void setSucceedLoginBehaviour(LoginBehaviour succeedLoginBehaviour) {
        this.succeedLoginBehaviour = succeedLoginBehaviour;
    }

    public LoginBehaviour getFailedLoginBehaviour() {
        return failedLoginBehaviour;
    }

    public void setFailedLoginBehaviour(LoginBehaviour failedLoginBehaviour) {
        this.failedLoginBehaviour = failedLoginBehaviour;
    }

    public LoginBehaviour getCancelledLoginBehaviour() {
        return cancelledLoginBehaviour;
    }

    public void setCancelledLoginBehaviour(LoginBehaviour cancelledLoginBehaviour) {
        this.cancelledLoginBehaviour = cancelledLoginBehaviour;
    }

    public void onActivityResult(int requestCode, int resultCode){
        this.loginOfflineController.onActivityResult(requestCode, resultCode);
    }

    private class LoginClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            callOnExternalClickListener(v);

            loginOfflineController.setLoggingListener(LoginOfflineButtonBelmondo.this.loggingListener);
            loginOfflineController.login();
        }
    }

    public void setFragment(Fragment fragment){
        this.fragment = fragment;
    }

    public Fragment getFragment(){
        return fragment;
    }

    private class LoggingOfflineController extends LoggingController {
        private LoginCommand command;

        public LoggingOfflineController() {}

        public void login() {
            command = new BelmondoLoginOfflineCommand(getFragment(), getContext());
            command.login();
        }


        public void onActivityResult(int requestCode, int resultCode) {
            switch (LoginResultEnum.fromInteger(resultCode)) {
                case SUCCEED: {
                    callOnLoginSucceed(LoginOfflineButtonBelmondo.this.getSucceedLoginBehaviour(), new LoginData(ProfileModelAdapter.getInstance(), null));
                    break;
                }
                case FAILED: {
                    callOnLoginFailed(LoginOfflineButtonBelmondo.this.getFailedLoginBehaviour(), null);
                    break;
                }
                case CANCELLED: {
                    callOnLoginCancelled(LoginOfflineButtonBelmondo.this.getCancelledLoginBehaviour());
                    break;
                }
                default: {
                    callOnLoginFailed(LoginOfflineButtonBelmondo.this.getFailedLoginBehaviour(), null);
                }

            }
        }

        protected void callOnLoginSucceed(LoginBehaviour loginBehaviour, LoginData data) {
            if (loggingListener != null) {
                loggingListener.onLoginSucced(loginBehaviour, data);
            }
        }

        protected void callOnLoginFailed(LoginBehaviour loginBehaviour, LoginError error) {
            if (loggingListener != null) {
                loggingListener.onLoginFailed(loginBehaviour, error);
            }
        }

        protected void callOnLoginCancelled(LoginBehaviour loginBehaviour) {
            if (loggingListener != null) {
                loggingListener.onLoginCancelled(loginBehaviour);
            }
        }
    }
}
