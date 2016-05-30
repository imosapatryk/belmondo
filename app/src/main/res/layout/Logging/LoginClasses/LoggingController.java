package layout.Logging.LoginClasses;

import com.example.paciu.facebooklogging.Logging.LoginClasses.LoginBehaviour.LoginBehaviour;
import com.example.paciu.facebooklogging.Logging.LoginClasses.LoginBehaviour.LoginData;
import com.example.paciu.facebooklogging.Logging.LoginClasses.LoginBehaviour.LoginError;

/**
 * Created by paciu on 04.04.2016.
 */
public abstract class LoggingController {

    private LoggingListener loginListener;

    protected void callonSucceedLoginListener(LoginBehaviour loginBehaviour, LoginData data){
        if(loginListener != null){
            loginListener.onLoginSucced(loginBehaviour, data);
        }
    }

    protected void callonFailedLoginListener(LoginBehaviour loginBehaviour, LoginError error){
        if(loginListener != null){
            loginListener.onLoginFailed(loginBehaviour, error);
        }
    }

    protected void callonCancelledLoginListener(LoginBehaviour loginBehaviour){
        if(loginListener != null){
            loginListener.onLoginCancelled(loginBehaviour);
        }
    }

    public LoggingListener getLoggingListener() {
        return loginListener;
    }

    public void setLoggingListener(LoggingListener loggingListener) {
        this.loginListener = loggingListener;
    }
}
