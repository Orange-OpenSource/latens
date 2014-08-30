package com.orange.latens.core.stats;

import com.orange.latens.TestTools;
import com.orange.latens.preferences.Preferences;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class AnalysisDataTest {

  private AnalysisData analysisData;

  @Before
  public void setup() {
    analysisData = new AnalysisData();
  }

  @Test
  public void shouldClearData() {
    // given
    analysisData.addDrawingEventForStats(1234);
    analysisData.addTouchEventForStats(12, 34, 56);
    // do
    analysisData.clear();
    // then
    Assert.assertTrue(analysisData.getDrawingEvents().isEmpty());
    Assert.assertTrue(analysisData.getLatencies().isEmpty());
    Assert.assertTrue(analysisData.getTouchEvents().isEmpty());
  }

  @Test
  public void shouldAddDrawingEventForStats() {
    // given
    long date = 1234;
    // do
    analysisData.addDrawingEventForStats(date);
    // then
    Assert.assertEquals(date, (long)analysisData.getDrawingEvents().get(0));
  }

  @Test
  public void shouldAddTouchEventForStats() {
    // given
    long date = 12;
    long elapsed = 34;
    float latensN = 56.78f;
    // do
    analysisData.addTouchEventForStats(date, elapsed, latensN);
    LatensPoint latensPoint = analysisData.getLatencies().get(0);
    // then
    Assert.assertEquals(date, (long)analysisData.getTouchEvents().get(0));
    Assert.assertEquals(elapsed, latensPoint.getDateMs());
    Assert.assertEquals(latensN, latensPoint.getLatensMs(), 0);
  }

  @Test
  public void shouldUpdateTouchMeanPeriod() throws IllegalAccessException {
    // given
    analysisData = Mockito.mock(AnalysisData.class);
    Mockito.doCallRealMethod().when(analysisData).updateTouchMeanPeriod(null);
    Preferences preferences = Mockito.mock(Preferences.class);
    TestTools.setObj("preferences", AnalysisData.class, analysisData, preferences);
    List<Long> touchEvents = new ArrayList<Long>();
    long period = 100;
    touchEvents.add(period);
    touchEvents.add(2*period);
    TestTools.setObj("touchEvents", AnalysisData.class, analysisData, touchEvents);
    // do
    analysisData.updateTouchMeanPeriod(null);
    // then
    Mockito.verify(preferences, Mockito.times(1)).setTouchMeanPeriodMs(null, period);
  }
}
