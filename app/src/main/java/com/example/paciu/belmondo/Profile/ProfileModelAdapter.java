package com.example.paciu.belmondo.Profile;

import android.content.Context;

import com.example.paciu.belmondo.SqliteDataSource.Profiles.ProfileDataSource;
import com.example.paciu.belmondo.SqliteDataSource.Profiles.ProfileModel;

/**
 * Created by paciu on 06.04.2016.
 */
public class ProfileModelAdapter implements Profile {

    private static ProfileModelAdapter offlineProfile;
    private ProfileModel profile;

    public static ProfileModelAdapter getInstance(){
        if(offlineProfile == null){
            offlineProfile = new ProfileModelAdapter();
        }
        return offlineProfile;
    }

    @Override
    public int getId() {
        return profile.getId();
    }

    @Override
    public String getFirstName() {
        return profile.getName();
    }

    @Override
    public String getLastName() {
        return "";
    }

    @Override
    public String getBirthDate() {
        return profile.getBirthDate();
    }

    @Override
    public void setBirthDate(Context context, String birthDate) {
        profile.setBirthDate(birthDate);
        updateProfileDataSource(context);
    }

    @Override
    public int getWeight() {
        return profile.getWeight();
    }

    @Override
    public void setWeight(Context context,int weight) {
        profile.setWeight(weight);
        updateProfileDataSource(context);
    }

    @Override
    public int getHeight() {
        return profile.getHeight();
    }

    @Override
    public void setHeight(Context context,int height) {
        profile.setHeight(height);
        updateProfileDataSource(context);
    }

    @Override
    public boolean isOnline() {
        return false;
    }

    @Override
    public String getAccessTokenToServer() {
        return null;
    }

    @Override
    public Sex getSex() {
        return profile.getSex();
    }

    @Override
    public void setSex(Context context, Sex sex) {
        profile.setSex(sex);
        updateProfileDataSource(context);
    }

    protected void updateProfileDataSource(Context context){
        ProfileDataSource dataSource = new ProfileDataSource(context);
        dataSource.openDB();
        dataSource.update(profile);
        dataSource.closeDB();
    }

    public ProfileModel getProfileModel() {
        return profile;
    }

    public void setProfileModel(ProfileModel profileModel) {
        this.profile = profileModel;
    }
}
