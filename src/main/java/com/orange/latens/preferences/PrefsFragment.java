package com.orange.latens.preferences;
/*
 * Latens, an android application to measure touch and display latency
 * Copyright (C) 2014 christophe.maldivi@orange.com, eric.petit@orange.com
 *
 * Latens is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Latens is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Latens. If not, see <http://www.gnu.org/licenses/>.
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
