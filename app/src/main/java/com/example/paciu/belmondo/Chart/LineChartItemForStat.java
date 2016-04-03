package com.example.paciu.belmondo.Chart;

import com.github.mikephil.charting.data.Entry;

/**
 * Created by paciu on 13.03.2016.
 */
public class LineChartItemForStat extends LineChartItem {

    public LineChartItemForStat(String dataLabel, int chartColor) {
        super(dataLabel, chartColor);
    }

    @Override
    public void update(int xIndex) {
        checkIsNewMaxValue(getCurrentValue());

        addNewEntryToEntryList(getCurrentValue(), xIndex);
    }

    @Override
    public void moveChartIfEmtryListSizeGreaterThan(boolean shouldMove) {
        if(shouldMove){
            if(getEntries().size() > LineChartForStat.X_VALUES_MAX_COUNT) {
                getEntries().remove(0);
            }
            for(Entry e : getEntries()){
                e.setXIndex(e.getXIndex() - 1);
            }
        }
    }
}
