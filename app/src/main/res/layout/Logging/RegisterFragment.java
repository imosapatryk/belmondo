package layout.Logging;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.paciu.facebooklogging.R;

/**
 * Created by paciu on 03.04.2016.
 */
public class RegisterFragment extends FragmentWithReplacerAndOnClick {

    private Button facebookRegisterButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_layout, container, false);
        initViewComponents(view);
        return view;
    }

    @Override
    protected void initViewComponents(View view){
        facebookRegisterButton = (Button)view.findViewById(R.id.register_with_facebook_button);
        facebookRegisterButton.setOnClickListener(this);
    }

    @Override
    public String getFragmentTag() {
        return "REGISTER_FRAGMENT";
    }

    @Override
    public void onClick(View v) {
        int id =  v.getId();
        if(id == facebookRegisterButton.getId()){
            doFacebookRegistration();
        }
    }

    protected void doFacebookRegistration(){}
}
