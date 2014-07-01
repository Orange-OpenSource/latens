package com.orange.latens;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.orange.latens.core.Controller;
import com.orange.latens.core.stats.LatensStats;
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
      LatensStats.showResults(this);
      return true;
    default:
      return super.onOptionsItemSelected(menuItem);
    }
  }

  private void launchSettings() {
    startActivity(new Intent(this, PreferencesActivity.class));
  }
}
