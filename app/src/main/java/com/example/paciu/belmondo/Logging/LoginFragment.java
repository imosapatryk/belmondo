package com.example.paciu.belmondo.Logging;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.paciu.belmondo.AppProfileManager;
import com.example.paciu.belmondo.Logging.LoginClasses.MainSystemLogging;
import com.example.paciu.belmondo.Logging.LoginOffline.LoginOfflineButtonBelmondo;
import com.example.paciu.belmondo.Logging.LoginOffline.OfflineLoginController;
import com.example.paciu.belmondo.R;
import com.facebook.login.widget.LoginButton;

/**
 * Created by paciu on 03.04.2016.
 */
public class LoginFragment extends FragmentWithReplacerAndOnClick {

    private LoginButton facebookLoginButton;
    private LoginOfflineButtonBelmondo offlineLoginButton;
    private OfflineLoginController offlineLoginController;
    private FacebookLoginController facebookLoginController;

    private MainSystemLogging appLoginController;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_layout, container, false);
        initViewComponents(view);
        return view;
    }

    @Override
    public String getFragmentTag() {
        return "LOGIN_FRAGMENT";
    }

    //--
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(facebookLoginController != null)facebookLoginController.onActivityResult(requestCode, resultCode, data);
        offlineLoginButton.onActivityResult(requestCode, resultCode);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == facebookLoginButton.getId()){
            appLoginController.setIndirectSystemLoginController(facebookLoginController);
        } else if(id == offlineLoginButton.getId()){
            appLoginController.setIndirectSystemLoginController(offlineLoginController);
        }
    }

    @Override
    protected void initViewComponents(View view) {
        initOfflineLoginCompomnents(view);
        initFacebookComponents(view);
    }

    protected void initOfflineLoginCompomnents(View view){
        offlineLoginController = new OfflineLoginController();
        offlineLoginButton = (LoginOfflineButtonBelmondo)view.findViewById(R.id.login_offline_button);
        offlineLoginButton.setOnClickListener(this);
        offlineLoginButton.setLoggingListener(offlineLoginController);
        offlineLoginButton.setFragment(this);
    }

    protected void initFacebookComponents(View view){
        facebookLoginController = new FacebookLoginController();
        facebookLoginButton = (LoginButton)view.findViewById(R.id.login_with_facebook_button);
        facebookLoginButton.setFragment(this);
        facebookLoginButton.setReadPermissions("public_profile");
        facebookLoginButton.registerCallback(facebookLoginController.getCallbackManager(), facebookLoginController);
        facebookLoginButton.setOnClickListener(this);
    }

    public void setAppLoginController(MainSystemLogging loggingController){
        this.appLoginController = loggingController;
    }
}
