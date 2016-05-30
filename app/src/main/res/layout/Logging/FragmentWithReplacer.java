package layout.Logging;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.view.View;

/**
 * Created by paciu on 03.04.2016.
 */
public abstract class FragmentWithReplacer extends Fragment implements FragmentTag {
    protected void replaceWithFragment(int resid, Fragment fragment){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(resid, fragment, getFragmentTag()).addToBackStack(getFragmentTag()).commit();
    }

    protected abstract void initViewComponents(View view);
}

