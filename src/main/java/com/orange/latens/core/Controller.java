package com.orange.latens.core;
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

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.orange.latens.R;
import com.orange.latens.core.stats.LatensStats;
import com.orange.latens.core.target.Target;
import com.orange.latens.core.target.TargetUpdateListener;
import com.orange.latens.core.target.TargetUpdateRunnable;
import com.orange.latens.core.view.Drawer;
import com.orange.latens.core.view.FingerPosition;
import com.orange.latens.core.view.Layout;

public class Controller implements TargetUpdateListener {

  private final FingerPosition fingerPosition = new FingerPosition();

  private Drawer drawer;
  private Target target;
  private Layout layout;
  private LatensStats latensStats;

  public Controller(Layout layout) {
    init(layout);
  }

  private void init(Layout layout) {
    this.layout = layout;
    layout.setController(this);

    new TargetUpdateRunnable(layout, this);

    Context ctx = layout.getContext();
    latensStats = new LatensStats(getlatensStatsView(), getLatensProgressBar());
    target      = new Target(ctx, layout);
    drawer      = new Drawer(layout, target);
  }

  public void onPreferencesUpdate() {
    target.onPreferencesUpdate();
    latensStats.onPreferencesUpdate();
  }

  public void onTouchEvent(MotionEvent touchEvent) {
    fingerPosition.onTouchEvent(touchEvent);
    analyseLatens(touchEvent);
  }

  public void draw(Canvas canvas) {
    latensStats.addDrawingEventForStats();
    drawer.draw(canvas);
  }

  @Override
  public void onTargetUpdate() {
    target.updatePosition();
    drawer.requestScreenUpdateAt(target.getPosition());
  }

  private void analyseLatens(MotionEvent touchEvent) {
    if (isActionDown(touchEvent)) {
      latensStats.onPress();
    }
    int latens = target.getPositionLatens(fingerPosition.getPosition());
    latensStats.add(latens);
  }

  private boolean isActionDown(MotionEvent touchEvent) {
    return touchEvent.getActionMasked() == MotionEvent.ACTION_DOWN;
  }

  private TextView getlatensStatsView() {
    return (TextView) layout.findViewById(R.id.latens);
  }

  private ProgressBar getLatensProgressBar() {
    return (ProgressBar) layout.findViewById(R.id.progress_bar);
  }
}
