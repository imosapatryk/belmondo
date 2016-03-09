package com.example.paciu.belmondo.ViewsExtends;

/**
 * Created by paciu on 09.03.2016.
 */
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;

import static com.example.paciu.belmondo.R.styleable.CardViewForDisplayStatFeature;
import static com.example.paciu.belmondo.R.styleable.CardViewForDisplayStatFeature_chartIcon;
import static com.example.paciu.belmondo.R.styleable.CardViewForDisplayStatFeature_chartIconClicked;
import static com.example.paciu.belmondo.R.styleable.CardViewForDisplayStatFeature_titleIcon;
import static com.example.paciu.belmondo.R.styleable.CardViewForDisplayStatFeature_titleText;
import static com.example.paciu.belmondo.R.styleable.CardViewForDisplayStatFeature_contentText;

public class CardViewForDisplayStatFeature extends CardView {

    private ImageView chartIcon;
    private Drawable chartIconNotClicked;
    private Drawable chartIconClicked;
    private ImageView titleIcon;

    private TextView titleTextView;
    private TextView contentTextView;

    private LinearLayout verticalMainLayout;
    private LinearLayout horizontalTitleLayout;
    private LinearLayout horizontalContentLayout;

    private boolean chartClicked = false;

    public CardViewForDisplayStatFeature(Context context) {
        super(context);
    }

    public CardViewForDisplayStatFeature(Context context, AttributeSet attrs) {
        super(context, attrs);

        verticalMainLayout = new LinearLayout(context);
        verticalMainLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams verticalMainLayoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        verticalMainLayout.setLayoutParams(verticalMainLayoutParams);


        horizontalTitleLayout = new LinearLayout(context);
        horizontalTitleLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams horizontalTitleLayoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        horizontalTitleLayoutParams.setMargins(5,10,5,0);
        horizontalTitleLayout.setLayoutParams(horizontalTitleLayoutParams);

        horizontalContentLayout = new LinearLayout(context);
        horizontalContentLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams horizontalContentLayoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        horizontalContentLayoutParams.setMargins(5,0,5,0);
        horizontalContentLayoutParams.gravity = Gravity.CENTER;
        horizontalContentLayout.setLayoutParams(horizontalTitleLayoutParams);


        this.addView(verticalMainLayout);


        verticalMainLayout.addView(horizontalTitleLayout);
        verticalMainLayout.addView(horizontalContentLayout);

        TypedArray attrArray = context.obtainStyledAttributes(attrs, CardViewForDisplayStatFeature);

        Drawable titleIconDrawable = attrArray.getDrawable(CardViewForDisplayStatFeature_titleIcon);
        if(titleIconDrawable != null){
            titleIcon = new ImageView(context);
            titleIcon.setImageDrawable(titleIconDrawable);
            LinearLayout.LayoutParams titleIconLayoutParams = new LinearLayout.LayoutParams(50, 50, 0.1f);
            titleIconLayoutParams.gravity = Gravity.START | Gravity.TOP;
            titleIcon.setLayoutParams(titleIconLayoutParams);
        }

        titleTextView = new TextView(context);
        titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        titleTextView.setTextColor(Color.parseColor("#B3B3B3"));

        String title = attrArray.getString(CardViewForDisplayStatFeature_titleText);
        if(title == null){
            title = "Title";
        }

        titleTextView.setText(title);
        LinearLayout.LayoutParams titleTextViewLayoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0.98f);
        titleTextViewLayoutParams.gravity = Gravity.START;
        titleTextView.setLayoutParams(titleTextViewLayoutParams);



        chartIconNotClicked = attrArray.getDrawable(CardViewForDisplayStatFeature_chartIcon);
        if(chartIconNotClicked != null){

            chartIcon = new ImageView(context);
            chartIcon.setImageDrawable(chartIconNotClicked);
            LinearLayout.LayoutParams chartIconLayoutParams = new LinearLayout.LayoutParams(50, 50, 0.1f);

            chartIconLayoutParams.gravity = Gravity.END | Gravity.TOP;
            chartIcon.setLayoutParams(chartIconLayoutParams);
        }

        chartIconClicked = attrArray.getDrawable(CardViewForDisplayStatFeature_chartIconClicked);

        if(titleIcon != null)
            horizontalTitleLayout.addView(titleIcon);
        horizontalTitleLayout.addView(titleTextView);
        if(chartIcon != null)
            horizontalTitleLayout.addView(chartIcon);

        contentTextView = new TextView(context);
        contentTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
        contentTextView.setTextColor(Color.parseColor("#A3A3A3"));
        contentTextView.setGravity(Gravity.CENTER);

        String content = attrArray.getString(CardViewForDisplayStatFeature_contentText);
        if(content == null){
            content = "Content text here";
        }

        contentTextView.setText(content);

        LinearLayout.LayoutParams contentTextViewLayoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 0.98f);
        contentTextViewLayoutParams.gravity = Gravity.CENTER;
        contentTextView.setLayoutParams(titleTextViewLayoutParams);

        horizontalContentLayout.addView(contentTextView);
    }

    public CardViewForDisplayStatFeature(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setChartIconOnClickListener(OnClickListener listener){
        if(chartIcon != null){
            chartIcon.setOnClickListener(listener);
        }
    }

    public void setContentText(String text){
        contentTextView.setText(text);
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
