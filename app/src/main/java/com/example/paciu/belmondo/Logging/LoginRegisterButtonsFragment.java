package com.example.paciu.belmondo.Logging;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.paciu.belmondo.Logging.LoginClasses.MainSystemLogging;
import com.example.paciu.belmondo.R;

/**
 * Created by paciu on 03.04.2016.
 */
public class LoginRegisterButtonsFragment extends FragmentWithReplacerAndOnClick{

    private TextView registerTextView;
    private Button loginButton;
    private MainSystemLogging appLoginController;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_register_button_fragment, container, false);
        initViewComponents(view);
        return view;
    }

    protected void initViewComponents(View view){
        registerTextView = (TextView)view.findViewById(R.id.register_textview);
        registerTextView.setOnClickListener(this);
        loginButton = (Button)view.findViewById(R.id.login_button);
        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == registerTextView.getId()){
            showRegisterOptions();
        } else if(id == loginButton.getId()){
            showLoginOptions();
        }
    }

    protected void showRegisterOptions(){
        RegisterFragment registerFragment = new RegisterFragment();
        replaceWithFragment(R.id.registration_fragment_container, registerFragment);
    }

    protected void showLoginOptions(){
        LoginFragment loginFragment = new LoginFragment();
        loginFragment.setAppLoginController(appLoginController);
        replaceWithFragment(R.id.registration_fragment_container, loginFragment);
    }

    @Override
    public String getFragmentTag() {
        return "LOGIN_REGISTER_FRAGMENT";
    }

    public void setAppLoginController(MainSystemLogging appLoginController){
        this.appLoginController = appLoginController;
    }
}
