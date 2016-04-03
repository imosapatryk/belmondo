package com.example.paciu.belmondo.ViewsExtends;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by paciu on 31.03.2016.
 */
public class ContentMainLayout extends LinearLayout {
    public ContentMainLayout(Context context) {
        super(context);
    }

    public ContentMainLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ContentMainLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ContentMainLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }
}
