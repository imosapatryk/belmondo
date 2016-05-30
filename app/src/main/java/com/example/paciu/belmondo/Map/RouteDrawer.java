package com.example.paciu.belmondo.Map;

import android.location.Location;

/**
 * Created by paciu on 13.05.2016.
 */
public interface RouteDrawer {
    MapDrawer getMapDrawer();
    void setMapDrawer(MapDrawer mapDrawer);
    void addMarker(Location location, String title, float hue);
    boolean isEnabled();
    void setEnabled(boolean enabled);

}
