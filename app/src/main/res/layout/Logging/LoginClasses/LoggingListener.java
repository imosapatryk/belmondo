package layout.Logging.LoginClasses;

import com.example.paciu.facebooklogging.Logging.LoginClasses.LoginBehaviour.LoginBehaviour;
import com.example.paciu.facebooklogging.Logging.LoginClasses.LoginBehaviour.LoginData;
import com.example.paciu.facebooklogging.Logging.LoginClasses.LoginBehaviour.LoginError;

/**
 * Created by paciu on 04.04.2016.
 */
public interface LoggingListener {
    void onLoginSucced(LoginBehaviour loginBehaviour, LoginData data);
    void onLoginFailed(LoginBehaviour loginBehaviour, LoginError error);
    void onLoginCancelled(LoginBehaviour loginBehaviour);
}
