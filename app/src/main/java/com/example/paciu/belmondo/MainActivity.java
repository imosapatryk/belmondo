package com.example.paciu.belmondo;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.paciu.belmondo.Calories.*;
import com.example.paciu.belmondo.Chart.*;
import com.example.paciu.belmondo.Discipline.*;
import com.example.paciu.belmondo.Discipline.DisciplinesTypes.Walking;
import com.example.paciu.belmondo.Distance.*;
import com.example.paciu.belmondo.Fragments.FragmentMap;
import com.example.paciu.belmondo.Location.*;
import com.example.paciu.belmondo.Map.MapDrawer;
import com.example.paciu.belmondo.Map.MarkerInfo;
import com.example.paciu.belmondo.Network.NetworkChangedReceiver;
import com.example.paciu.belmondo.Profile.Profile;
import com.example.paciu.belmondo.Result.*;
import com.example.paciu.belmondo.Speed.*;
import com.example.paciu.belmondo.SportActivity.SportActivity;
import com.example.paciu.belmondo.SportActivity.SportActivityModelAdapter;
import com.example.paciu.belmondo.SqliteDataSource.Activities.SportActivityDataSource;
import com.example.paciu.belmondo.SqliteDataSource.Activities.SportActivityModel;
import com.example.paciu.belmondo.SqliteDataSource.Results.ResultDataSource;
import com.example.paciu.belmondo.SqliteDataSource.Results.ResultModel;
import com.example.paciu.belmondo.SwipeDetector.SwipeDetector;
import com.example.paciu.belmondo.Timer.*;
import com.example.paciu.belmondo.TwiceBackButton.*;
import com.example.paciu.belmondo.Utils.DurationUtils;
import com.example.paciu.belmondo.Utils.IteratorUtils;
import com.example.paciu.belmondo.ViewsExtends.*;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.model.LatLng;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.measure.unit.NonSI;
import javax.measure.unit.SI;


