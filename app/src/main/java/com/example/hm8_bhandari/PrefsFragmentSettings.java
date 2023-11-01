//Nirmal Bhandari
//L20422171
package com.example.hm8_bhandari;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.util.Log;

public class PrefsFragmentSettings  extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    public PrefsFragmentSettings () {

    }

    @Override
    public void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs_fragments_settings);
    }

    @Override
    public void onResume () {

        super.onResume();

        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

        Preference pref;
        pref = getPreferenceScreen().findPreference("key_animal_info");
        pref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

            public boolean onPreferenceClick (Preference preference) {

                try {

                    Uri site = Uri.parse("https://detroitzoo.org/animals/zoo-animals/");
                    Intent intent = new Intent(Intent.ACTION_VIEW, site);
                    startActivity(intent);
                }
                catch (Exception e) {
                    Log.e("PrefsFragmentSettings","Browser failed", e);
                }
                return true;
            }
        });
    }

    public void onSharedPreferenceChanged (SharedPreferences sharedPreferences, String key) {
        if (key.equals("key_music_enabled")) {

            boolean b = sharedPreferences.getBoolean("key_music_enabled", true);

            if (b == false) {

                if (Assets.mediaPlayer != null) {

                    Assets.mediaPlayer.setVolume(0,0);
                }
            } else {

                if (Assets.mediaPlayer != null) {

                    Assets.mediaPlayer.setVolume(1,1);
                }
            }
        }

        if (key.equals("key_sound_enabled")) {

            boolean b1 = sharedPreferences.getBoolean("key_sound_enabled", true);

            if (b1 == true) {
                Assets.sp_status = true;
            } else if (b1 == false){

                Assets.sp_status = false;
            }
        }
    }
}
