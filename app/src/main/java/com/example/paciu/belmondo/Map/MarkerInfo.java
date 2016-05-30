package com.example.paciu.belmondo.Map;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by paciu on 13.05.2016.
 */
public class MarkerInfo {
    private String title;
    private float hue;
    private LatLng position;

    public MarkerInfo(LatLng position, String title, float hue) {
        this.title = title;
        this.hue = hue;
        this.position = position;
    }

    public LatLng getPosition() {
        return position;
    }

    public void setPosition(LatLng position) {
        this.position = position;
    }

    public float getHue() {
        return hue;
    }

    public void setHue(float hue) {
        this.hue = hue;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
