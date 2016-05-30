package layout.Logging.LoginOffline;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.example.paciu.facebooklogging.Logging.LoginClasses.LoggingController;
import com.example.paciu.facebooklogging.Logging.LoginClasses.LoggingListener;
import com.example.paciu.facebooklogging.Logging.LoginClasses.LoginBehaviour.LoginBehaviour;
import com.example.paciu.facebooklogging.Logging.LoginClasses.LoginBehaviour.LoginData;
import com.example.paciu.facebooklogging.Logging.LoginClasses.LoginBehaviour.LoginError;
import com.example.paciu.facebooklogging.Logging.LoginClasses.LoginBehaviour.LoginSucceed;

/**
 * Created by paciu on 05.04.2016.
 */
public class LoginOfflineButtonBelmondo extends LoginOfflineButtonBase {

    private LoggingOfflineController loginOfflineController;
    private LoggingListener loggingListener;

    public LoginOfflineButtonBelmondo(Context context) {
        super(context);
        setUpInternalListenerAndLoginOfflineInternalController();
    }

    public LoginOfflineButtonBelmondo(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUpInternalListenerAndLoginOfflineInternalController();
    }

    public LoginOfflineButtonBelmondo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUpInternalListenerAndLoginOfflineInternalController();
    }

    public LoginOfflineButtonBelmondo(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setUpInternalListenerAndLoginOfflineInternalController();

    }

    protected void setUpInternalListenerAndLoginOfflineInternalController(){
        setInternalOnClickListener(new LoginClickListener());
        loginOfflineController = new LoggingOfflineController();
    }

    public LoggingListener getLoggingListener() {
        return loggingListener;
    }

    public void setLoggingListener(LoggingListener loggingListener) {
        this.loggingListener = loggingListener;
    }

    private class LoginClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            callOnExternalClickListener(v);

            loginOfflineController.setLoggingListener(LoginOfflineButtonBelmondo.this.loggingListener);
            loginOfflineController.performLogin(getContext());
        }
    }

    private class LoggingOfflineController extends LoggingController implements LoggingListener{
       public void performLogin(Context context){
           //setting callback to acitvity
           onLoginSucced(new LoginSucceed(), null);
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
   }
}
