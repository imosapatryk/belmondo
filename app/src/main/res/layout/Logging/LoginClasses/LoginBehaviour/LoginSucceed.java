package layout.Logging.LoginClasses.LoginBehaviour;

import android.content.Context;
import android.content.Intent;

import com.example.paciu.facebooklogging.Logging.LoginRegisterActivity;
import com.example.paciu.facebooklogging.Logging.Profile;

/**
 * Created by paciu on 04.04.2016.
 */
public class LoginSucceed implements LoginBehaviour {
    @Override
    public void runLoginState(Context context, LoginData data) {
        Profile.getInstance().setProfileData(data);
        startNewActivity(context, LoginRegisterActivity.class);
    }

    protected void startNewActivity(Context context, Class<?> cls){
        Intent intent = new Intent(context, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
