package com.orange.latens.core.stats;

import com.orange.latens.TestTools;
import com.orange.latens.core.Constants;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class LatensStatsHandleStatViewUpdateTest {

  @Test
  public void shouldHandleStatViewUpdateWhenTooEarly() throws IllegalAccessException {
    // given
    long date = 1234;
    long lastViewUpdate = date - 1;
    LatensStats latensStats = Mockito.mock(LatensStats.class);
    Mockito.doCallRealMethod().when(latensStats).handleStatViewUpdate();
    Mockito.when(latensStats.getDate()).thenReturn(date);
    TestTools.setObj("lastViewUpdate", LatensStats.class, latensStats, lastViewUpdate);
    // do
    latensStats.handleStatViewUpdate();
    // then
    Mockito.verify(latensStats, Mockito.times(0)).doStatViewUpdate();
  }

  @Test
  public void shouldHandleStatViewUpdate() throws IllegalAccessException {
    // given
    long date = 1234;
    long lastViewUpdate = date - 1 - Constants.LATENS_VIEW_UPDATE_PERIOD_MS;
    LatensStats latensStats = Mockito.mock(LatensStats.class);
    Mockito.doCallRealMethod().when(latensStats).handleStatViewUpdate();
    Mockito.when(latensStats.getDate()).thenReturn(date);
    TestTools.setObj("lastViewUpdate", LatensStats.class, latensStats, lastViewUpdate);
    // do
    latensStats.handleStatViewUpdate();
    // then
    Mockito.verify(latensStats, Mockito.times(1)).doStatViewUpdate();
  }
}
