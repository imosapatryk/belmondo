package layout.Logging.LoginClasses.LoginBehaviour;

/**
 * Created by paciu on 04.04.2016.
 */
public class LoginData {

    private LoginError loginError;

    public LoginData(){}

    public LoginData(LoginError loginError) {
        setLoginError(loginError);
    }

    public LoginError getLoginError() {
        return loginError;
    }

    private void setLoginError(LoginError error){
        this.loginError = loginError;
    }
}
