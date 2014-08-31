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
