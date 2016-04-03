package com.example.paciu.belmondo.ViewsExtends;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;

import static com.example.paciu.belmondo.R.styleable.CardViewForDisplayStatFeature;
import static com.example.paciu.belmondo.R.styleable.CardViewForDisplayStatFeature_contentText;
import static com.example.paciu.belmondo.R.styleable.CardViewForDisplayStatFeature_titleIcon;
import static com.example.paciu.belmondo.R.styleable.CardViewForDisplayStatFeature_titleText;

/**
 * Created by paciu on 11.03.2016.
 */
public class CardViewForDisplayStatFeature extends CardView {

    protected ImageView titleIcon;

    protected TextView titleTextView;
    protected TextView contentTextView;

    protected LinearLayout verticalMainLayout;
    protected LinearLayout horizontalTitleLayout;
    protected LinearLayout horizontalContentLayout;
    
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
        horizontalTitleLayoutParams.setMargins(0, 10, 0, 0);
        horizontalTitleLayout.setLayoutParams(horizontalTitleLayoutParams);


        horizontalContentLayout = new LinearLayout(context);
        horizontalContentLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams horizontalContentLayoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        horizontalContentLayoutParams.setMargins(5, 0, 5, 0);
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
            LinearLayout.LayoutParams titleIconLayoutParams = new LinearLayout.LayoutParams(30, 30);
            titleIconLayoutParams.gravity = Gravity.START | Gravity.TOP;
            titleIconLayoutParams.topMargin = 2;
            titleIconLayoutParams.leftMargin = 5;
            titleIcon.setLayoutParams(titleIconLayoutParams);
        }

        titleTextView = new TextView(context);
        titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        titleTextView.setTextColor(Color.parseColor("#97f8720b"));

        String title = attrArray.getString(CardViewForDisplayStatFeature_titleText);
        if(title == null){
            title = "Title";
        }

        titleTextView.setText(title);
        LinearLayout.LayoutParams titleTextViewLayoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0.98f);
        titleTextViewLayoutParams.gravity = Gravity.START;
        titleTextViewLayoutParams.leftMargin = 5;
        titleTextView.setLayoutParams(titleTextViewLayoutParams);
        if(titleIcon != null)
            horizontalTitleLayout.addView(titleIcon);
        horizontalTitleLayout.addView(titleTextView);


        contentTextView = new TextView(context);
        contentTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
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

        contentTextView.setPadding(0,0,0,5);
        horizontalTitleLayout.setPadding(0, 0, 0, 0);
        horizontalContentLayout.setPadding(0, 0, 0, 0);

    }

    public CardViewForDisplayStatFeature(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setContentText(String text){
        contentTextView.setText(text);
    }

    public void setTitleText(String text){
        titleTextView.setText(text);
    }

    public void setTitleTextViewOnClickListener(OnClickListener listener){
        if(titleTextView != null){
            titleTextView.setOnClickListener(listener);
        }
    }

}
