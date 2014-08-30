package com.orange.latens.core.stats;

import com.orange.latens.TestTools;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class LatensStatsAddToFilterTest {

  @Test
  public void shouldAddToFilter() throws IllegalAccessException {
    // given
    int latens = 1235;
    float beta = 0.678f;
    float latensN = 234.3f;
    float expectedLatensN = 556.52545f;
    LatensStats latensStats = Mockito.mock(LatensStats.class);
    Mockito.doCallRealMethod().when(latensStats).addToFilter(latens);
    TestTools.setObj("latensN", LatensStats.class, latensStats, latensN);
    TestTools.setObj("beta", LatensStats.class, latensStats, beta);
    // do
    latensStats.addToFilter(latens);
    // then
    Assert.assertEquals(expectedLatensN, (Float) TestTools.getObj("latensN", LatensStats.class, latensStats), 0);
  }
}
