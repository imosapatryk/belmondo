package com.example.paciu.belmondo.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout.*;

import com.example.paciu.belmondo.R;
import com.example.paciu.belmondo.google.LocationSourceProvider;
import com.example.paciu.belmondo.google.TrainingMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

/**
 * Created by paciu on 30.03.2016.
 */
public class FragmentMap  extends Fragment{
    private MapView mapView;
    private ViewGroup contentViewGroup;
    private TrainingMap trainingMap;

    public FragmentMap(Context context, LocationSourceProvider sourceProvider){
        trainingMap = new TrainingMap(sourceProvider);
        setupMapView(context, trainingMap);
    }

    protected void setupMapView(Context context, OnMapReadyCallback listener){
        mapView = new MapView(context);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        mapView.setLayoutParams(layoutParams);
        mapView.onCreate(null);
        mapView.getMapAsync(listener);
        mapView.onResume();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        setMenuVisibility(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.google_map_fragment, container, false);
        contentViewGroup = (ViewGroup) view;
        if(mapView != null && mapView.getParent() != null){
            ((ViewGroup)mapView.getParent()).removeView(mapView);
        }
        contentViewGroup.addView(mapView);
        setupToolbar(view);
        return view;
    }

    protected void setupToolbar(View parent){
        Toolbar toolbar = (Toolbar)parent.findViewById(R.id.map_fragment_toolbar);
        AppCompatActivity activity = ((AppCompatActivity) getActivity());
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(true);
        activity.getSupportActionBar().setHomeButtonEnabled(true);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            getFragmentManager().popBackStackImmediate();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public TrainingMap getTrainingMap() {
        return trainingMap;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

}
