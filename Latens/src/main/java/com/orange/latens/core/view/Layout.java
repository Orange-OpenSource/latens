package com.orange.latens.core.view;

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
