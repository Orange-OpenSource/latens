package com.orange.latens.core.stats;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class LatensStatsOnPreferenceUpdateTest {

  @Test
  public void shouldUpdatePreferences() {
    // given
    LatensStats latensStats = Mockito.mock(LatensStats.class);
    Mockito.doCallRealMethod().when(latensStats).onPreferencesUpdate();
    // do
    latensStats.onPreferencesUpdate();
    // then
    Mockito.verify(latensStats, Mockito.times(1)).refreshAnalysisDuration();
  }
}
