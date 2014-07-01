package com.orange.latens.core.target;

import android.content.Context;
import android.graphics.Point;
import android.os.SystemClock;
import android.view.View;

import com.orange.latens.Preferences;
import com.orange.latens.core.Constants;
import com.orange.latens.core.view.AndroidMetrics;
import com.orange.latens.core.view.Layout;

public class Target implements View.OnLayoutChangeListener {

  private final Context ctx;
  private final Layout layout;
  private final AndroidMetrics metrics;
  private final Point center = new Point();
  private final Point position = new Point();


  private int revolutionPeriodMs;

  private int targetDiameterPx;
  private int maxRadiusMm;
  private int maxRadiusPixels;
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

  public Point getPosition() {
    return position;
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

  public Point getCenter() {
    return center;
  }
  public int getRadiusPixels() {
    return radiusPixels;
  }
  public int getTargetRadiusPixels() {
    return targetDiameterPx/2;
  }
}
