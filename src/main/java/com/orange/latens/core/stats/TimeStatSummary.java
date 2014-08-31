package com.orange.latens.core.stats;
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

import java.util.List;

import lombok.Getter;

@Getter
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
}
