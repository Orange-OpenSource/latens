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
public class LatensStatsGetBetaTest {

  @Test
  public void shouldGetBeta() throws IllegalAccessException {
    // given
    float touchMeanPeriod = 20;
    long timeConstantMs = 30;
    float expected = 0.5134171f;
    LatensStats latensStats = Mockito.mock(LatensStats.class);
    Mockito.doCallRealMethod().when(latensStats).getBeta();
    Preferences preferences = Mockito.mock(Preferences.class);
    Mockito.when(preferences.getTouchMeanPeriodMs(null)).thenReturn(touchMeanPeriod);
    Mockito.when(preferences.getTimeConstantMs(null)).thenReturn(timeConstantMs);
    TestTools.setObj("preferences", LatensStats.class, latensStats, preferences);
    // do
    float beta = latensStats.getBeta();
    // then
    Assert.assertEquals(expected, beta, 0);
  }
}
