package com.orange.latens.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.orange.latens.preferences.Preferences;
import com.orange.latens.R;
import com.orange.latens.core.stats.AnalysisData;
import com.orange.latens.core.stats.LatensPoint;
import com.orange.latens.core.stats.LatensStats;
import com.orange.latens.core.stats.TimeStatSummary;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.List;


public class LatensGraphActivity extends Activity {

  private static final String LTAG = LatensGraphActivity.class.getSimpleName();

  private AnalysisData analysisData;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_latens_graph);
    setUpViews();
  }

  private void setUpViews() {
    initAnalysisData();
    showDrawingAndTouchStats();
    showChart();
  }

  private void initAnalysisData() {
    analysisData = LatensStats.getAnalysisData();
  }

  private void showChart() {
    XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
    XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
    setUpRenderer(renderer);
    addChartView(dataset, renderer);
    renderSeries(dataset, renderer);
  }


  private void setUpRenderer(XYMultipleSeriesRenderer renderer) {
    renderer.setShowGrid(true);
    renderer.setLabelsTextSize(25);
    renderer.setLegendTextSize(25);
  }

  private void addChartView(XYMultipleSeriesDataset dataset, XYMultipleSeriesRenderer renderer) {
    LinearLayout layout = (LinearLayout) findViewById(R.id.chart);
    GraphicalView chartView = ChartFactory.getLineChartView(this, dataset, renderer);
    layout.addView(chartView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
  }

  private void renderSeries(XYMultipleSeriesDataset dataset, XYMultipleSeriesRenderer renderer) {
    XYSeries series = getSeries();
    fillSeries(series);

    dataset.addSeries(series);
    // create a new renderer for the new series
    XYSeriesRenderer serieRenderer = new XYSeriesRenderer();
    renderer.addSeriesRenderer(serieRenderer);
    // set some renderer properties
    serieRenderer.setPointStyle(PointStyle.CIRCLE);
    serieRenderer.setFillPoints(true);
  }

  private XYSeries getSeries() {
    return new XYSeries("latency");
  }

  private void fillSeries(XYSeries series) {
    List<LatensPoint> latencies = analysisData.getLatencies();
    series.add(0, 0);
    for (LatensPoint latensPoint : latencies) {
      series.add(latensPoint.getDateMs(), latensPoint.getLatensMs());
    }
  }

  private void showDrawingAndTouchStats() {
    TimeStatSummary dStats = new TimeStatSummary(analysisData.getDrawingEvents());
    TimeStatSummary tStats = new TimeStatSummary(analysisData.getTouchEvents());

    Log.d(LTAG, String.format("drawings per sec: %d (sd %d, m %d M %d)\ntouch events per sec: %d (sd %d, m %d, M %d Tc %f)",
            dStats.getMeanFrequency(), dStats.getStandardDeviation(), dStats.getMinPeriod(), dStats.getMaxPeriod(),
            tStats.getMeanFrequency(), tStats.getStandardDeviation(), tStats.getMinPeriod(), tStats.getMaxPeriod(),
            Preferences.getTouchMeanPeriodMs(this)));

    getDrawingAndTouchStatsView().setText(String.format("Drawing Frequency: %d Hz\nTouch events Frequency: %d Hz\nEstimated latency: %d ms", dStats.getMeanFrequency(), tStats.getMeanFrequency(), getLastLatency()));
  }

  private int getLastLatency() {
    List<LatensPoint> latencies = analysisData.getLatencies();
    if (latencies.size() > 1) {
      return (int) latencies.get(latencies.size() - 1).getLatensMs();
    } else {
      return 0;
    }
  }

  private TextView getDrawingAndTouchStatsView() {
    return (TextView) findViewById(R.id.drawings_and_touch_stats);
  }
}
