package com.example.paciu.belmondo.ViewsExtends;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by paciu on 31.03.2016.
 */
public class LinearLayoutForFragAnim extends LinearLayout {

    public LinearLayoutForFragAnim(Context context) {
        super(context);
    }

    public LinearLayoutForFragAnim(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LinearLayoutForFragAnim(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LinearLayoutForFragAnim(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public float getXFraction(){
        return getX()/getWidth();
    }

    public void setXFraction(float xFraction){
        final int width = getWidth();
        setX((width > 0) ? xFraction * width : -9999);
    }

}
