package com.example.paciu.belmondo.Profile;

import android.content.Context;

/**
 * Created by paciu on 06.04.2016.
 */
public interface Profile {
    int getId();
    String getFirstName();
    String getLastName();
    String getBirthDate();
    void setBirthDate(Context context, String birthDate);
    int getWeight();
    void setWeight(Context context, int weight);
    int getHeight();
    void setHeight(Context context, int height);
    boolean isOnline();
    String getAccessTokenToServer();
    Sex getSex();
    void setSex(Context context, Sex sex);
}
