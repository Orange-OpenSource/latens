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
public class LatensStatsGetElapsedTimeTest {

  @Test
  public void shouldGetElapsedTime() throws IllegalAccessException {
    // given
    long date = 123456;
    long expected = 1234;
    long analysisStartDate = date - expected;
    LatensStats latensStats = Mockito.mock(LatensStats.class);
    Mockito.doCallRealMethod().when(latensStats).getElapsedTime();
    Mockito.when(latensStats.getDate()).thenReturn(date);
    TestTools.setObj("analysisStartDate", LatensStats.class, latensStats, analysisStartDate);
    // do
    long elapsedTime = latensStats.getElapsedTime();
    // then
    Assert.assertEquals(expected, elapsedTime);
  }
}
