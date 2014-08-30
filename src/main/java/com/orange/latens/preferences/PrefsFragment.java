package com.orange.latens.preferences;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.orange.latens.R;

public class PrefsFragment extends PreferenceFragment {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initPreferenceScreen();
  }

  private void initPreferenceScreen() {
    getPreferenceManager().setSharedPreferencesName(Preferences.DOMAIN);
    addPreferencesFromResource(R.xml.preferences);
  }
}
