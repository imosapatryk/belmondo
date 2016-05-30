package com.example.paciu.belmondo.Network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by paciu on 21.03.2016.
 */
public class NetworkChangedReceiver extends BroadcastReceiver {

    private OnNetworkStateChangedListener onNetworkStateChangedListener;
    private boolean connected;

    public NetworkChangedReceiver(Context context, OnNetworkStateChangedListener listener){
        this.onNetworkStateChangedListener = listener;
        this.connected = isConnectedToTheInternet(context);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean newConnectedState = isConnectedToTheInternet(context);
        if(newConnectedState != connected){
            if(newConnectedState){
                callOnInternetConnected();
            } else {
                callOnInternetConnectionLost();
            }
            connected = newConnectedState;
        }
    }

    protected void callOnInternetConnected(){
        if(onNetworkStateChangedListener != null){
            onNetworkStateChangedListener.onInternetConnected();
        }
    }

    protected void callOnInternetConnectionLost(){
        if(onNetworkStateChangedListener != null){
            onNetworkStateChangedListener.onInternetConnectionLost();
        }
    }

    public OnNetworkStateChangedListener getOnNetworkStateChangedListener() {
        return onNetworkStateChangedListener;
    }

    public void setOnNetworkStateChangedListener(OnNetworkStateChangedListener onNetworkStateChangedListener) {
        this.onNetworkStateChangedListener = onNetworkStateChangedListener;
    }

    public interface OnNetworkStateChangedListener{
        void onInternetConnected();
        void onInternetConnectionLost();
    }

    protected boolean isConnectedToTheInternet(Context context){
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
}
