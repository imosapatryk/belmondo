package com.example.paciu.belmondo.ResultDetails;
import android.graphics.Color;

import com.example.paciu.belmondo.Result.LatLngSerializableImpl;
import com.example.paciu.belmondo.Result.Result;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paciu on 22.04.2016.
 */
public class ResultDetailsMapViewController implements OnMapReadyCallback {
    private Result result;
    private MapView mapView;
    private PolylineOptions polylineOptions;

    public ResultDetailsMapViewController(MapView mapView, Result result) {
        this.result = result;
        this.mapView = mapView;
        setupMapView();
        polylineOptions = new PolylineOptions();
        polylineOptions.addAll(convertResultMapCoordsToLatLng());
        setLineColor(Color.parseColor("#CCf6700a"));
        setLineWidth(25);
    }

    protected void setupMapView(){
        mapView.onCreate(null);
        mapView.getMapAsync(this);
        mapView.onResume();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if(result.getMapCoords() != null) {
            setupUISettings(googleMap);
            googleMap.addPolyline(polylineOptions);
            addStartMarker(googleMap);
            addEndMarker(googleMap);
            if(result.getMapCoords() != null && result.getMapCoords().size() > 0)
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(result.getMapCoords().get(0).latitude, result.getMapCoords().get(0).longitude), 17));
        }
    }

    protected void setupUISettings(GoogleMap googleMap){
        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setCompassEnabled(true);
        uiSettings.setMapToolbarEnabled(true);
        uiSettings.setZoomControlsEnabled(true);
    }

    protected List<LatLng> convertResultMapCoordsToLatLng(){
        if(result.getMapCoords() != null){
            List<LatLng> latLngs = new ArrayList<>();
            for(LatLngSerializableImpl ll : result.getMapCoords()){
                latLngs.add(new LatLng(ll.latitude, ll.longitude));
            }
            return latLngs;
        }
        return null;
    }

    protected void addStartMarker(GoogleMap googleMap) {
        if(result.getMapCoords() != null && result.getMapCoords().size() > 0) {
            LatLngSerializableImpl latLngSerializable = result.getMapCoords().get(0);
            googleMap.addMarker(new MarkerOptions().title("Start")
                    .position(new LatLng(latLngSerializable.latitude, latLngSerializable.longitude)));
        }
    }

    protected void addEndMarker(GoogleMap googleMap) {
        if(result.getMapCoords() != null && result.getMapCoords().size() > 0) {
            LatLngSerializableImpl latLngSerializable = result.getMapCoords().get(result.getMapCoords().size() - 1);
            googleMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("Stop")
                    .position(new LatLng(latLngSerializable.latitude, latLngSerializable.longitude)));
        }
    }

    public void setLineColor(int color){
        polylineOptions.color(color);
    }

    public void setLineWidth(float width){
        polylineOptions.width(width);
    }

    public int getLineColor(){
        return polylineOptions.getColor();
    }

    public float getLineWidth(){
        return polylineOptions.getWidth();
    }
}
