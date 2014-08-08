package com.orange.latens.core.stats;

import android.content.Context;

import com.orange.latens.Preferences;

import java.util.ArrayList;
import java.util.List;

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

  void updateTouchMeanPeriod(Context ctx) {
    TimeStatSummary stats = new TimeStatSummary(touchEvents);
    Preferences.setTouchMeanPeriodMs(ctx, stats.getMeanPeriod());
  }

  public List<LatensPoint> getLatencies() {
    return latencies;
  }

  public List<Long> getDrawingEvents() {
    return drawingEvents;
  }

  public List<Long> getTouchEvents() {
    return touchEvents;
  }
}
