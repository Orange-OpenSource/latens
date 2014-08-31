package com.orange.latens.core.view;
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