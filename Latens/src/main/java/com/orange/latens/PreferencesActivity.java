package com.orange.latens;

import android.os.Bundle;
import android.preference.PreferenceActivity;


public class PreferencesActivity extends PreferenceActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getPreferenceManager().setSharedPreferencesName(Preferences.DOMAIN);
    addPreferencesFromResource(R.xml.preferences);
  }
}
