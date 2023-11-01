//Nirmal Bhandari
//L20422171
package com.example.hm8_bhandari;

import android.os.Build;
import android.preference.PreferenceActivity;
import java.util.List;


public class PrefsActivity extends PreferenceActivity {
    @Override
    protected boolean isValidFragment (String fragmentName){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB)
            return true;
        else if (PrefsFragmentSettings.class.getName().equals(fragmentName)) {
            return true;
        }
        return false;
    }
    @Override
    public void onBuildHeaders (List<Header> target){
        getFragmentManager().beginTransaction().replace(android.R.id.content, new PrefsFragmentSettings()).commit();
    }
}
