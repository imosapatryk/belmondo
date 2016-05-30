package com.example.paciu.belmondo.Logging.LoginOffline;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

/**
 * Created by paciu on 05.04.2016.
 */
public class ButtonWithInternalClickBase extends Button {

    private OnClickListener externalOnClickListener;
    private OnClickListener internalOnClickListener;

    public ButtonWithInternalClickBase(Context context) {
        super(context);
        setupOnClickListener();
    }

    public ButtonWithInternalClickBase(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupOnClickListener();
    }

    public ButtonWithInternalClickBase(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupOnClickListener();
    }

    public ButtonWithInternalClickBase(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setupOnClickListener();
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        this.externalOnClickListener = l;
    }

    protected void callOnExternalClickListener(View v){
        if(externalOnClickListener != null){
            externalOnClickListener.onClick(v);
        }
    }

    protected void setInternalOnClickListener(final OnClickListener l) {
        internalOnClickListener = l;
    }

    private void setupOnClickListener() {
        super.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (ButtonWithInternalClickBase.this.internalOnClickListener != null) {
                    ButtonWithInternalClickBase.this.internalOnClickListener.onClick(v);
                } else if (ButtonWithInternalClickBase.this.externalOnClickListener != null) {
                    ButtonWithInternalClickBase.this.externalOnClickListener.onClick(v);
                }
            }
        });
    }
}
