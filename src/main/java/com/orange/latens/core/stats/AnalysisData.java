package com.orange.latens.core.stats;

import android.content.Context;

import com.orange.latens.preferences.Preferences;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class AnalysisData {

  private final List<Long> drawingEvents = new ArrayList<Long>();
  private final List<Long> touchEvents = new ArrayList<Long>();
  private final List<LatensPoint> latencies = new ArrayList<LatensPoint>();

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

  // not tested
  void updateTouchMeanPeriod(Context ctx) {
    TimeStatSummary stats = new TimeStatSummary(touchEvents);
    Preferences.setTouchMeanPeriodMs(ctx, stats.getMeanPeriod());
  }
}
