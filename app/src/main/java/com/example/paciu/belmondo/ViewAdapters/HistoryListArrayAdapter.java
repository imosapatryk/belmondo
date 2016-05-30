package com.example.paciu.belmondo.ViewAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.paciu.belmondo.R;
import com.example.paciu.belmondo.Result.Result;
import com.example.paciu.belmondo.SettingsActivity;

import java.util.List;

import javax.measure.quantity.Length;
import javax.measure.quantity.Velocity;
import javax.measure.unit.Unit;


/**
 * Created by paciu on 10.04.2016.
 */
public class HistoryListArrayAdapter extends ArrayAdapter<Result> {
    int resourceRow;

    public HistoryListArrayAdapter(Context context, int resource, List<Result> objects) {
        super(context, resource, objects);
        this.resourceRow = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = ((LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(resourceRow,parent, false);
        }
        final Result result = getItem(position);

        ImageView disciplineIcon = (ImageView)view.findViewById(R.id.history_row_discipline_icon);
        String disciplinePath = getContext().getPackageName() + ":drawable/" + result.getDisciplineName().toLowerCase();
        disciplineIcon.setImageDrawable(getContext().
                getDrawable(getContext().getResources().getIdentifier(disciplinePath,null,null)));

        TextView dateTextView = (TextView)view.findViewById(R.id.history_row_date_title);
        String[] splitDate = result.getDateTime().split(" ");
        dateTextView.setText(splitDate[1] + " "  + splitDate[0]);

        TextView duration = (TextView)view.findViewById(R.id.history_row_duration);
        duration.setText(result.getDuration());

        int speedUnitIndex = SettingsActivity.UnitsPreferenceFragment.getSpeedUnitIndex(getContext());
        String speedUnit = getContext().getResources().getStringArray(R.array.speedUnits)[speedUnitIndex];

        int distanceUnitIndex = SettingsActivity.UnitsPreferenceFragment.getDistanceUnitIndex(getContext());
        String distanceUnit = getContext().getResources().getStringArray(R.array.distanceUnit)[distanceUnitIndex];


        TextView avgspeed = (TextView)view.findViewById(R.id.history_row_avgspeed_tv);
        avgspeed.setText(String.format("%1$.2f", result.getAvgSpeed().doubleValue((Unit<Velocity>)Unit.valueOf(speedUnit))));

        TextView maxspeed = (TextView)view.findViewById(R.id.history_row_maxspeed_tv);
        maxspeed.setText(String.format("%1$.2f", result.getMaxSpeed().doubleValue((Unit<Velocity>)Unit.valueOf(speedUnit))));

        TextView distance = (TextView)view.findViewById(R.id.history_row_distance_tv);
        distance.setText(String.format("%1$.2f", result.getDistance().doubleValue((Unit<Length>)Unit.valueOf(distanceUnit))));

        TextView calories = (TextView)view.findViewById(R.id.history_row_calories_tv);
        calories.setText(String.valueOf(result.getCalories()));
        return view;
    }
}
