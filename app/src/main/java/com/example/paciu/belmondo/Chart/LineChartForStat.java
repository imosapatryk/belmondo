package com.example.paciu.belmondo.Chart;

import android.content.Context;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by paciu on 12.03.2016.
 */
public class LineChartForStat implements IUpdateChart {

    public static final int X_VALUES_MAX_COUNT = 20;

    private LineChart lineChart;

    private float maxValue;

    List<ILineChartable> lineChartItemList;
    List<String> xValues;

    private float totalXSteps;

    private boolean xValueGreaterThan58 = false;

    public LineChartForStat(Context context, View parentView, int resourceLineChart) {
        lineChart = (LineChart) parentView.findViewById(resourceLineChart);

        setupChart();
        maxValue = 0;
        totalXSteps = 0;

        xValues = new ArrayList<>();
        xValues.add("0");

        lineChartItemList = new ArrayList<>();

        lineChart.setData(new LineData(xValues));
        lineChart.invalidate();

    }

    protected void setupChart() {
        lineChart.setDescription("");

        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);

        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getAxisLeft().setAxisMinValue(maxValue);

        lineChart.getAxisRight().setEnabled(false);
    }

    public void addLineChartItem(ILineChartable item) throws TooManyChartsException {
        if (lineChartItemList.size() < 3) {
            if (!lineChartItemList.contains(item)) {
                this.lineChart.getLineData().addDataSet(item.getLineDataSet());
                lineChartItemList.add(item);
            }
        } else {
            throw new TooManyChartsException("In one chart can be only 3 charts!");
        }
    }

    public void remoLineChartItem(ILineChartable item) {
        if (lineChartItemList.contains(item)) {
            lineChart.getLineData().removeDataSet(item.getLineDataSet());
            lineChartItemList.remove(item);
        }
    }

    public void removeLineChartItemAtIndex(int index) {
        try {
            this.lineChart.getLineData().removeDataSet(index);
            this.lineChartItemList.remove(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(float secondStep) {

        addNextStepToXValues(secondStep);

        if (xValues.size() > X_VALUES_MAX_COUNT) {
            xValues.remove(0);
        }

        for (ILineChartable item : lineChartItemList) {
            item.update(getLastIndexOfXValues());

            item.moveChartIfEmtryListSizeGreaterThan(xValues.size() == X_VALUES_MAX_COUNT);

            checkIfNewMAxValueAndSetXAxisMaxValueIfYes(item.getMaxYValue());
        }
        invalidateAndNotifyDataSetChanged();
    }



    protected void addNextStepToXValues(float nextStep) {
        totalXSteps += nextStep;
        String seconds = String.valueOf((int)totalXSteps % 60);
        String minutes = String.valueOf((int)totalXSteps / 60);

        if((int)totalXSteps % 60 < 10){
            seconds = "0" + seconds;
        }

        xValues.add(minutes + ":" + seconds);

    }

    protected int getLastIndexOfXValues(){
        return xValues.size() - 1;
    }

    protected void checkIfNewMAxValueAndSetXAxisMaxValueIfYes(float value) {
        if (value > maxValue) {
            maxValue = value;
            lineChart.getAxisLeft().setAxisMaxValue(value);
        }
    }

    protected void invalidateAndNotifyDataSetChanged() {
        lineChart.invalidate();
        lineChart.notifyDataSetChanged();
    }

    public static class TooManyChartsException extends Exception {
        public TooManyChartsException(String detailMessage) {
            super(detailMessage);
        }
    }
}
