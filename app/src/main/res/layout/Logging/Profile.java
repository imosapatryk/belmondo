package layout.Logging;

import com.example.paciu.facebooklogging.Logging.LoginClasses.LoginBehaviour.LoginData;

/**
 * Created by paciu on 04.04.2016.
 */
public class Profile {

    private static Profile profile;

    private LoginData profileData;

    private Profile(){}

    public static Profile getInstance(){
        if(profile == null){
            profile = new Profile();
        }
        return profile;
    }

    public void setProfileData(LoginData data){
        this.profileData = data;
    }

    public LoginData getProfileData(){
        return this.profileData;
    }
}
