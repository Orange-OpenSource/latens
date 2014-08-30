package com.orange.latens.core.stats;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class TimeStatSummaryFunctionalTest {

  @Test
  public void shouldComputeTimeStats() {
    // given
    List<Long> events = new ArrayList<Long>();
    fillEvents(events);
    // do
    TimeStatSummary timeStatSummary = new TimeStatSummary(events);
    // then
    Assert.assertEquals(110, timeStatSummary.getMaxPeriod());
    Assert.assertEquals(10  , timeStatSummary.getMeanFrequency());
    Assert.assertEquals(100, timeStatSummary.getMeanPeriod(), 0);
    Assert.assertEquals(90, timeStatSummary.getMinPeriod());
    Assert.assertEquals(4, timeStatSummary.getStandardDeviation());
  }

  private void fillEvents(List<Long> events) {
    long date = 0;
    for (int i = 0; i < 10; i++) {
      date += 100;
      events.add(date);
    }
    date += 90;
    events.add(date);
    date += 110;
    events.add(date);
  }
}
