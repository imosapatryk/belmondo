package com.example.paciu.belmondo.ViewAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.paciu.belmondo.Discipline.Discipline;
import com.example.paciu.belmondo.R;

import java.util.List;

/**
 * Created by paciu on 08.04.2016.
 */
public class ArrayAdapterWithIcons extends ArrayAdapter<Discipline> {
    int resourceRow;
    public ArrayAdapterWithIcons(Context context, int resource, List<Discipline> disciplines) {
        super(context, resource, disciplines);
        this.resourceRow = resource;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            view = ((LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(resourceRow, parent, false);
        }
        ImageView imageIcon = (ImageView)view.findViewById(R.id.discipline_row_imageview);
        imageIcon.setImageDrawable(getContext().getDrawable(getItem(position).getIconResId()));

        TextView textView = (TextView)view.findViewById(R.id.discipline_row_textview);
        textView.setText(getItem(position).getName());
        return view;
    }
}
