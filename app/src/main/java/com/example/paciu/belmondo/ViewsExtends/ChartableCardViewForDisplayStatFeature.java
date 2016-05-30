package com.example.paciu.belmondo.ViewsExtends;

/**
 * Created by paciu on 09.03.2016.
 */
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.LineChart;

import static com.example.paciu.belmondo.R.styleable.ChartableCardViewForDisplayStatFeature;
import static com.example.paciu.belmondo.R.styleable.ChartableCardViewForDisplayStatFeature_chartIcon;
import static com.example.paciu.belmondo.R.styleable.ChartableCardViewForDisplayStatFeature_chartIconClicked;


public class ChartableCardViewForDisplayStatFeature extends CardViewForDisplayStatFeature {

    private ImageView chartIcon;
    private Drawable chartIconNotClicked;
    private Drawable chartIconClicked;


    private boolean chartClicked = false;

    public ChartableCardViewForDisplayStatFeature(Context context) {
        super(context);
    }

    public ChartableCardViewForDisplayStatFeature(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray attrArray = context.obtainStyledAttributes(attrs, ChartableCardViewForDisplayStatFeature);


        chartIconNotClicked = attrArray.getDrawable(ChartableCardViewForDisplayStatFeature_chartIcon);
        if(chartIconNotClicked != null){

            chartIcon = new ImageView(context);
            chartIcon.setImageDrawable(chartIconNotClicked);
            LinearLayout.LayoutParams chartIconLayoutParams = new LinearLayout.LayoutParams(45, 45, 0.1f);
            chartIconLayoutParams.setMargins(0,0,0,0);
            chartIconLayoutParams.gravity = Gravity.END | Gravity.TOP;
            chartIcon.setLayoutParams(chartIconLayoutParams);
        }

        chartIconClicked = attrArray.getDrawable(ChartableCardViewForDisplayStatFeature_chartIconClicked);

        if(chartIcon != null)
            horizontalTitleLayout.addView(chartIcon);

    }

    public ChartableCardViewForDisplayStatFeature(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setChartIconOnClickListener(OnClickListener listener){
        if(chartIcon != null){
            chartIcon.setOnClickListener(listener);
        }
    }

    public void setChartIconToClicked(){
        if(chartIcon != null && chartIconClicked != null){
            chartIcon.setImageDrawable(chartIconClicked);
        }
    }

    public void setChartIconToNotClicked(){
        if(chartIcon != null && chartIconNotClicked != null){
            chartIcon.setImageDrawable(chartIconNotClicked);
        }
    }

    public boolean isChartClicked() {
        return chartClicked;
    }

    public void setChartClicked(boolean chartClicked) {
        if(chartClicked){
            setChartIconToClicked();
        } else setChartIconToNotClicked();

        this.chartClicked = chartClicked;
    }

    public void toggleContentWithChart(LineChart chart){
        if(this.horizontalContentLayout.getChildCount() > 0) {
            this.horizontalContentLayout.removeViewAt(0);
            this.horizontalContentLayout.addView(chart);
        }

    }
}
