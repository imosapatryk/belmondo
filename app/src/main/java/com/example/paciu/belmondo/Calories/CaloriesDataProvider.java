package com.example.paciu.belmondo.Calories;

import com.example.paciu.belmondo.Profile.Profile;

/**
 * Created by paciu on 10.05.2016.
 */
public interface CaloriesDataProvider extends CaloriesObservable{
    boolean isEnabled();
    void setEnabled(boolean enabled);
    int getCalories();
    Profile getProfile();
    void setProfile(Profile profile);
}
