package com.orange.latens.core.stats;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.orange.latens.LatensGraphActivity;
import com.orange.latens.Preferences;
import com.orange.latens.core.Constants;

import java.lang.ref.SoftReference;

public class LatensStats {

  private final Context ctx;
  private final TextView textView;
  private final ProgressBar progressBar;
  private final AnalysisData analysisData = new AnalysisData();

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

  private void initAnalysisDataSoftReference() {
    softReferenceAnalysisData = new SoftReference<AnalysisData>(analysisData);
  }

  public void onPreferencesUpdate() {
    refreshAnalysisDuration();
  }

  private void refreshAnalysisDuration() {
    analysisDuration = Preferences.getAnalysisDurationMs(ctx);
  }

  public void onPress() {
    initAnalysis();
  }

  private void initAnalysis() {
    long date = getDate();

    latensN = 0;
    analysisFinished = false;
    analysisData.clear();
    lastViewUpdate = date;
    analysisStartDate = date;

    beta = getBeta();
    setProgressBar(0);
  }

  private void setProgressBar(long progress) {
    progressBar.setProgress((int)progress);
    progressBar.setMax((int)analysisDuration);
  }

  private float getBeta() {
    return (float) Math.exp( - getTouchMeanPeriodMs() / getTimeConstantMs() );
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
  private void doAdd(int latens) {
    long date = getDate();
    long elapsed = getElapsedTime();
    addToFilter(latens);
    analysisData.addTouchEventForStats(date, elapsed, latensN);
    updateAnalysisFinished(elapsed);
    handleStatViewUpdate();
  }

  private void addToFilter(int latens) {
    latensN = beta * latensN + (1-beta) * latens;
  }

  private void updateAnalysisFinished(long elapsed) {
    if (elapsed > analysisDuration) {
      analysisFinished = true;
      analysisData.updateTouchMeanPeriod(ctx);
      showResults(ctx);
    }
    setProgressBar(elapsed);
  }

  private void handleStatViewUpdate() {
    long dt = getDate() - lastViewUpdate;
    if (dt > Constants.LATENS_VIEW_UPDATE_PERIOD_MS) {
      doStatViewUpdate();
    }
  }
  private void doStatViewUpdate() {
    Log.d(this.getClass().getSimpleName(), String.format("%f ms (beta = %f)", latensN, beta));
    textView.setText(String.format("%d ms", (int)latensN));
  }

  public static void showResults(Context ctx) {
    ctx.startActivity(new Intent(ctx, LatensGraphActivity.class));
  }

  private float getTouchMeanPeriodMs() {
    return Preferences.getTouchMeanPeriodMs(ctx);
  }

  private long getTimeConstantMs() {
    return Preferences.getTimeConstantMs(ctx);
  }

  private long getDate() {
    return SystemClock.uptimeMillis();
  }

  private long getElapsedTime() {
    return getDate() - analysisStartDate;
  }
}
