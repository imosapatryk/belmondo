package com.example.paciu.belmondo.ViewsExtends;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.paciu.belmondo.R;
import com.example.paciu.belmondo.ViewAdapters.TimeIntervalAdapterItem;
import com.example.paciu.belmondo.Timer.TimeParts;
import com.example.paciu.belmondo.ViewAdapters.TimerIntervalAdapter;
import com.example.paciu.belmondo.Utils.UnitsUtils;

import java.util.ArrayList;

/**
 * Created by paciu on 15.03.2016.
 */
public class TimerCardView extends CardView{

    private LinearLayout horizontalContentLayout;
    private TextView timeTextView;
    private TextView milisTextView;

    private ListView intervalsListView;
    private TimerIntervalAdapter intervalsAdapter;

    public TimerCardView(Context context) {
        super(context);

        setup();
    }

    public TimerCardView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setup();
    }

    public TimerCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setup();
    }

    public void addInterval(TimeParts intervalTime){
        if(intervalsAdapter.isEmpty()){
            intervalsAdapter.add(new TimeIntervalAdapterItem(1, intervalTime.toString()));

            showAndAnimateIntervalsListView();
        }
        intervalsAdapter.insert(new TimeIntervalAdapterItem(intervalsAdapter.getCount() + 1, "0:00:00:0"), 0);
    }

    protected void showAndAnimateIntervalsListView(){
        intervalsListView.setVisibility(VISIBLE);
        animateTimerTextViewToSizeAlignedToIntervalsList();
    }

    public void removeInterval(int index){
        if(index >= 0 && index < intervalsAdapter.getCount()) {
            intervalsAdapter.remove(intervalsAdapter.getItem(index));
        }
    }

    public void removeAllItemsFromIntervalListView(){
        intervalsAdapter.removeAllItems();
    }

    protected void setup() {
        setupContent();
        setupIntervalsAdapter();

        intervalsListView.setAdapter(intervalsAdapter);
    }

    protected void setupContent( ){
        setupContentLayout();
        this.addView(horizontalContentLayout);

        setupTimeTextView();
        horizontalContentLayout.addView(timeTextView);

        setupMilisTextView();
        horizontalContentLayout.addView(milisTextView);

        setupIntervalsListView();
        horizontalContentLayout.addView(intervalsListView);
    }

    protected void setupContentLayout(){
        horizontalContentLayout = new LinearLayout(getContext());
        horizontalContentLayout.setOrientation(LinearLayout.HORIZONTAL);
        horizontalContentLayout.setGravity(Gravity.CENTER);

        horizontalContentLayout.setPadding(UnitsUtils.dpToInt(getContext(), 30), UnitsUtils.dpToInt(getContext(), 3), UnitsUtils.dpToInt(getContext(), 30), UnitsUtils.dpToInt(getContext(), 3));
        LinearLayout.LayoutParams horizontalContentLayoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        horizontalContentLayoutParams.gravity = Gravity.CENTER;

        horizontalContentLayout.setLayoutParams(horizontalContentLayoutParams);
    }

    protected void setupTimeTextView(){
        timeTextView = TextViewUtils.createTextView(getContext(), 40, "#A3A3A3", "0:00:00");
        timeTextView.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams timeTextViewLayoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        timeTextViewLayoutParams.gravity = Gravity.CENTER;


        timeTextView.setLayoutParams(timeTextViewLayoutParams);
    }

    protected void setupMilisTextView(){
        milisTextView = TextViewUtils.createTextView(getContext(), 30, "#A3A3A3", "0");
        milisTextView.setPadding(0, 0, 0, UnitsUtils.dpToInt(getContext(), 3));

        LinearLayout.LayoutParams milisTextViewLayoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        milisTextViewLayoutParams.gravity = Gravity.START | Gravity.BOTTOM;

        milisTextView.setLayoutParams(milisTextViewLayoutParams);
    }

    protected void setupIntervalsListView(){
        intervalsListView = new ListView(getContext());
        intervalsListView.setDivider(null);
        intervalsListView.setDividerHeight(0);
        intervalsListView.setVisibility(View.GONE);

        LinearLayout.LayoutParams intervalsListViewLayoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, UnitsUtils.dpToInt(getContext(), 40));
        intervalsListViewLayoutParams.setMarginStart(UnitsUtils.dpToInt(getContext(), 30));
        intervalsListViewLayoutParams.gravity = Gravity.CENTER;

        intervalsListView.setLayoutParams(intervalsListViewLayoutParams);
    }

    protected void setupIntervalsAdapter(){
        intervalsAdapter = new TimerIntervalAdapter(getContext(), R.layout.timer_interval_row, new ArrayList<TimeIntervalAdapterItem>());
    }

    protected void animateTimerTextViewsToItsOriginalSize(){
        float spTextSize = timeTextView.getTextSize() / getContext().getResources().getDisplayMetrics().scaledDensity;
        animateTimerTextSize(spTextSize, 40);
    }

    protected void animateTimerTextViewToSizeAlignedToIntervalsList(){
        animateTimerTextSize(40, 30);
    }

    protected void animateTimerTextSize(float from, float to){
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(from, to);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                timeTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, (float) valueAnimator.getAnimatedValue());
                milisTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, (float) valueAnimator.getAnimatedValue() - 10);
            }
        });

        valueAnimator.setDuration(400);
        valueAnimator.start();
    }

    public void update(TimeParts totalTimeParts, TimeParts intervalTimePars) {
        timeTextView.setText(totalTimeParts.getTimeStringWithoutMilis());
        milisTextView.setText(totalTimeParts.getMilisString());

        if(!intervalsAdapter.isEmpty()){
            intervalsAdapter.getItem(0).setTime(intervalTimePars.toString());
            intervalsAdapter.notifyDataSetChanged();
        }
    }

    public void resetAndHideIntervalListView() {
        removeAllItemsFromIntervalListView();
        intervalsListView.setVisibility(GONE);
        animateTimerTextViewsToItsOriginalSize();
    }

    public String getTimeText(){
        return this.timeTextView.getText().toString() + ":" + milisTextView.getText().toString();
    }
}
