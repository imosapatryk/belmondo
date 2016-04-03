package com.example.paciu.belmondo.google;

import android.content.Context;
import android.location.GpsStatus;
import android.location.LocationManager;
import android.util.Log;
import android.widget.Toast;

import com.example.paciu.belmondo.R;

import java.util.HashSet;

/**
 * Created by paciu on 21.03.2016.
 */
public class GPSStatusMonitor implements GpsStatus.Listener {

    private Context context;
    private HashSet<GPSStatusMonitor.OnGpsStatusChanged> listeners;
    private OnGpsStatusChanged selfListener;

    public GPSStatusMonitor(Context context, OnGpsStatusChanged listener){
        this(context);
        addGpsStatusListener(listener);
    }

    public GPSStatusMonitor(Context context) {
        this.context = context;
        this.listeners = new HashSet<>();
        selfListener = new OnGpsStatusChanged() {
            @Override
            public void onGpsStarted() {
                Log.i("GPS", "STARTED");
                Toast.makeText(getContext(), R.string.gps_has_been_enabled, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onGpsStopped() {
                Log.i("GPS", "STOPPED");
                Toast.makeText(getContext(), R.string.gps_has_been_disabled, Toast.LENGTH_SHORT).show();
            }
        };
        addSelfAsGpsStatusListener();
    }

    protected void addSelfAsGpsStatusListener(){
        LocationManager manager = (LocationManager)getContext().getSystemService(Context.LOCATION_SERVICE);
        manager.addGpsStatusListener(this);
    }

    public void addGpsStatusListener(OnGpsStatusChanged listener) {
        if(listener != null && listener != this){
            listeners.add(listener);
        }
    }

    public void removeGpsStatusListener(GpsStatus.Listener listener){
        listeners.remove(listener);
    }

    @Override
    public void onGpsStatusChanged(int event) {
        if(listeners.size() > 0){
            callOnGpsStatusChangedForAllListeners(event);
        } else {
            switchEventGpsStatus(selfListener, event);
        }
    }

    public void switchEventGpsStatus(GPSStatusMonitor.OnGpsStatusChanged listener, int event){
        switch(event){
            case GpsStatus.GPS_EVENT_STARTED:{
                if(listener!= null)listener.onGpsStarted();
                break;
            }
            case GpsStatus.GPS_EVENT_STOPPED:{
                if(listener!= null)listener.onGpsStopped();
                break;
            }
        }
    }

    protected void callOnGpsStatusChangedForAllListeners(int event){
        for (OnGpsStatusChanged l: listeners) {
            if(l != null){
                switchEventGpsStatus(l, event);
            }
        }
    }

    public Context getContext() {
        return context;
    }

    public interface OnGpsStatusChanged{
        void onGpsStarted();
        void onGpsStopped();
    }
}
