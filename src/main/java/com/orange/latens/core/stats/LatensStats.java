package com.orange.latens.core.stats;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.common.annotations.VisibleForTesting;
import com.orange.latens.activities.LatensActivity;
import com.orange.latens.core.Constants;
import com.orange.latens.preferences.Preferences;

import java.lang.ref.SoftReference;

public class LatensStats {

  private final Context ctx;
  private final TextView textView;
  private final ProgressBar progressBar;
  private final AnalysisData analysisData = new AnalysisData();
  private final Preferences preferences = new Preferences();

  private static SoftReference<AnalysisData> softReferenceAnalysisData;

  private float latensN;
  private float beta;
  private long lastViewUpdate;
  private long analysisStartDate;
  private long analysisDuration;
  private boolean analysisFinished = true;


  public LatensStats(TextView textView, ProgressBar progressBar) {
    this.ctx = textView.getContext();
    this.textView = textView;
    this.progressBar = progressBar;
    initAnalysisDataSoftReference();
  }

  public static AnalysisData getAnalysisData() {
    AnalysisData analysisData = softReferenceAnalysisData.get();
    if (softReferenceAnalysisData.get() == null) {
      throw new NullPointerException("Analysis data have been released by the garbage collector");
    } else {
      return analysisData;
    }
  }

  @VisibleForTesting
  void initAnalysisDataSoftReference() {
    softReferenceAnalysisData = new SoftReference<AnalysisData>(analysisData);
  }

  public void onPreferencesUpdate() {
    refreshAnalysisDuration();
  }

  @VisibleForTesting
  void refreshAnalysisDuration() {
    analysisDuration = preferences.getAnalysisDurationMs(ctx);
  }

  public void onPress() {
    initAnalysis();
  }

  @VisibleForTesting
  void initAnalysis() {
    long date = getDate();

    latensN = 0;
    analysisFinished = false;
    analysisData.clear();
    lastViewUpdate = date;
    analysisStartDate = date;

    beta = getBeta();
    setProgressBar(0);
  }

  @VisibleForTesting
  void setProgressBar(long progress) {
    progressBar.setProgress((int)progress);
    progressBar.setMax((int)analysisDuration);
  }

  @VisibleForTesting
  float getBeta() {
    float touchMeanPeriodMs = preferences.getTouchMeanPeriodMs(ctx);
    long timeConstantMs = preferences.getTimeConstantMs(ctx);
    return (float) Math.exp( - touchMeanPeriodMs / timeConstantMs );
  }

  public void addDrawingEventForStats() {
    if (!analysisFinished) {
      analysisData.addDrawingEventForStats(getDate());
    }
  }

  public void add(int latens) {
    if (!analysisFinished) {
      doAdd(latens);
    }
  }
  @VisibleForTesting
  void doAdd(int latens) {
    long date = getDate();
    long elapsed = getElapsedTime();
    addToFilter(latens);
    analysisData.addTouchEventForStats(date, elapsed, latensN);
    updateAnalysisFinished(elapsed);
    handleStatViewUpdate();
  }

  @VisibleForTesting
  void addToFilter(int latens) {
    latensN = beta * latensN + (1-beta) * latens;
  }

  @VisibleForTesting
  void updateAnalysisFinished(long elapsed) {
    if (elapsed > analysisDuration) {
      analysisFinished();
    }
    setProgressBar(elapsed);
  }

  @VisibleForTesting
  void analysisFinished() {
    analysisFinished = true;
    analysisData.updateTouchMeanPeriod(ctx);
    LatensActivity.showResults(ctx);
  }

  @VisibleForTesting
  void handleStatViewUpdate() {
    long dt = getDate() - lastViewUpdate;
    if (dt > Constants.LATENS_VIEW_UPDATE_PERIOD_MS) {
      doStatViewUpdate();
    }
  }
  @VisibleForTesting
  void doStatViewUpdate() {
    Log.d(this.getClass().getSimpleName(), String.format("%f ms (beta = %f)", latensN, beta));
    textView.setText(String.format("%d ms", (int)latensN));
  }

  // not tested
  @VisibleForTesting
  long getDate() {
    return SystemClock.uptimeMillis();
  }

  @VisibleForTesting
  long getElapsedTime() {
    return getDate() - analysisStartDate;
  }
}
