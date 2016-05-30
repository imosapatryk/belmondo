package com.example.paciu.belmondo.Map;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by paciu on 13.05.2016.
 */
public interface MapDrawer {
    boolean isDrawingEnabled();
    void setDrawingEnabled(boolean enabled);
    void draw(Location nextLocation);
    Location getLastDrawnLocation();
    void addMarker(MarkerInfo marker);
    void setLineWidth(float width);
    float getLineWidth();
    void setLineColor(int color);
    int getLineColor();
    int getDrawnPointsCount();
    List<LatLng> getMapDrawnPoints();
}
