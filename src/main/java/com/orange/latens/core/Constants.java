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
