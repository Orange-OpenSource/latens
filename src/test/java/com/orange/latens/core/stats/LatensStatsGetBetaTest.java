package com.orange.latens.core.stats;

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
  public void shouldGetBeta() {
    // given
    float touchMeanPeriod = 20;
    long timeConstantMs = 30;
    float expected = 0.5134171f;
    LatensStats latensStats = Mockito.mock(LatensStats.class);
    Mockito.doCallRealMethod().when(latensStats).getBeta();
    Mockito.when(latensStats.getTouchMeanPeriodMs()).thenReturn(touchMeanPeriod);
    Mockito.when(latensStats.getTimeConstantMs()).thenReturn(timeConstantMs);
    // do
    float beta = latensStats.getBeta();
    // then
    Assert.assertEquals(expected, beta, 0);
  }
}
