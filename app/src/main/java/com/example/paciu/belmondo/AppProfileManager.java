package com.example.paciu.belmondo;

import com.example.paciu.belmondo.Profile.Profile;

/**
 * Created by paciu on 06.04.2016.
 */
public class AppProfileManager {

    private static AppProfileManager appProfileManager;

    private Profile profile;

    private AppProfileManager() {}

    public static AppProfileManager getInstance(){
        if(appProfileManager == null){
            appProfileManager = new AppProfileManager();
        }
        return appProfileManager;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
