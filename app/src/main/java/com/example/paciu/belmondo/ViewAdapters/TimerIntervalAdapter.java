package com.example.paciu.belmondo.ViewAdapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.paciu.belmondo.R;

import java.util.List;

/**
 * Created by paciu on 12.03.2016.
 */
public class TimerIntervalAdapter extends ArrayAdapter<TimeIntervalAdapterItem> {

    private int layoutResource;

    public TimerIntervalAdapter(Context context, int layoutResource, List<TimeIntervalAdapterItem> objects) {
        super(context, layoutResource, objects);

        this.layoutResource = layoutResource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final TimeIntervalAdapterItem item = this.getItem(position);

        View view = convertView;
        if(view == null){
            LayoutInflater inflater = ((Activity)getContext()).getLayoutInflater();
            view = inflater.inflate(layoutResource, parent, false);
        }

        final TextView numberTextView = (TextView)view.findViewById(R.id.time_interval_number);
        numberTextView.setText("#" + item.getNumber());

        final TextView timeTextView = (TextView)view.findViewById(R.id.time_interval_time);
        timeTextView.setText(item.getTime());

        if(item.ifAnimate()){
            Animation animation = AnimationUtils.loadAnimation((Activity)getContext(), android.R.anim.fade_in);
            view.startAnimation(animation);
            item.setAnimate(false);
        }
        return view;
    }

    public void removeAllItems(){
        int count = getCount();
        for(int i = 0; i < count; i++){
            remove(this.getItem(0));
        }
    }
}
