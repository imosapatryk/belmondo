package com.example.paciu.belmondo.Logging;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.view.View;

import com.example.paciu.belmondo.R;

/**
 * Created by paciu on 03.04.2016.
 */
public abstract class FragmentWithReplacer extends Fragment implements FragmentTag {
    protected void replaceWithFragment(int resid, Fragment fragment){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.frag_fade_in, R.anim.frag_fade_out, R.anim.frag_fade_in,  R.anim.frag_fade_out)
                .replace(R.id.registration_fragment_container, fragment)
                .addToBackStack(getFragmentTag())
                .commit();
    }

    protected abstract void initViewComponents(View view);
}

