package com.orange.latens.core.stats;

import com.orange.latens.TestTools;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class LatensStatsAddDrawingEventForStatsTest {

  @Test
  public void shouldAddDrawingEventForStatsWhenAnalysisIsNotFinished() throws IllegalAccessException {
    // given
    boolean analysisFinished = false;
    long date = 1235;
    LatensStats latensStats = Mockito.mock(LatensStats.class);
    Mockito.doCallRealMethod().when(latensStats).addDrawingEventForStats();
    Mockito.when(latensStats.getDate()).thenReturn(date);
    TestTools.setObj("analysisFinished", LatensStats.class, latensStats, analysisFinished);
    AnalysisData analysisData = Mockito.mock(AnalysisData.class);
    TestTools.setObj("analysisData", LatensStats.class, latensStats, analysisData);
    // do
    latensStats.addDrawingEventForStats();
    // then
    Mockito.verify(analysisData, Mockito.times(1)).addDrawingEventForStats(date);
  }

  @Test
  public void shouldAddDrawingEventForStatsWhenAnalysisIsFinished() throws IllegalAccessException {
    // given
    boolean analysisFinished = true;
    LatensStats latensStats = Mockito.mock(LatensStats.class);
    Mockito.doCallRealMethod().when(latensStats).addDrawingEventForStats();
    TestTools.setObj("analysisFinished", LatensStats.class, latensStats, analysisFinished);
    AnalysisData analysisData = Mockito.mock(AnalysisData.class);
    TestTools.setObj("analysisData", LatensStats.class, latensStats, analysisData);
    // do
    latensStats.addDrawingEventForStats();
    // then
    Mockito.verify(analysisData, Mockito.times(0)).addDrawingEventForStats(Mockito.anyLong());
  }
}
