package com.orange.latens.core.stats;

import com.orange.latens.TestTools;
import com.orange.latens.preferences.Preferences;

import org.junit.Assert;
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
    long analysisDuration = 1235;
    LatensStats latensStats = Mockito.mock(LatensStats.class);
    Mockito.doCallRealMethod().when(latensStats).refreshAnalysisDuration();
    Preferences preferences = Mockito.mock(Preferences.class);
    Mockito.when(preferences.getAnalysisDurationMs(null)).thenReturn(analysisDuration);
    TestTools.setObj("preferences", LatensStats.class, latensStats, preferences);
    // do
    latensStats.refreshAnalysisDuration();
    // then
    Assert.assertEquals(analysisDuration, TestTools.getObj("analysisDuration", LatensStats.class, latensStats));
  }
}
