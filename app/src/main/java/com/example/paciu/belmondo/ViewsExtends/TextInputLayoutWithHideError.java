package com.example.paciu.belmondo.ViewsExtends;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by paciu on 06.03.2016.
 */
public class TextInputLayoutWithHideError extends TextInputLayout {
    public TextInputLayoutWithHideError(Context context) {
        super(context);
    }

    public TextInputLayoutWithHideError(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextInputLayoutWithHideError(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setError(CharSequence error) {
        super.setError(error);

        if(getChildCount() == 2){
            if(error == null){
                getChildAt(1).setVisibility(View.GONE);
            } else {
                getChildAt(1).setVisibility(View.VISIBLE);
            }
        }
    }

    public boolean hasError(){
        return getError() != null;
    }
}
