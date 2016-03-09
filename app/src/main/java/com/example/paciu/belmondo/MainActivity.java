package com.example.paciu.belmondo;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paciu.belmondo.DataSource.Profiles.Profile;
import com.example.paciu.belmondo.Threads.Timer.OnTimeElapsed;
import com.example.paciu.belmondo.Threads.TimeParts;
import com.example.paciu.belmondo.Threads.Timer;
import com.example.paciu.belmondo.ViewsExtends.CardViewForDisplayStatFeature;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final long SHORT_DELAY = 2000;
    private Profile profile;
    private boolean backTappedTwoTimesForClose = false;

    private Timer timer;

    private TextView tv;
    private TextView tv_milis;
    static int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_activity_toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        tv = (TextView)findViewById(R.id.frag_for_stat_time_text_view);
        tv_milis = (TextView)findViewById(R.id.frag_for_stat_milis_text_view);


        timer = new Timer(MainActivity.this);
        timer.setOnTimeElapsedListener(new OnTimeElapsed() {
            @Override
            public void on100MilisElapsed(final TimeParts timeParts) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText(timeParts.getTimeStringWithoutMilis());
                        tv_milis.setText(timeParts.getMilisString());
                    }
                });
            }

            @Override
            public void onSecondElapsed(TimeParts timeParts) {

            }

            @Override
            public void onMinuteElapsed(TimeParts timeParts) {

            }

            @Override
            public void onHourElapsed(TimeParts timeParts) {

            }
        });
        timer.start();

        final CardViewForDisplayStatFeature cw = (CardViewForDisplayStatFeature)findViewById(R.id.frag_for_stat_card_view);
        cw.setChartIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cw.setChartClicked(!cw.isChartClicked());
                if (cw.isChartClicked())
                    count++;
                if (count == 1)
                    cw.setContentText(" - km/h");
                else
                    cw.setContentText(count + " km/h");
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            backTappedTwoTimesForClose = false;
        } else if(!backTappedTwoTimesForClose) {
            Toast.makeText(MainActivity.this, getString(R.string.double_back_to_exit), Toast.LENGTH_SHORT).show();
            backTappedTwoTimesForClose = true;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    backTappedTwoTimesForClose = false;
                }
            }, SHORT_DELAY);

        } else if(backTappedTwoTimesForClose){
            finish();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            timer.pause();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            timer.resume();
        } else if (id == R.id.nav_history) {
            timer.start();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

}
