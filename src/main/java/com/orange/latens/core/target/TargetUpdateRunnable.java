package com.orange.latens.core.target;

import android.view.View;

public class TargetUpdateRunnable implements Runnable {

  private final View view;
  private final TargetUpdateListener listener;

  public TargetUpdateRunnable(View view, TargetUpdateListener listener) {
    this.view = view;
    this.listener = listener;
    forceUpdate();
  }

  @Override
  public void run() {
    listener.onTargetUpdate();
    forceUpdate();
  }

  private void forceUpdate() {
    view.post(this);
  }
}
