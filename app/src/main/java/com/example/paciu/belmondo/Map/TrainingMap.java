package com.example.paciu.belmondo.Map;

import com.example.paciu.belmondo.Discipline.DisciplineObservable;
import com.example.paciu.belmondo.Location.LocationObservable;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;

/**
 * Created by paciu on 02.04.2016.
 */
public class TrainingMap implements Map, OnMapReadyCallback {
    private GoogleMap map;
    private LocationSource sourceProvider;
    private RouteDrawer routeDrawer;
    private LocationObservable locationObservable;
    private DisciplineObservable disciplineObservable;

    public TrainingMap(LocationObservable locationObservable, DisciplineObservable disciplineObservable){
        this.locationObservable = locationObservable;
        this.sourceProvider = new MapLocationSource(this.locationObservable);
        this.disciplineObservable = disciplineObservable;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        setupMap(googleMap);
        routeDrawer = new RouteDrawerBasedOnLocationAndDiscipline(new GoogleMapDrawer(googleMap), locationObservable, disciplineObservable);
    }

    protected void setupMap(GoogleMap googleMap){
        map = googleMap;
        map.setMyLocationEnabled(true);
        map.getUiSettings().setMyLocationButtonEnabled(true);
        map.getUiSettings().setCompassEnabled(true);
        map.getUiSettings().setMapToolbarEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);
        map.setLocationSource(sourceProvider);
    }

    public RouteDrawer getRouteDrawer(){
        return this.routeDrawer;
    }
}

