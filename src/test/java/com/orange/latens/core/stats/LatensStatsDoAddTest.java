package com.orange.latens.core.stats;

import com.orange.latens.TestTools;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class LatensStatsDoAddTest {

  @Test
  public void shouldDoAdd() throws IllegalAccessException {
    // given
    int latens = 1235;
    int latensN = 987;
    long date = 234;
    long elapsed = 567;
    LatensStats latensStats = Mockito.mock(LatensStats.class);
    Mockito.doCallRealMethod().when(latensStats).doAdd(latens);
    Mockito.when(latensStats.getDate()).thenReturn(date);
    Mockito.when(latensStats.getElapsedTime()).thenReturn(elapsed);
    TestTools.setObj("latensN", LatensStats.class, latensStats, latensN);
    AnalysisData analysisData = Mockito.mock(AnalysisData.class);
    TestTools.setObj("analysisData", LatensStats.class, latensStats, analysisData);
    // do
    latensStats.doAdd(latens);
    // then
    Mockito.verify(latensStats, Mockito.times(1)).addToFilter(latens);
    Mockito.verify(analysisData, Mockito.times(1)).addTouchEventForStats(date, elapsed, latensN);
    Mockito.verify(latensStats, Mockito.times(1)).updateAnalysisFinished(elapsed);
    Mockito.verify(latensStats, Mockito.times(1)).handleStatViewUpdate();
  }
}
