package com.example.paciu.belmondo.Chart;

import com.github.mikephil.charting.data.LineDataSet;

/**
 * Created by paciu on 13.03.2016.
 */
public interface ILineChartable extends ILineChartUpdatable {
    float getMaxYValue();

    LineDataSet getLineDataSet();

    String getDataSetLabel();

    float getCurrentValue();
    void setCurrentValue(float value);

    void moveChartIfEmtryListSizeGreaterThan(boolean shouldMove);
}
