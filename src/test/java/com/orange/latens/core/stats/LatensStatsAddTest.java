package com.orange.latens.core.stats;

import com.orange.latens.TestTools;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class LatensStatsAddTest {

  @Test
  public void shouldAddWhenAnalysisIsNotFinished() throws IllegalAccessException {
    // given
    int latens = 1235;
    boolean analysisFinished = false;
    LatensStats latensStats = Mockito.mock(LatensStats.class);
    Mockito.doCallRealMethod().when(latensStats).add(latens);
    TestTools.setObj("analysisFinished", LatensStats.class, latensStats, analysisFinished);
    // do
    latensStats.add(latens);
    // then
    Mockito.verify(latensStats, Mockito.times(1)).doAdd(latens);
  }

  @Test
  public void shouldAddWhenAnalysisIsFinished() throws IllegalAccessException {
    // given
    int latens = 1235;
    boolean analysisFinished = true;
    LatensStats latensStats = Mockito.mock(LatensStats.class);
    Mockito.doCallRealMethod().when(latensStats).add(latens);
    TestTools.setObj("analysisFinished", LatensStats.class, latensStats, analysisFinished);
    // do
    latensStats.add(latens);
    // then
    Mockito.verify(latensStats, Mockito.times(0)).doAdd(latens);
  }
}
