package com.orange.latens.core.stats;

import com.orange.latens.TestTools;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class LatensStatsUpdateAnalysisFinishedTest {

  @Test
  public void shouldUpdateAnalysisFinishedWhenNotFinished() throws IllegalAccessException {
    // given
    long elapsed = 123;
    long analysisDuration = elapsed + 1000;
    LatensStats latensStats = Mockito.mock(LatensStats.class);
    Mockito.doCallRealMethod().when(latensStats).updateAnalysisFinished(elapsed);
    TestTools.setObj("analysisDuration", LatensStats.class, latensStats, analysisDuration);
    // do
    latensStats.updateAnalysisFinished(elapsed);
    // then
    Mockito.verify(latensStats, Mockito.times(0)).analysisFinished();
    Mockito.verify(latensStats, Mockito.times(1)).setProgressBar(elapsed);
  }

  @Test
  public void shouldUpdateAnalysisFinishedWhenFinished() throws IllegalAccessException {
    // given
    long elapsed = 123;
    long analysisDuration = elapsed - 1000;
    LatensStats latensStats = Mockito.mock(LatensStats.class);
    Mockito.doCallRealMethod().when(latensStats).updateAnalysisFinished(elapsed);
    TestTools.setObj("analysisDuration", LatensStats.class, latensStats, analysisDuration);
    // do
    latensStats.updateAnalysisFinished(elapsed);
    // then
    Mockito.verify(latensStats, Mockito.times(1)).analysisFinished();
    Mockito.verify(latensStats, Mockito.times(1)).setProgressBar(elapsed);
  }
}
