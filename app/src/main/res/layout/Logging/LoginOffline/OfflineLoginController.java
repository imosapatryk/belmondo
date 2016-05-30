package layout.Logging.LoginOffline;

import com.example.paciu.facebooklogging.Logging.LoginClasses.LoggingController;
import com.example.paciu.facebooklogging.Logging.LoginClasses.LoggingListener;
import com.example.paciu.facebooklogging.Logging.LoginClasses.LoginBehaviour.LoginBehaviour;
import com.example.paciu.facebooklogging.Logging.LoginClasses.LoginBehaviour.LoginData;
import com.example.paciu.facebooklogging.Logging.LoginClasses.LoginBehaviour.LoginError;

/**
 * Created by paciu on 05.04.2016.
 */
public class OfflineLoginController extends LoggingController implements LoggingListener{
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
