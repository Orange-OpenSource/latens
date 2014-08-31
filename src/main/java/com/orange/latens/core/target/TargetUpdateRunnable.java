package com.orange.latens.core.target;
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

import android.view.View;

public class TargetUpdateRunnable implements Runnable {

  private final View view;
  private final TargetUpdateListener listener;

  public TargetUpdateRunnable(View view, TargetUpdateListener listener) {
    this.view = view;
    this.listener = listener;
    forceUpdate();
  }

  @Override
  public void run() {
    listener.onTargetUpdate();
    forceUpdate();
  }

  private void forceUpdate() {
    view.post(this);
  }
}
