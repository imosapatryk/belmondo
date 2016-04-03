package com.example.paciu.belmondo.Chart;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paciu on 13.03.2016.
 */
public abstract class LineChartItem implements ILineChartable {

    private String dataLabel;
    private LineDataSet lineDataSet;
    private List<Entry> entryList;
    private List<String> xValues;

    private float currentValue = 0;

    private float maxValue = 0;


    public LineChartItem(String dataLabel, int chartColor) {
        this.dataLabel = dataLabel;

        entryList = new ArrayList<>();
        entryList.add(new Entry(0, 0));

        xValues = new ArrayList<>();
        xValues.add("0");

        lineDataSet = new LineDataSet(entryList, dataLabel);
        setupLineDataSet(chartColor);
    }

    protected void setupLineDataSet(int chartColor){
        lineDataSet.setColor(chartColor);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setDrawValues(false);
    }

    protected void addNewEntryToEntryList(float value, int xIndex){
        Entry entry = new Entry(value, xIndex);
        entryList.add(entry);
    }

    protected void checkIsNewMaxValue(float value){
        if(value > maxValue){
            maxValue = value;
        }
    }

    @Override
    public float getCurrentValue() {
        return currentValue;
    }

    @Override
    public void setCurrentValue(float value) {
        this.currentValue = value;
    }

    @Override
    public float getMaxYValue() {
        return maxValue;
    }

    @Override
    public LineDataSet getLineDataSet() {
        return lineDataSet;
    }

    @Override
    public String getDataSetLabel() {
        return dataLabel;
    }

    protected List<Entry> getEntries(){
        return entryList;
    }
}
