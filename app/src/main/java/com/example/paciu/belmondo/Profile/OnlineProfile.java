package com.example.paciu.belmondo.Profile;

import android.content.Context;

/**
 * Created by paciu on 06.04.2016.
 */
public class OnlineProfile implements Profile {

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getFirstName() {
        return null;
    }

    @Override
    public String getLastName() {
        return null;
    }

    @Override
    public String getBirthDate() {
        return null;
    }

    @Override
    public void setBirthDate(Context context, String birthDate) {

    }

    @Override
    public int getWeight() {
        return 0;
    }

    @Override
    public void setWeight(Context context, int weight) {

    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void setHeight(Context context, int height) {

    }

    @Override
    public boolean isOnline() {
        return true;
    }

    @Override
    public String getAccessTokenToServer() {
        return null;
    }

    @Override
    public Sex getSex() {
        return null;
    }

    @Override
    public void setSex(Context context, Sex sex) {

    }
}
