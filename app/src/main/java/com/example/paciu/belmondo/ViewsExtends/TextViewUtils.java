package com.example.paciu.belmondo.ViewsExtends;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.widget.TextView;

/**
 * Created by paciu on 15.03.2016.
 */
public class TextViewUtils {

    public static TextView createTextView(Context context, float spTextSize, String textColor, String text){
        TextView textView = new TextView(context);
        textView.setTextColor(Color.parseColor(textColor));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, spTextSize);
        textView.setText(text);

        return textView;
    }
}
