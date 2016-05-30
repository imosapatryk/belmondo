package layout.Logging;

import android.app.Activity;
import android.os.Handler;
import android.widget.Toast;

import com.example.paciu.facebooklogging.R;

/**
 * Created by paciu on 03.04.2016.
 */
public class TwiceBackButtonAppFinisher {
    private int clickCount = 0;
    private Activity activity;

    public TwiceBackButtonAppFinisher(Activity activity) {
        this.activity = activity;
    }

    public void backPressed() {
        clickCount++;

        if (clickCount == 1) {
            showMessageAndDelayCounter();
        } else if (clickCount == 2) {
            finishActivity();
        }
    }
    public void backCancel(){
        clickCount = 0;
    }

    protected void showMessageAndDelayCounter(){
        Toast.makeText(activity, R.string.tap_twice_to_exit, Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                clickCount = 0;
            }
        }, 2000);
    }

    protected void finishActivity(){
        activity.finish();
    }
}
