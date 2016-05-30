package com.example.paciu.belmondo.Map;

import android.graphics.Color;
import android.location.Location;

import com.example.paciu.belmondo.Discipline.Discipline;
import com.example.paciu.belmondo.Discipline.DisciplineChangedListener;
import com.example.paciu.belmondo.Discipline.DisciplineObservable;
import com.example.paciu.belmondo.Location.LocationChangedListener;
import com.example.paciu.belmondo.Location.LocationObservable;
import com.google.android.gms.maps.model.LatLng;

import org.jscience.physics.amount.Amount;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.measure.quantity.Length;
import javax.measure.unit.SI;

/**
 * Created by paciu on 13.05.2016.
 */
public class RouteDrawerBasedOnLocationAndDiscipline implements DisciplineChangedListener, RouteDrawer, LocationChangedListener {

    private MapDrawer mapDrawer;
    private Amount<Length> drawResolution = Amount.valueOf(0, SI.METER);
    private List<MarkerInfo> markers;

    public RouteDrawerBasedOnLocationAndDiscipline(MapDrawer mapDrawer, LocationObservable locationObservable, DisciplineObservable disciplineObservable) {
        this.mapDrawer = mapDrawer;
        locationObservable.registerLocationObserver(this);
        disciplineObservable.addDisciplineChangedListener(this);
        markers = new ArrayList<>();
        mapDrawer.setLineColor(Color.parseColor("#CCf6700a"));
        mapDrawer.setLineWidth(25);
    }

    @Override
    public void onLocationChanged(Location l) {
        if (l != null) {
            if(mapDrawer.getDrawnPointsCount() == 0){
                if(isEnabled()){
                    addMarker(l, "START", 30);
                }
            }
            if(mapDrawer.getLastDrawnLocation() != null){
                Amount<Length> distanceBetween = Amount.valueOf(l.distanceTo(mapDrawer.getLastDrawnLocation()), SI.METER);
                if(distanceBetween.compareTo(drawResolution) > 0){
                    mapDrawer.draw(l);
                }
            } else mapDrawer.draw(l);
            drawMarkersFromBuffor();
        }
    }

    @Override
    public boolean isEnabled(){
        return mapDrawer.isDrawingEnabled();
    }

    @Override
    public void setEnabled(boolean enabled){
        mapDrawer.setDrawingEnabled(enabled);
    }

    @Override
    public MapDrawer getMapDrawer() {
        return mapDrawer;
    }

    @Override
    public void setMapDrawer(MapDrawer mapDrawer) {
        this.mapDrawer = mapDrawer;
    }

    @Override
    public void addMarker(Location location, String title, float hue) {
        MarkerInfo marker;
        LatLng latLng = null;

        if(location != null){
            latLng = new LatLng(location.getLatitude(), location.getLongitude());
        }
        marker = new MarkerInfo(latLng, title, hue);

        if(mapDrawer.getDrawnPointsCount() == 0){
            markers.add(marker);
        } else {
            mapDrawer.addMarker(marker);
        }
    }

    protected void drawMarkersFromBuffor(){
        Iterator<MarkerInfo> markerInfoIterator = markers.iterator();
        while (markerInfoIterator.hasNext()){
            MarkerInfo info = markerInfoIterator.next();
            mapDrawer.addMarker(info);
            markerInfoIterator.remove();
        }
    }

    @Override
    public void onDisciplineChanged(Discipline discipline) {
        if(discipline != null){
            drawResolution = discipline.getDisciplineOnMapResolution();
        }
    }
}
