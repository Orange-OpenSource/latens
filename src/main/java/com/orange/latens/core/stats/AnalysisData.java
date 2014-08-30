package com.orange.latens.core.stats;

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
