package com.orange.latens.core.stats;
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

import com.orange.latens.preferences.Preferences;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;

@Getter
public class AnalysisData {

  private final List<Long> drawingEvents = new ArrayList<Long>();
  private final List<Long> touchEvents = new ArrayList<Long>();
  private final List<LatensPoint> latencies = new ArrayList<LatensPoint>();

  @Getter(AccessLevel.NONE)
  private final Preferences preferences = new Preferences();

  void clear() {
    drawingEvents.clear();
    touchEvents.clear();
    latencies.clear();
  }

  void addDrawingEventForStats(long date) {
    drawingEvents.add(date);
  }

  void addTouchEventForStats(long date, long elapsed, float latensN) {
    touchEvents.add(date);
    latencies.add(new LatensPoint(latensN, elapsed));
  }

  void updateTouchMeanPeriod(Context ctx) {
    TimeStatSummary stats = new TimeStatSummary(touchEvents);
    preferences.setTouchMeanPeriodMs(ctx, stats.getMeanPeriod());
  }
}
