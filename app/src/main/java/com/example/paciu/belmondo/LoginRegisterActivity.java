package com.example.paciu.belmondo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.paciu.belmondo.Logging.LoginClasses.LoggingListener;
import com.example.paciu.belmondo.Logging.LoginClasses.LoginBehaviour.LoginBehaviour;
import com.example.paciu.belmondo.Logging.LoginClasses.LoginBehaviour.LoginData;
import com.example.paciu.belmondo.Logging.LoginClasses.LoginBehaviour.LoginError;
import com.example.paciu.belmondo.Logging.LoginClasses.MainSystemLogging;
import com.example.paciu.belmondo.Logging.LoginRegisterButtonsFragment;
import com.example.paciu.belmondo.TwiceBackButton.AppFinisherCommand;
import com.example.paciu.belmondo.TwiceBackButton.TwiceBackButtonAppControl;
import com.facebook.FacebookSdk;
public class LoginRegisterActivity extends AppCompatActivity implements LoggingListener{
    private TwiceBackButtonAppControl twiceBackButtonAppFinisher;
    private MainSystemLogging systemLogging;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        FacebookSdk.sdkInitialize(getApplicationContext());

        if(AppProfileManager.getInstance().getProfile() != null){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        systemLogging = new MainSystemLogging(this);
        LoginRegisterButtonsFragment loginRegisterButtonsFragment = new LoginRegisterButtonsFragment();
        loginRegisterButtonsFragment.setAppLoginController(systemLogging);

        getFragmentManager().beginTransaction().add(R.id.registration_fragment_container, loginRegisterButtonsFragment, loginRegisterButtonsFragment.getFragmentTag()).commit();
        twiceBackButtonAppFinisher = new TwiceBackButtonAppControl(new AppFinisherCommand(this, getString(R.string.tap_twice_to_exit)));
    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() > 0){
            getFragmentManager().popBackStackImmediate();
        } else {
            twiceBackButtonAppFinisher.backPressed();
        }
    }

    @Override
    public void onLoginSucced(LoginBehaviour loginBehaviour, LoginData data) {
        AppProfileManager.getInstance().setProfile(data.getProfile());
        loginBehaviour.runLoginState(this, data);
    }

    @Override
    public void onLoginFailed(LoginBehaviour loginBehaviour, LoginError error) {
        loginBehaviour.runLoginState(this, new LoginData(null, error));
    }

    @Override
    public void onLoginCancelled(LoginBehaviour loginBehaviour) {
        loginBehaviour.runLoginState(this, new LoginData());
    }
}
