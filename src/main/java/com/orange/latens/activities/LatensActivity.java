package com.orange.latens.activities;
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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.orange.latens.R;
import com.orange.latens.core.Controller;
import com.orange.latens.core.view.Layout;

public class LatensActivity extends Activity {

  private Controller controller;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    init();
  }

  private void init() {
    Layout layout = (Layout) findViewById(R.id.latens_view);
    controller = new Controller(layout);
  }

  @Override
  protected void onResume() {
    super.onResume();
    controller.onPreferencesUpdate();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.main_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem menuItem) {
    switch (menuItem.getItemId()) {
    case R.id.menu_settings:
      launchSettings();
      return true;
    case R.id.menu_graph:
      showResults(this);
      return true;
    default:
      return super.onOptionsItemSelected(menuItem);
    }
  }

  private void launchSettings() {
    startActivity(new Intent(this, PreferencesActivity.class));
  }

  public static void showResults(Context ctx) {
    ctx.startActivity(new Intent(ctx, LatensGraphActivity.class));
  }
}
