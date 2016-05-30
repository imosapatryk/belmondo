package layout.Logging;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.paciu.facebooklogging.Logging.LoginClasses.LoggingListener;
import com.example.paciu.facebooklogging.Logging.LoginClasses.LoginBehaviour.LoginBehaviour;
import com.example.paciu.facebooklogging.Logging.LoginClasses.LoginBehaviour.LoginData;
import com.example.paciu.facebooklogging.Logging.LoginClasses.LoginBehaviour.LoginError;
import com.example.paciu.facebooklogging.Logging.LoginClasses.MainSystemLogging;
import com.example.paciu.facebooklogging.R;
import com.facebook.FacebookSdk;

public class LoginRegisterActivity extends AppCompatActivity implements LoggingListener{
    private TwiceBackButtonAppFinisher twiceBackButtonAppFinisher;
    private MainSystemLogging systemLogging;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        FacebookSdk.sdkInitialize(getApplicationContext());

        systemLogging = new MainSystemLogging(this);
        LoginRegisterButtonsFragment loginRegisterButtonsFragment = new LoginRegisterButtonsFragment();
        loginRegisterButtonsFragment.setAppLoginController(systemLogging);

        getFragmentManager().beginTransaction().add(R.id.registration_fragment_container, loginRegisterButtonsFragment, loginRegisterButtonsFragment.getFragmentTag()).commit();
        twiceBackButtonAppFinisher = new TwiceBackButtonAppFinisher(this);
    }

    @Override
    public void onBackPressed() {
        twiceBackButtonAppFinisher.backPressed();
        if(getFragmentManager().getBackStackEntryCount() > 0){
            getFragmentManager().popBackStackImmediate();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
    }

    @Override
    public void onLoginSucced(LoginBehaviour loginBehaviour, LoginData data) {
        loginBehaviour.runLoginState(getApplicationContext(), data);
    }

    @Override
    public void onLoginFailed(LoginBehaviour loginBehaviour, LoginError error) {
        loginBehaviour.runLoginState(getApplicationContext(), new LoginData(error));
    }

    @Override
    public void onLoginCancelled(LoginBehaviour loginBehaviour) {
        loginBehaviour.runLoginState(getApplicationContext(), new LoginData());
    }
}
