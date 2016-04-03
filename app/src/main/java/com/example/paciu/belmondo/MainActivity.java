package com.example.paciu.belmondo;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.paciu.belmondo.Chart.LineChartForStat;
import com.example.paciu.belmondo.Chart.LineChartForStatItemsUsage;
import com.example.paciu.belmondo.Fragments.FragmentMap;
import com.example.paciu.belmondo.SwipeDetector.SwipeDetector;
import com.example.paciu.belmondo.Threads.TimeParts;
import com.example.paciu.belmondo.Threads.Timer;
import com.example.paciu.belmondo.Threads.Timer.OnTimeElapsed;
import com.example.paciu.belmondo.ViewsExtends.CardViewForDisplayStatFeature;
import com.example.paciu.belmondo.ViewsExtends.DistanceCardView;
import com.example.paciu.belmondo.ViewsExtends.FloatingActionButtonPlayPause;
import com.example.paciu.belmondo.ViewsExtends.SpeedCardView;
import com.example.paciu.belmondo.ViewsExtends.TimerCardView;
import com.example.paciu.belmondo.google.GPSStatusMonitor;
import com.example.paciu.belmondo.google.GoogleLocationProvider;
import com.example.paciu.belmondo.google.LocationSourceProvider;
import com.example.paciu.belmondo.network.NetworkChangedReceiver;
import com.example.paciu.belmondo.utils.AnimationUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnTimeElapsed, ResultCallback<LocationSettingsResult>,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, Timer.OnTimerStateChanged, NetworkChangedReceiver.OnNetworkStateChangedListener,  SwipeDetector.SwipeListener, OnClickListener{

    private static final int REQUEST_GPS_TURN_ON = 1;

    private boolean backTappedTwoTimesForClose = false;

    private Timer timer;
    private TimerCardView timerCardView;
    private SpeedCardView speedCardView;
    private DistanceCardView distanceCardView;

    private LineChartForStatItemsUsage lineChartForStatItemsUsage;

    private GoogleApiClient googleApiClient;
    private GoogleLocationProvider locationProvider;
    private GPSStatusMonitor gpsMonitor;
    private SwipeDetector swipeDetector;
    private NetworkChangedReceiver networkChangedReceiver;
    private FragmentMap mapFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        googleApiClient = new GoogleApiClient.Builder(getApplicationContext(), this, this).addApi(LocationServices.API).build();
        googleApiClient.connect();
        locationProvider = new GoogleLocationProvider(getApplicationContext(), googleApiClient, this, null);

        setupGPSMonitor();
        setupNetworkChangedReceiver();
        setupMapFragment(locationProvider);
        setupTimer();
        setupCardViews();
        setupSwipeDetector();
        setupToolbarAndNavigationDrawer(swipeDetector);
        setupTimerControlButtons();

        createLineChartForStatItemsUsage();
    }

    protected void setupGPSMonitor(){
        gpsMonitor = new GPSStatusMonitor(getApplicationContext());
    }

    protected void setupNetworkChangedReceiver(){
        networkChangedReceiver = new NetworkChangedReceiver(getApplicationContext(), this);
        registerReceiver(networkChangedReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    protected void setupMapFragment(LocationSourceProvider sourceProvider){
        mapFragment = new FragmentMap(getApplicationContext(), sourceProvider);
    }

    protected void setupTimer(){
        timer = new Timer(MainActivity.this, this, this);
    }

    protected void setupCardViews(){
        timerCardView = (TimerCardView)findViewById(R.id.timer_card_view_for_stat);
        speedCardView = (SpeedCardView)findViewById(R.id.frag_for_stat_card_view_speed_place);
        distanceCardView = (DistanceCardView)findViewById(R.id.distance_card_view);

        CardViewForDisplayStatFeature chartCardView = (CardViewForDisplayStatFeature) findViewById(R.id.frag_for_stat_card_view_bottom_chart);
        chartCardView.setTitleTextViewOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                lineChartForStatItemsUsage.showChooseChartsDialog();
            }
        });
    }

    protected void setupSwipeDetector(){
        swipeDetector = new SwipeDetector(this);
    }

    protected void setupToolbarAndNavigationDrawer(SwipeDetector swipeDetector){
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_activity_toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        drawer.setOnTouchListener(swipeDetector);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    protected void setupTimerControlButtons(){
        final FloatingActionButtonPlayPause startPauseButton = (FloatingActionButtonPlayPause) findViewById(R.id.start_pause_fab);
        startPauseButton.setOnClickListener(this);

        final FloatingActionButton stopButton = (FloatingActionButton) findViewById(R.id.stop_fab);
        stopButton.setOnClickListener(this);

        final FloatingActionButton newIntervalButton = (FloatingActionButton) findViewById(R.id.newInterval_fab);
        newIntervalButton.setOnClickListener(this);
    }

    protected void createLineChartForStatItemsUsage(){
        View parentForChart = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        LineChartForStat lineChartForStat = new LineChartForStat(MainActivity.this, parentForChart, R.id.frag_stat_chart);
        lineChartForStatItemsUsage = new LineChartForStatItemsUsage(MainActivity.this, lineChartForStat);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangedReceiver);
    }

    @Override
    public void onStart(){
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            closeDrawerWhenBackPressed();
        } else if(getMapFragment().isVisible()){
            closeMapViewWhenBackPressed();
        } else if (!isBackTappedTwoTimesForClose()) {
           setWaitOnNextClickWhenBackPressed();
        } else if (isBackTappedTwoTimesForClose()) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    protected void closeDrawerWhenBackPressed(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        backTappedTwoTimesForClose = false;
    }

    protected void closeMapViewWhenBackPressed(){
        getFragmentManager().popBackStackImmediate();
        backTappedTwoTimesForClose = false;
    }

    protected void setWaitOnNextClickWhenBackPressed(){
        Toast.makeText(MainActivity.this, getString(R.string.double_back_to_exit), Toast.LENGTH_SHORT).show();
        backTappedTwoTimesForClose = true;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                backTappedTwoTimesForClose = false;
            }
        }, AnimationUtils.SHORT_DELAY);
    }

    private boolean isBackTappedTwoTimesForClose(){
        return backTappedTwoTimesForClose;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            timer.pause();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
        } else if (id == R.id.nav_history) {
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onConnected(Bundle bundle) {
        Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_SHORT).show();
        locationProvider.requestLocationUpdates(googleApiClient);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(getApplicationContext(), "Connection suspended", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(getApplicationContext(), "Connection Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResult(LocationSettingsResult locationSettingsResult) {
        Status status = locationSettingsResult.getStatus();

        switch (status.getStatusCode()){
            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED: {
                try {
                    status.startResolutionForResult(MainActivity.this, REQUEST_GPS_TURN_ON);
                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                }
                break;
            }
            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:{
                Toast.makeText(MainActivity.this, R.string.gps_unavailable, Toast.LENGTH_SHORT).show();
                Log.i("GPS_LOC_SETTINGS_RESULT", "Unavailable");
                break;
            }
            case LocationSettingsStatusCodes.SUCCESS:{
                Log.i("GPS_LOC_SETTINGS_RESULT", "Success");
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode){
            case REQUEST_GPS_TURN_ON:{
                locationProvider.switchResult(getApplicationContext(), resultCode);
                break;
            }
            default: break;
        }

    }

    @Override
    public void on100MilisElapsed(final TimeParts totalTimeParts, final TimeParts intervalTimeParts) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                timerCardView.update(totalTimeParts, intervalTimeParts);
            }
        });
    }

    @Override
    public void onSecondElapsed(TimeParts totalTimeParts, final TimeParts intervalTimeParts) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                speedCardView.setSpeedInMetresPerSecond(locationProvider.getSpeedInMetresPerSecond());
                distanceCardView.setDistanceInMetres(getMapFragment().getTrainingMap().getMapDrawer().getDistanceDrawn().getDistance());

                lineChartForStatItemsUsage.setCurrentValueOfItem(getString(R.string.heart_rate), intervalTimeParts.getMilis() % 4);
                lineChartForStatItemsUsage.setCurrentValueOfItem(getString(R.string.speed), speedCardView.getSpeedInMetresPerSecond().toKilometresPerHour());
                lineChartForStatItemsUsage.updateChart(1);
            }
        });
    }

    @Override
    public void onMinuteElapsed(TimeParts totalTimeParts, TimeParts intervalTimeParts) {

    }

    @Override
    public void onHourElapsed(TimeParts totalTimeParts, TimeParts intervalTimeParts) {

    }

    @Override
    public void onNewIntervalCreated(final TimeParts lastTimePartInterval) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                timerCardView.addInterval(lastTimePartInterval);
            }
        });
    }

    @Override
    public void onTimerStarted(TimeParts timeParts) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                timerCardView.resetAndHideIntervalListView();
                getMapFragment().getTrainingMap().getMapDrawer().startDrawing();
            }
        });
    }

    @Override
    public void onTimerStopped(TimeParts totalTimeParts, TimeParts intervalTimeParts) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getMapFragment().getTrainingMap().getMapDrawer().stopDrawing();
            }
        });
    }

    @Override
    public void onTimerPaused(TimeParts totalTimeParts, TimeParts intervalTimeParts) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getMapFragment().getTrainingMap().getMapDrawer().pauseDrawing();
            }
        });
    }

    @Override
    public void onTimerResumed(TimeParts totalTimeParts, TimeParts intervalTimeParts) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getMapFragment().getTrainingMap().getMapDrawer().resumeDrawing();
            }
        });
    }

    @Override
    public void onInternetConnected(){
        googleApiClient.connect();
    }

    @Override
    public void onInternetConnectionLost() {
    }

    @Override
    public void onSwipeLeft(View v, MotionEvent e) {
        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        if(drawerLayout.isDrawerOpen(Gravity.START)){
            drawerLayout.closeDrawer(Gravity.START);
        } else {
            showMap();
        }
    }

    protected void showMap(){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.frag_slide_in_right, R.anim.frag_slide_out_left, R.anim.frag_slide_in_left, R.anim.frag_slide_out_right)
                .replace(R.id.drawer_layout, getMapFragment())
                .addToBackStack("MAP_FRAGMENT")
                .commit();
    }

    protected FragmentMap getMapFragment(){
        return mapFragment;
    }

    @Override
    public void onSwipeRight(View v, MotionEvent e) {
        Fragment fragment = getFragmentManager().findFragmentByTag("MAP_FRAGMENT");
        if(fragment == null || !fragment.isVisible()){
            DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
            drawer.openDrawer(GravityCompat.START);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == R.id.newInterval_fab){
            timer.startNewIntervalIfNotPaused();
        } else if(id == R.id.stop_fab){
            timer.stop();
            ((FloatingActionButtonPlayPause)(findViewById(R.id.start_pause_fab))).setPlayState(false);
        } else if(id == R.id.start_pause_fab){
            FloatingActionButtonPlayPause startPauseButton = (FloatingActionButtonPlayPause)findViewById(R.id.start_pause_fab);
            startPauseButton.clickMethodBasedOnStartPauseResumeObject(timer);
        }
    }

}



