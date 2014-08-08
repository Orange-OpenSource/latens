package com.orange.latens.core.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.orange.latens.core.Constants;
import com.orange.latens.core.target.Target;

public class Drawer {

  private final Layout layout;
  private final Target target;

  private final Rect rect = new Rect();
  private final Paint paint = new Paint();

  private final Point LastDrawnPos = new Point();
  private final Point position = new Point();


  public Drawer(Layout layout, Target target) {
    this.layout = layout;
    this.target = target;
    init();
  }

  private void init() {
    initPaint();
  }

  private void initPaint() {
    paint.setColor(Constants.TARGET_CIRCLE_COLOR);
  }


  public void draw(Canvas canvas) {
    LastDrawnPos.x = position.x;
    LastDrawnPos.y = position.y;
    drawRevolutionCircle(canvas);
    drawTarget(canvas);
  }

  private void drawRevolutionCircle(Canvas canvas) {
    Point center = target.getCenter();
    paint.setStyle(Paint.Style.STROKE);
    canvas.drawCircle(center.x, center.y, target.getRadiusPixels(), paint);
  }

  private void drawTarget(Canvas canvas) {
    paint.setStyle(Paint.Style.FILL_AND_STROKE);
    canvas.drawCircle(position.x, position.y, target.getTargetRadiusPixels(), paint);
  }


  public void requestScreenUpdateAt(Point circlePosition) {
    position.set(circlePosition.x, circlePosition.y);
    invalidate(position);
  }

  private void invalidate(Point pos) {
    int radiusPixels = target.getTargetRadiusPixels();
    Point oldPos = LastDrawnPos;
    rect.left   = Math.min(pos.x - radiusPixels, oldPos.x - radiusPixels);
    rect.right  = Math.max(pos.x + radiusPixels, oldPos.x + radiusPixels);
    rect.top    = Math.min(pos.y - radiusPixels, oldPos.y - radiusPixels);
    rect.bottom = Math.max(pos.y + radiusPixels, oldPos.y + radiusPixels);

    layout.invalidate(rect);
  }
}
