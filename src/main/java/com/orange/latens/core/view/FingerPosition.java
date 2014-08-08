package com.orange.latens.core.view;

import android.graphics.Point;
import android.view.MotionEvent;

public class FingerPosition {

  private final Point position = new Point();

  public void onTouchEvent(MotionEvent touchEvent) {
    this.position.set((int) touchEvent.getX(), (int) touchEvent.getY());
  }

  public Point getPosition() {
    return position;
  }
}
