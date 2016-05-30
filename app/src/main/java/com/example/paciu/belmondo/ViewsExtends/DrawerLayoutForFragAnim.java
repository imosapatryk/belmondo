package com.example.paciu.belmondo.ViewsExtends;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;

/**
 * Created by paciu on 31.03.2016.
 */
public class DrawerLayoutForFragAnim extends DrawerLayout {
    public DrawerLayoutForFragAnim(Context context) {
        super(context);
    }

    public DrawerLayoutForFragAnim(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawerLayoutForFragAnim(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public float getXFraction(){
        return getX()/getWidth();
    }

    public void setXFraction(float xFraction){
        final int width = getWidth();
        setX((width > 0) ? xFraction * width : -9999);
    }

}
