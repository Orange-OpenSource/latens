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
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.orange.latens.core.Controller;

public class Layout extends FrameLayout {

  private Controller controller;

  public Layout(Context context, AttributeSet attrs) {
    super(context, attrs);
    setWillNotDraw(false);
  }

  @Override
  public boolean onTouchEvent(MotionEvent touchEvent) {
    controller.onTouchEvent(touchEvent);
    return true;
  }

  @Override
  public void draw(Canvas canvas) {
    controller.draw(canvas);
    super.draw(canvas);
  }

  public void setController(Controller controller) {
    this.controller = controller;
  }
}
