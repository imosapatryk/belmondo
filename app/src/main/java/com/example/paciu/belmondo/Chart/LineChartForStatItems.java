package com.example.paciu.belmondo.Chart;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;

import com.example.paciu.belmondo.R;
import com.github.mikephil.charting.charts.LineChart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paciu on 13.03.2016.
 */
public class LineChartForStatItems {

    private List<LineChartItemForStat> lineChartsItems;
    private List<Boolean> checkedItems;
    private LineChartForStat lineChart;
    private Context context;

    public LineChartForStatItems(Context context, LineChartForStat lineChart){
        lineChartsItems = new ArrayList<>();
        checkedItems = new ArrayList<>();

        this.context = context;
        this.lineChart = lineChart;
    }

    public void updateVisibilityOnLineChart(){
        if(lineChart != null){
            for(int i = 0; i < checkedItems.size(); i++){
                decideToAddOrRemoveItemFromChart(lineChartsItems.get(i), checkedItems.get(i));
            }
        }
    }

    public void decideToAddOrRemoveItemFromChart(LineChartItemForStat item, boolean value){
        if(value){
            try {
                lineChart.addLineChartItem(item);
            } catch (LineChartForStat.TooManyChartsException e) {
                e.printStackTrace();
            }
        } else {
            lineChart.remoLineChartItem(item);
        }
    }

    public void addLineChartItem(LineChartItemForStat item){
        if(!lineChartsItems.contains(item)){
            lineChartsItems.add(item);
            checkedItems.add(false);
        }
    }

    public void setCurrentValueOfItem(String dataLabel, float value){
        for (LineChartItemForStat item : lineChartsItems) {
            if(item.getDataSetLabel().equals(dataLabel)){
                item.setCurrentValue(value);
                break;
            }
        }
    }

    public boolean [] getCheckedItems(){
        boolean [] values = new boolean[checkedItems.size()];
        for(int i = 0; i < checkedItems.size(); i++){
            values[i] = checkedItems.get(i);
        }
        return values;
    }

    public void setCheckedItem(int index, boolean value){
        if(index < checkedItems.size()){
            checkedItems.set(index, value);
        }
    }

    public CharSequence [] getItemsLabels(){
        CharSequence [] seq = new CharSequence[lineChartsItems.size()];
        for(int i = 0; i < lineChartsItems.size(); i++){
            seq[i] = lineChartsItems.get(i).getDataSetLabel();
        }
        return seq;
    }

    public void showChooseChartsDialog(){
        new AlertDialog.Builder(getContext())
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        updateVisibilityOnLineChart();
                    }
                })
                .setTitle(R.string.choose_chart_title)
                .setMultiChoiceItems(getItemsLabels(), getCheckedItems(), new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        setCheckedItem(which, isChecked);
                    }
                }).show();
    }

    public Context getContext() {
        return context;
    }

    public void removeLineChartItem(LineChartItemForStat item){
        int index = lineChartsItems.indexOf(item);
        if(index > 0){
            lineChartsItems.remove(index);
            checkedItems.remove(index);
            lineChart.remoLineChartItem(item);
        }
    }

    public LineChartForStat getLineChart() {
        return lineChart;
    }
}
