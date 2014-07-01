package com.orange.latens.core;

import android.graphics.Color;

public class Constants {
  public static final int TARGET_CIRCLE_DIAMETER_MILLIMETERS = 12;
  public static final int TARGET_CIRCLE_COLOR = Color.WHITE;
  public static final int TARGET_REVOLUTION_PERIOD = 1500;

  public static final long STATS_TIME_CONSTANT_MS = 5000;
  public static final long STATS_ANALYSIS_DURATION_MS = 3 * STATS_TIME_CONSTANT_MS;
  public static final long LATENS_VIEW_UPDATE_PERIOD_MS = 1000;

  public static final float TOUCH_CAPTURE_DEFAULT_PERIOD_MS = (float)1000/60;
}
