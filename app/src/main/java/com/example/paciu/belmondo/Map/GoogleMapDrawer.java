package com.example.paciu.belmondo.Map;

import android.location.Location;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

/**
 * Created by paciu on 13.05.2016.
 */
public class GoogleMapDrawer implements MapDrawer {
    private Location lastDrawnLocation;
    private GoogleMap googleMap;
    private boolean drawingEnabled;
    private PolylineOptions polylineOptions;
    private Polyline polyline;

    public GoogleMapDrawer(GoogleMap googleMap) {
        this.googleMap = googleMap;
        drawingEnabled = true;
        polylineOptions = new PolylineOptions().geodesic(true);
        polyline = googleMap.addPolyline(polylineOptions);
        drawingEnabled = false;
    }

    @Override
    public void draw(Location nextLocation) {
        if(nextLocation != null) {
            lastDrawnLocation = nextLocation;
            if (isDrawingEnabled()) {
                polylineOptions.add(new LatLng(nextLocation.getLatitude(), nextLocation.getLongitude()));
                polyline.setPoints(polylineOptions.getPoints());
            }
        }
    }

    @Override
    public Location getLastDrawnLocation() {
        return lastDrawnLocation;
    }

    @Override
    public boolean isDrawingEnabled() {
        return drawingEnabled;
    }

    @Override
    public void setDrawingEnabled(boolean enabled) {
        this.drawingEnabled = enabled;
    }

    @Override
    public void addMarker(MarkerInfo marker) {
        MarkerOptions markerOptions = new MarkerOptions().title(marker.getTitle()).position(marker.getPosition()).icon(getBitmapDescriptor(marker.getHue()));
        googleMap.addMarker(markerOptions);
    }

    @Override
    public void setLineWidth(float width) {
        polyline.setWidth(width);
    }

    @Override
    public float getLineWidth() {
        return polyline.getWidth();
    }

    @Override
    public void setLineColor(int color) {
        polyline.setColor(color);
    }

    @Override
    public int getLineColor() {
        return polyline.getColor();
    }

    @Override
    public int getDrawnPointsCount(){
        return polylineOptions.getPoints().size();
    }

    @Override
    public List<LatLng> getMapDrawnPoints() {
        return polylineOptions.getPoints();
    }

    protected BitmapDescriptor getBitmapDescriptor(float hue){
        return BitmapDescriptorFactory.defaultMarker(hue);
    }
}
