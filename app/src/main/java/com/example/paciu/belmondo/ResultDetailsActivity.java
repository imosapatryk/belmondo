package com.example.paciu.belmondo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.paciu.belmondo.Discipline.BelmondoDisciplineFactory;
import com.example.paciu.belmondo.Discipline.Discipline;
import com.example.paciu.belmondo.Result.LatLngSerializableImpl;
import com.example.paciu.belmondo.Result.Result;
import com.example.paciu.belmondo.ResultDetails.ResultDetailsMapViewController;
import com.example.paciu.belmondo.Utils.DurationUtils;
import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.ShareOpenGraphObject;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.widget.ShareButton;
import com.google.android.gms.maps.MapView;

import org.jscience.physics.amount.Amount;

import javax.measure.quantity.Length;
import javax.measure.quantity.Velocity;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

public class ResultDetailsActivity extends AppCompatActivity {
    public static String RESULT = "RESULT";
    private ResultDetailsMapViewController resultDetailsMapViewController;
    private Result result;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_details);

        result = (Result)getIntent().getSerializableExtra(RESULT);
        setupToolbar(result.getDisciplineName());

        Discipline discipline = new BelmondoDisciplineFactory().create(this, result.getDisciplineName());
        setupComponents(discipline);
    }

    protected void setupComponents(Discipline discipline){
        if(discipline.hasMap()) {
            MapView mapView = (MapView) findViewById(R.id.result_details_map_fragment);
            resultDetailsMapViewController = new ResultDetailsMapViewController(mapView, result);
        }
        setDisciplineIcon(discipline);
        setDateText(result.getDateTime());
        setDurationText(result.getDuration());
        setSpeedText(result.getMaxSpeed(), result.getAvgSpeed());
        setDistanceText(result.getDistance());
        setCaloriesText(result.getCalories());
        setupShareContent();
    }

    public void setDisciplineIcon(Discipline discipline) {
        ImageView disciplineIcon = (ImageView)findViewById(R.id.history_row_discipline_icon);
        disciplineIcon.setImageResource(discipline.getIconResId());
    }

    public void setDateText(String dateTime) {
        TextView dateTextView = (TextView)findViewById(R.id.history_row_date_title);
        String[] splitDate = dateTime.split(" ");
        dateTextView.setText(splitDate[1] + " "  + splitDate[0]);
    }

    public void setDurationText(String text){
        TextView duration = (TextView)findViewById(R.id.history_row_duration);
        duration.setText(text);
    }

    public void setSpeedText(Amount<Velocity> maxSpeed, Amount<Velocity> avgSpeed){

        int speedUnitIndex = SettingsActivity.UnitsPreferenceFragment.getSpeedUnitIndex(this);
        String speedUnit = getResources().getStringArray(R.array.speedUnits)[speedUnitIndex];

        TextView avgSpeedText = (TextView)findViewById(R.id.history_row_avgspeed_tv);
        avgSpeedText.setText(String.format("%1$.2f", avgSpeed.doubleValue((Unit<Velocity>) Unit.valueOf(speedUnit))));

        TextView maxSpeedText = (TextView)findViewById(R.id.history_row_maxspeed_tv);
        maxSpeedText.setText(String.format("%1$.2f", maxSpeed.doubleValue((Unit<Velocity>) Unit.valueOf(speedUnit))));
    }

    public void setDistanceText(Amount<Length> distance){
        int distanceUnitIndex = SettingsActivity.UnitsPreferenceFragment.getDistanceUnitIndex(this);
        String distanceUnit = getResources().getStringArray(R.array.distanceUnit)[distanceUnitIndex];
        TextView distanceTextView = (TextView)findViewById(R.id.history_row_distance_tv);
        distanceTextView.setText(String.format("%1$.2f", distance.doubleValue((Unit<Length>) Unit.valueOf(distanceUnit))));
    }

    public void setCaloriesText(int calories){
        TextView caloriesTextView = (TextView)findViewById(R.id.history_row_calories_tv);
        caloriesTextView.setText(String.valueOf(calories));
    }

    protected void setupShareContent(){
        ShareButton shareButton = (ShareButton)findViewById(R.id.fb_share_button);
        ShareOpenGraphObject.Builder builder = new ShareOpenGraphObject.Builder()
                .putString("og:type", "fitness.course")
                .putString("og:title", "Belmondo")
                .putInt("fitness:duration:value", DurationUtils.getDurationInSeconds(result.getDuration(),"hh:mm:ss:m"))
                .putString("fitness:duration:units", "s")
                .putDouble("fitness:distance:value", result.getDistance().doubleValue(SI.KILOMETER))
                .putString("fitness:distance:units", "km")
                .putDouble("fitness:speed:value", result.getMaxSpeed().doubleValue(SI.METERS_PER_SECOND))
                .putString("fitness:speed:units", "m/s")
                .putInt("fitness:calories", result.getCalories());

        if(result.getMapCoords() != null) {
            for (int i = 0; i < result.getMapCoords().size(); i++) {
                LatLngSerializableImpl ll = result.getMapCoords().get(i);
                builder.putDouble("fitness:metrics[" + i + "]:location:latitude", ll.latitude);
                builder.putDouble("fitness:metrics[" + i + "]:location:longitude", ll.longitude);
            }
        } else {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg_create_profile);
            SharePhoto photo = new SharePhoto.Builder().setBitmap(bitmap).setUserGenerated(true).build();
            builder.putPhoto("og:image", photo);
        }
        ShareOpenGraphObject object = builder.build();
        ShareOpenGraphAction action = new ShareOpenGraphAction.Builder()
                .setActionType(getFitnessActionTypeBasedOnDiscipline())
                .putObject("fitness:course", object)
                .build();
        ShareOpenGraphContent content = new ShareOpenGraphContent.Builder()
                .setPreviewPropertyName("fitness:course")
                .setAction(action)
                .build();

        shareButton.setShareContent(content);
    }

    protected String getFitnessActionTypeBasedOnDiscipline(){
        String discipline = result.getDisciplineName().toLowerCase();
        if(discipline.equals("walking")){
            return "fitness.walks";
        } else if(discipline.equals("running")){
            return "fitness.runs";
        } else if(discipline.equals("cycling")){
            return "fitness.bikes";
        }
        return "";
    }

    protected void setupToolbar(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.result_details_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return true;
    }
}