public class MainActivity extends AppCompatActivity
        implements TimerListener, NavigationView.OnNavigationItemSelectedListener,ResultCallback<LocationSettingsResult>,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, NetworkChangedReceiver.OnNetworkStateChangedListener,  SwipeDetector.SwipeListener, OnClickListener{

    private static final int REQUEST_GPS_TURN_ON = 1;
    private TwiceBackButtonAppControl twiceBackButtonAppFinisher;
    private Timer timer;
    private GoogleApiClient googleApiClient;
    private GoogleLocationRequester googleLocationRequester;
    private GoogleLocationProvider googleLocationProvider;
    private LocationDataProvider locationDataProvider;

    private DistanceDataProvider distanceDataProvider;
    private SpeedDataProvider speedDataProvider;
    private DisciplineProvider disciplineProvider;

    private DisciplinesProvider disciplinesProvider;

    private CaloriesDataProvider caloriesDataProvider;

    private TimerCardView timerCardView;
    private CaloriesCardView caloriesCardView;
    private SpeedCardView speedCardView;
    private DistanceCardView distanceCardView;
    private LineChartForStatItemsUsage lineChartForStatItemsUsage;
    private SwipeDetector swipeDetector;
    private NetworkChangedReceiver networkChangedReceiver;
    private FragmentMap mapFragment;
    private MenuItem chooseDisciplineMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadActivity();
    }
    MockLocationProvider mockLocation;
    protected void loadActivity(){
        timer = new TimerImpl(MainActivity.this, this);

        twiceBackButtonAppFinisher = new TwiceBackButtonAppControl(new AppFinisherCommand(this, getString(R.string.tap_twice_to_exit)));

        googleApiClient = new GoogleApiClient.Builder(getApplicationContext(), this, this).addApi(LocationServices.API).build();
        googleApiClient.connect();
        googleLocationProvider = new com.example.paciu.belmondo.Location.GoogleLocationProvider(googleApiClient);
        googleLocationRequester = new GoogleLocationRequester(googleApiClient, this, googleLocationProvider, googleLocationProvider.getLocationRequest());
        googleLocationRequester.checkLocationSettings(googleApiClient);
        //mockLocation = new MockLocationProvider();
        locationDataProvider = googleLocationProvider; // mockLocation;//googleLocationProvider;

        speedDataProvider = new SpeedDataProviderBasedOnLocation(locationDataProvider);
        speedDataProvider.setEnabled(false);

        disciplineProvider = new BelmondoDisciplineProvider();
        disciplineProvider.setCurrentDiscipline(new Walking(getApplicationContext()));
        disciplinesProvider = new BelmondoDisciplinesProvider(getApplicationContext(), new BelmondoDisciplineFactory(), R.array.disciplines);



        setupNetworkChangedReceiver();
        setupMapFragment(locationDataProvider, disciplineProvider);
        setupCardViews();
        setupSwipeDetector();
        setupToolbarAndNavigationDrawer(swipeDetector);
        setupTimerControlButtons();
        createLineChartForStatItemsUsage();
    }

    protected void setupNetworkChangedReceiver(){
        networkChangedReceiver = new NetworkChangedReceiver(getApplicationContext(), this);
        registerReceiver(networkChangedReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    protected void setupMapFragment(LocationObservable locationObservable, DisciplineObservable disciplineObservable){
        mapFragment = new FragmentMap(getApplicationContext(), locationObservable, disciplineObservable);
    }

    protected void setupCardViews(){
        timerCardView = (TimerCardView)findViewById(R.id.timer_card_view_for_stat);

        caloriesCardView = (CaloriesCardView)findViewById(R.id.frag_for_stat_card_view_calories);

        speedCardView = (SpeedCardView)findViewById(R.id.frag_for_stat_card_view_speed_place);
        speedCardView.setSpeedObservable(speedDataProvider);
        speedCardView.setDisplayUnit(SettingsActivity.UnitsPreferenceFragment.getSpeedUnitIndex(getApplicationContext()));
        speedCardView.updateContentText(true);

        distanceCardView = (DistanceCardView)findViewById(R.id.distance_card_view);
        distanceCardView.setDisplayUnit(SettingsActivity.UnitsPreferenceFragment.getDistanceUnitIndex(getApplicationContext()));
        distanceCardView.updateContentText(true);

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
        //drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        drawer.setOnTouchListener(swipeDetector);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        TextView navHeaderTitle = (TextView)navigationView.getHeaderView(0).findViewById(R.id.nav_header_title);
        com.example.paciu.belmondo.Profile.Profile profile = AppProfileManager.getInstance().getProfile();
        navHeaderTitle.setText(profile.getFirstName() + " " + profile.getLastName());
    }

    protected void setupTimerControlButtons(){
        final FloatingActionButtonPlayPause startPauseButton = (FloatingActionButtonPlayPause) findViewById(R.id.start_pause_fab);
        startPauseButton.setOnClickListener(this);

        final FloatingActionButton stopButton = (FloatingActionButton) findViewById(R.id.stop_fab);
        stopButton.setEnabled(false);
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
        } else if(getFragmentManager().getBackStackEntryCount() > 0){
            getFragmentManager().popBackStackImmediate();
        } else {
            twiceBackButtonAppFinisher.backPressed();
        }
    }

    protected void closeDrawerWhenBackPressed(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        chooseDisciplineMenuItem = menu.findItem(R.id.action_choose_discipline);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_choose_discipline && !mapFragment.isVisible()){
            new ChooseDisciplineAlertDialog(MainActivity.this, IteratorUtils.toList(disciplinesProvider.getDisciplinesIterator()), disciplineProvider).show(item);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_history) {
            showHistory();
        } else if (id == R.id.nav_settings){
            showSettings();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onConnected(Bundle bundle) {
        Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_SHORT).show();
        googleLocationProvider.requestLocationUpdates(googleApiClient);
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
                googleLocationRequester.switchResult(getApplicationContext(), resultCode);
                break;
            }
            default: break;
        }
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
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
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
            MapDrawer mapDrawer = mapFragment.getTrainingMap().getRouteDrawer().getMapDrawer();
            if(mapDrawer.getDrawnPointsCount() > 0){
                mapDrawer.addMarker(new MarkerInfo(mapDrawer.getMapDrawnPoints().get(mapDrawer.getDrawnPointsCount()-1), "STOP", 30));
            }
            ResultModel result = createResultFromStats();
            performOnResultReady(result);
        } else if(id == R.id.start_pause_fab){
            FloatingActionButtonPlayPause startPauseButton = (FloatingActionButtonPlayPause)findViewById(R.id.start_pause_fab);
            startPauseButton.clickMethodBasedOnStartPauseResumeObject(timer);
            FloatingActionButton stop = (FloatingActionButton)findViewById(R.id.stop_fab);
            stop.setEnabled(true);
        }
    }

    protected ResultModel createResultFromStats(){
        ResultModel result = new ResultModel();
        result.setDisciplineName(disciplineProvider.getCurrentDiscipline().getResourceUsedName());
        result.setUserId(AppProfileManager.getInstance().getProfile().getId());
        result.setDateTime(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
        result.setDuration(timerCardView.getTimeText());
        float avgSpeed = 0;
        int duration = DurationUtils.getDurationInSeconds(timerCardView.getTimeText(),"hh:mm:ss:m");
        if(duration != 0) {
            avgSpeed = (float) distanceDataProvider.getDistance().doubleValue(SI.METER) / duration;
        }
        result.setAvgSpeed(avgSpeed);
        result.setMaxSpeed((float)speedDataProvider.getMaxSpeed().doubleValue(SI.METERS_PER_SECOND));
        result.setDistance((float)distanceDataProvider.getDistance().doubleValue(SI.METER));
        result.setCalories(caloriesDataProvider.getCalories());
        result.setMapCoords(getMapFragment().getTrainingMap().getRouteDrawer().getMapDrawer().getMapDrawnPoints(), true);
        return result;
    }

    protected void showHistoryWithNewMAinActivityWhenBack(){
        Intent intent = new Intent(this, HistoryActivity.class);
        intent.putExtra("CREATE_NEW_MAINACTIVITY", true);
        startActivity(intent);
        finish();
    }

    protected void showHistory(){
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }

    protected void showSettings(){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    protected void performOnResultReady(ResultModel result){
        if(!AppProfileManager.getInstance().getProfile().isOnline()){
            performOfflineResultReady(result);
        }
    }

    protected void performOfflineResultReady(ResultModel result){
        ResultDataSource resultDataSource = new ResultDataSource(getApplicationContext());
        resultDataSource.openDB();
        resultDataSource.insertNew(result);
        resultDataSource.closeDB();
        showHistoryWithNewMAinActivityWhenBack();
    }

    @Override
    public void onTimerStarted(TimeParts timeParts) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                timerCardView.resetAndHideIntervalListView();
                chooseDisciplineMenuItem.setEnabled(false);
                distanceDataProvider = new DistanceDataProviderBaseOnLocation(locationDataProvider, disciplineProvider.getCurrentDiscipline().getDisciplineOnMapResolution());
                distanceCardView.setDistanceObservable(distanceDataProvider);
                speedDataProvider.setEnabled(true);
                mapFragment.getTrainingMap().getRouteDrawer().setEnabled(true);


                Profile profile = AppProfileManager.getInstance().getProfile();
                SportActivityDataSource sportActivityDataSource = new SportActivityDataSource(MainActivity.this);
                sportActivityDataSource.openDB();
                List<SportActivityModel> sportActivities = sportActivityDataSource.getAllByName(disciplineProvider.getCurrentDiscipline().getName());
                sportActivityDataSource.closeDB();
                List<SportActivity> sportActivityModelAdapters = new ArrayList<>();
                for(SportActivityModel s : sportActivities){
                    sportActivityModelAdapters.add(new SportActivityModelAdapter(s));
                }
                caloriesDataProvider = new CaloriesDataProviderInTime(profile, sportActivityModelAdapters, speedDataProvider, timer);
                caloriesDataProvider.setEnabled(true);
                caloriesCardView.setCaloriesObservable(caloriesDataProvider);

            }
        });
    }

    @Override
    public void onTimerStopped(TimeParts totalTimeParts, TimeParts intervalTimeParts) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                speedDataProvider.setEnabled(false);
                distanceDataProvider.setEnabled(false);
                caloriesDataProvider.setEnabled(false);
            }
        });
    }

    @Override
    public void onTimerPaused(TimeParts totalTimeParts, TimeParts intervalTimeParts) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                speedDataProvider.setEnabled(false);
                distanceDataProvider.setEnabled(false);
                caloriesDataProvider.setEnabled(false);
            }
        });
    }

    @Override
    public void onTimerResumed(TimeParts totalTimeParts, TimeParts intervalTimeParts) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                speedDataProvider.setEnabled(true);
                distanceDataProvider.setEnabled(true);
                caloriesDataProvider.setEnabled(true);
            }
        });
    }

    @Override
    public void on100MilisElapsed(final TimeParts totalTimeParts,final TimeParts intervalTimeParts) {
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
                lineChartForStatItemsUsage.setCurrentValueOfItem(getString(R.string.heart_rate), intervalTimeParts.getMilis() % 4);
                lineChartForStatItemsUsage.setCurrentValueOfItem(getString(R.string.speed), (float)speedDataProvider.getSpeed().doubleValue(NonSI.KILOMETERS_PER_HOUR));
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
    public void onNewIntervalCreated(final TimeParts lastTimeInterval) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                timerCardView.addInterval(lastTimeInterval);
            }
        });
    }
}




