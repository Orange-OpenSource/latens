package com.orange.latens;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.orange.latens.core.Constants;

public class Preferences {

  public static final String DOMAIN = "Latens";

  private static final String KEY_REVOLUTION_PERIOD_MS = "revolution_period_ms";
  private static final String KEY_TIME_CONSTANT_MS = "integrator_time_constant_ms";
  private static final String KEY_TOUCH_MEAN_PERIOD_MS = "touch_mean_period_ms";
  private static final String KEY_ANALYSIS_DURATION_MS = "analysis_duration_ms";
  private static final String KEY_RADIUS_MM = "radius_mm";


  public static int getRevolutionPeriod(Context ctx) {
    String stringVal = getPreferences(ctx).getString(KEY_REVOLUTION_PERIOD_MS, "-1");
    int revolutionPeriod = Integer.parseInt(stringVal);
    if (revolutionPeriod <= 0) {
      revolutionPeriod = Constants.TARGET_REVOLUTION_PERIOD;
      setRevolutionPeriod(ctx, revolutionPeriod);
    }
    return revolutionPeriod;
  }

  public static void setRevolutionPeriod(Context ctx, int revolutionPeriod) {
    Editor editor = getPreferences(ctx).edit();
    editor.putString(KEY_REVOLUTION_PERIOD_MS, Integer.toString(revolutionPeriod));
    editor.commit();
  }

  private static SharedPreferences getPreferences(Context ctx) {
    return ctx.getSharedPreferences(DOMAIN, Context.MODE_PRIVATE);
  }

  public static long getTimeConstantMs(Context ctx) {
    String stringVal = getPreferences(ctx).getString(KEY_TIME_CONSTANT_MS, "-1");
    long timeConstantMs = Long.parseLong(stringVal);
    if (timeConstantMs <= 0) {
      timeConstantMs = Constants.STATS_TIME_CONSTANT_MS;
      setTimeConstantMs(ctx, timeConstantMs);
    }
    return timeConstantMs;
  }

  public static void setTimeConstantMs(Context ctx, long timeConstantMs) {
    Editor editor = getPreferences(ctx).edit();
    editor.putString(KEY_TIME_CONSTANT_MS, Long.toString(timeConstantMs));
    editor.commit();
  }

  public static float getTouchMeanPeriodMs(Context ctx) {
    String stringVal = getPreferences(ctx).getString(KEY_TOUCH_MEAN_PERIOD_MS, "-1");
    float meanPeriod = Float.parseFloat(stringVal);
    if (meanPeriod <= 0) {
      meanPeriod = Constants.TOUCH_CAPTURE_DEFAULT_PERIOD_MS;
      setTouchMeanPeriodMs(ctx, meanPeriod);
    }
    return meanPeriod;
  }

  public static void setTouchMeanPeriodMs(Context ctx, float meanPeriod) {
    Editor editor = getPreferences(ctx).edit();
    editor.putString(KEY_TOUCH_MEAN_PERIOD_MS, Float.toString(meanPeriod));
    editor.commit();
  }

  public static long getAnalysisDurationMs(Context ctx) {
    String stringVal = getPreferences(ctx).getString(KEY_ANALYSIS_DURATION_MS, "-1");
    long analysisDuration = Long.parseLong(stringVal);
    if (analysisDuration <= 0) {
      analysisDuration = Constants.STATS_ANALYSIS_DURATION_MS;
      setAnalysisDurationMs(ctx, analysisDuration);
    }
    return analysisDuration;
  }

  public static void setAnalysisDurationMs(Context ctx, long analysisDuration) {
    Editor editor = getPreferences(ctx).edit();
    editor.putString(KEY_ANALYSIS_DURATION_MS, Long.toString(analysisDuration));
    editor.commit();
  }

  public static int getRadiusMm(Context ctx, int maxValueMm) {
    String stringVal = getPreferences(ctx).getString(KEY_RADIUS_MM, "-1");
    int radiusMm = Integer.parseInt(stringVal);
    if (radiusMm <= 0) {
      setRadiusMm(ctx, maxValueMm);
    }
    return radiusMm;
  }

  public static void setRadiusMm(Context ctx, int radiusMm) {
    Editor editor = getPreferences(ctx).edit();
    editor.putString(KEY_RADIUS_MM, Long.toString(radiusMm));
    editor.commit();
  }
}