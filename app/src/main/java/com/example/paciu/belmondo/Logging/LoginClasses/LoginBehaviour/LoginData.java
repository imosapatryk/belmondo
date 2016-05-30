package com.example.paciu.belmondo.Logging.LoginClasses.LoginBehaviour;


import com.example.paciu.belmondo.Profile.Profile;

/**
 * Created by paciu on 04.04.2016.
 */
public class LoginData {

    private Profile profile;
    private LoginError loginError;

    public LoginData(){}

    public LoginData(Profile profile, LoginError loginError) {
        this.profile = profile;
        setLoginError(loginError);
    }

    public LoginError getLoginError() {
        return loginError;
    }

    private void setLoginError(LoginError error){
        this.loginError = loginError;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
