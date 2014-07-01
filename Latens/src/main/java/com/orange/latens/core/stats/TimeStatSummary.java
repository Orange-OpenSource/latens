package com.orange.latens.core.stats;

import java.util.List;

public class TimeStatSummary {

  private float meanPeriod;
  private long minPeriod;
  private long maxPeriod;
  private long standardDeviation;


  public TimeStatSummary(List<Long> events) {
    init(events);
  }

  private void init(List<Long> events) {
    if (events.size() > 1) {
      computeMeanMinMax(events);
      computeStandardDeviation(events);
    }
  }

  private void computeMeanMinMax(List<Long> events) {
    long sumDt = 0;
    minPeriod = Long.MAX_VALUE;
    maxPeriod = Long.MIN_VALUE;
    for (int i = 1; i < events.size(); i++) {
      long dt = events.get(i) - events.get(i-1);
      sumDt += dt;
      if (dt > maxPeriod) {
        maxPeriod = dt;
      }
      if (dt < minPeriod) {
        minPeriod = dt;
      }
    }
    meanPeriod = (float)sumDt/(events.size()-1);
  }

  private void computeStandardDeviation(List<Long> events) {
    float sum2 = 0;
    for (int i = 1; i < events.size(); i++) {
      long dt = events.get(i) - events.get(i-1);
      sum2 += Math.pow(dt - meanPeriod, 2);
    }
    standardDeviation = (int) Math.sqrt(sum2/(events.size()-1));
  }


  public long getMeanFrequency() {
    return meanPeriod == 0 ? 0 : (long) (1000 / meanPeriod);
  }
  public float getMeanPeriod() {
    return meanPeriod;
  }
  public long getMinPeriod() {
    return minPeriod;
  }
  public long getMaxPeriod() {
    return maxPeriod;
  }
  public long getStandardDeviation() {
    return standardDeviation;
  }
}
