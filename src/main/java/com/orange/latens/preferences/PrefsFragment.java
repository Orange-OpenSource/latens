package com.orange.latens.preferences;
/*
 * Copyright (C) 2014 Orange
 * Authors: Christophe Maldivi
 *
 * This software is the confidential and proprietary information of Orange.
 * You shall not disclose such confidential information and shall use it only
 * in accordance with the terms of the license agreement you entered into
 * with Orange.
 */

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
