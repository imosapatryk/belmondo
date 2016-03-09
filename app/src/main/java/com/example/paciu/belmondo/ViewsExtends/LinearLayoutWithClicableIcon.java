package com.example.paciu.belmondo.ViewsExtends;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.paciu.belmondo.R;
/*
import static com.example.paciu.belmondo.R.styleable.LinearLayoutWithClickableIcon;
import static com.example.paciu.belmondo.R.styleable.LinearLayoutWithClickableIcon_iconT;
import static com.example.paciu.belmondo.R.styleable.LinearLayoutWithClickableIcon_iconGravity;
*/
/**
 * Created by paciu on 08.03.2016.
 */
/*
public class LinearLayoutWithClicableIcon extends LinearLayout {
    private ImageView icon;
    @TargetApi(Build.VERSION_CODES.M)
    public LinearLayoutWithClicableIcon(Context context, AttributeSet attrs) {
        super(context, attrs);

        icon = new ImageView(context);

        TypedArray a = context.obtainStyledAttributes(attrs, LinearLayoutWithClickableIcon);
        Drawable drawable = a.getDrawable(LinearLayoutWithClickableIcon_iconT);


        if(drawable != null){
            LinearLayout la = new LinearLayout(context);
            la.setMinimumHeight(40);
            la.setMinimumWidth(40);
            this.addView(la);
            icon.setBackground(drawable);
            int ig = a.getInt(LinearLayoutWithClickableIcon_iconGravity, 2);

            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(40, 40,0.1f);
            p.gravity = ig;
            icon.setLayoutParams(p);



            this.addView(icon);

        }
    }

    public LinearLayoutWithClicableIcon(Context context) {
        super(context);
    }



}
*/