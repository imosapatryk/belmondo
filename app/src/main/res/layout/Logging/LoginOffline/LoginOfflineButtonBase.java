package layout.Logging.LoginOffline;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

/**
 * Created by paciu on 05.04.2016.
 */
public class LoginOfflineButtonBase extends Button {

    private OnClickListener externalOnClickListener;
    private OnClickListener internalOnClickListener;

    public LoginOfflineButtonBase(Context context) {
        super(context);
        setupOnClickListener();
    }

    public LoginOfflineButtonBase(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupOnClickListener();
    }

    public LoginOfflineButtonBase(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupOnClickListener();
    }

    public LoginOfflineButtonBase(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
                if (LoginOfflineButtonBase.this.internalOnClickListener != null) {
                    LoginOfflineButtonBase.this.internalOnClickListener.onClick(v);
                } else if (LoginOfflineButtonBase.this.externalOnClickListener != null) {
                    LoginOfflineButtonBase.this.externalOnClickListener.onClick(v);
                }
            }
        });
    }
}
