package com.orange.latens.core.stats;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class LatensStatsRefreshAnalysisDurationTest {

  @Test
  public void shouldRefreshAnalysisDuration() throws IllegalAccessException {
    // given
    int analysisDuration = 1235;
    LatensStats latensStats = Mockito.mock(LatensStats.class);
    Mockito.doCallRealMethod().when(latensStats).refreshAnalysisDuration();
    // do
    //latensStats.refreshAnalysisDuration();
    // then
    //Assert.assertEquals(analysisDuration, TestTools.getObj("analysisDuration", LatensStats.class, latensStats));
  }
}
