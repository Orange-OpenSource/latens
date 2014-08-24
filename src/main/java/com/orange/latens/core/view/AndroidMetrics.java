package com.orange.latens.core.view;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(suppressConstructorProperties = true)
public class AndroidMetrics {
  private static final int MICROMETERS_PER_INCH = 25400;

  private final float ydpi;
  private final int   widthPixels;
  private final int   heightPixels;


  int getMicrometersPerPixel() {
    return (int) (MICROMETERS_PER_INCH/ydpi);
  }

  public int millimetersToPixels(int millimeters) {
    return (millimeters * 1000) / getMicrometersPerPixel();
  }

  public int pixelsToMillimeters(int pixels) {
    return pixels * getMicrometersPerPixel() / 1000;
  }

  // not tested, android stuff
  public static AndroidMetrics get(Context ctx) {
    DisplayMetrics metrics = new DisplayMetrics();
    WindowManager  wm      = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
    wm.getDefaultDisplay().getMetrics(metrics);
    return new AndroidMetrics(metrics.ydpi, metrics.widthPixels, metrics.heightPixels);
  }
}