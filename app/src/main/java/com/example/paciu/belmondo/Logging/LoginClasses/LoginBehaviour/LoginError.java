package com.example.paciu.belmondo.Logging.LoginClasses.LoginBehaviour;

/**
 * Created by paciu on 04.04.2016.
 */
public class LoginError {
    private String message;

    public LoginError(String localizedMessage){
        this.message = localizedMessage;
    }

    public String getLocalizedErrorMessage(){
        return message;
    }
}
