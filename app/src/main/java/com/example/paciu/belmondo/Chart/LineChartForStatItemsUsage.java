package com.example.paciu.belmondo.Chart;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

/**
 * Created by paciu on 14.03.2016.
 */
public class LineChartForStatItemsUsage extends LineChartForStatItems{



    public LineChartForStatItemsUsage(Context context, LineChartForStat lineChartForStat){
        super(context, lineChartForStat);

        addLineChartItem(new LineChartItemForStat("Speed", Color.parseColor("#8ceaff")));
        addLineChartItem(new LineChartItemForStat("Heart rate", Color.parseColor("#ff8c8c")));
        setCheckedItem(0, true);
        updateVisibilityOnLineChart();
    }

    public void updateChart(float secondStep){
        this.getLineChart().update(secondStep);
    }
}
