package com.orange.latens.activities;

import android.app.Activity;
import android.os.Bundle;

import com.orange.latens.preferences.PrefsFragment;


public class PreferencesActivity extends Activity {

  private PrefsFragment prefsFragment;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initPrefFragment();
  }

  private void initPrefFragment() {
    prefsFragment = new PrefsFragment();
    getFragmentManager().beginTransaction().
            replace(android.R.id.content, prefsFragment).commit();
  }
}
