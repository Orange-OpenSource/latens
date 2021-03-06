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

import android.content.Context;
import android.graphics.Point;
import android.os.SystemClock;
import android.view.View;

import com.orange.latens.preferences.Preferences;
import com.orange.latens.core.Constants;
import com.orange.latens.core.view.AndroidMetrics;
import com.orange.latens.core.view.Layout;

import lombok.Getter;

public class Target implements View.OnLayoutChangeListener {

  private final Context ctx;
  private final Layout layout;
  private final AndroidMetrics metrics;

  @Getter
  private final Point position = new Point();
  @Getter
  private final Point center = new Point();

  private int revolutionPeriodMs;
  private int targetDiameterPx;
  private int maxRadiusMm;
  private int maxRadiusPixels;

  @Getter
  private int radiusPixels;


  public Target(Context ctx, Layout layout) {
    this.ctx = ctx;
    this.metrics = AndroidMetrics.get(ctx);
    this.layout = layout;
    init();
  }

  private void init() {
    setRevolutionPeriodMs(Preferences.getRevolutionPeriod(ctx));
    setTargetDiameterMm(Constants.TARGET_CIRCLE_DIAMETER_MILLIMETERS);
    attachOnLayoutChangeListener();
  }

  private void attachOnLayoutChangeListener() {
    layout.addOnLayoutChangeListener(this);
  }

  @Override
  public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
    refreshProperties();
  }

  public void onPreferencesUpdate() {
    refreshProperties();
  }

  private void refreshProperties() {
    setRevolutionPeriodMs(Preferences.getRevolutionPeriod(ctx));
    computeMaxRadius();
    setRadiusMm(Preferences.getRadiusMm(ctx, maxRadiusMm));
    updateCenter();
  }

  public void setRadiusMm(int radiusMm) {
    int tmpRadiusPx = metrics.millimetersToPixels(radiusMm);
    if (tmpRadiusPx > maxRadiusPixels) {
      Preferences.setRadiusMm(ctx, maxRadiusMm);
      tmpRadiusPx = maxRadiusPixels;
    }
    radiusPixels = tmpRadiusPx;
  }

  public void setTargetDiameterMm(int diameterMm) {
    targetDiameterPx = metrics.millimetersToPixels(diameterMm);
    refreshProperties();
  }

  public void setRevolutionPeriodMs(int revolutionPeriodMs) {
    this.revolutionPeriodMs = revolutionPeriodMs;
  }

  private void updateCenter() {
    int height = layout.getHeight();
    int cy = height - maxRadiusPixels - targetDiameterPx*2/3;
    center.set(layout.getWidth()/2, cy);
  }

  private void computeMaxRadius() {
    int minWidthHeight = Math.min(metrics.getWidthPixels(), metrics.getHeightPixels());
    int diameter = minWidthHeight - targetDiameterPx;
    maxRadiusPixels = diameter/2;
    maxRadiusMm = metrics.pixelsToMillimeters(maxRadiusPixels);
  }

  public void updatePosition() {
    double angleRad = getCurrentAngleRad();
    int dx = getDxForAngle(angleRad);
    int dy = getDyForAngle(angleRad);
    position.set(center.x + dx, center.y + dy);
  }

  private int getDxForAngle(double angleRad) {
    return (int)(radiusPixels * Math.cos(angleRad));
  }

  private int getDyForAngle(double angleRad) {
    return (int)(radiusPixels * Math.sin(angleRad));
  }

  private double getCurrentAngleRad() {
    double factor = ((double)getCurrentTime() % revolutionPeriodMs) / revolutionPeriodMs;
    return factor * Math.PI * 2;
  }

  private long getCurrentTime() {
    return SystemClock.uptimeMillis();
  }

  public int getPositionLatens(Point latePosition) {
    updatePosition();
    double dTheta = getDeltaTheta(latePosition);
    return getDtFromAngleDiff(dTheta);
  }

  private double getDeltaTheta(Point latePosition) {
    double dTheta = getPolarAngle(position) - getPolarAngle(latePosition);
    if (position.y < center.y && latePosition.y >= center.y) {
      dTheta += Math.PI * 2;
    }
    return dTheta;
  }

  private double getPolarAngle(Point latePosition) {
    int x = latePosition.x - center.x;
    int y = latePosition.y - center.y;
    return Math.atan2(y, x);
  }

  private int getDtFromAngleDiff(double dTheta) {
    return (int)(revolutionPeriodMs * dTheta / (2 * Math.PI));
  }

  public int getTargetRadiusPixels() {
    return targetDiameterPx/2;
  }
}
