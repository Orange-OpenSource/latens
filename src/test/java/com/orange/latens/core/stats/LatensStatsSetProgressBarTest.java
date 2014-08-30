package com.orange.latens.core.stats;

import android.widget.ProgressBar;

import com.orange.latens.TestTools;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class LatensStatsSetProgressBarTest {

  @Test
  public void shouldSetProgressBar() throws IllegalAccessException {
    // given
    int progress = 13;
    int analysisDuration = 123;
    ProgressBar progressBar = Mockito.mock(ProgressBar.class);
    LatensStats latensStats = Mockito.mock(LatensStats.class);
    Mockito.doCallRealMethod().when(latensStats).setProgressBar(progress);
    TestTools.setObj("analysisDuration", LatensStats.class, latensStats, analysisDuration);
    TestTools.setObj("progressBar", LatensStats.class, latensStats, progressBar);
    // do
    latensStats.setProgressBar(progress);
    // then
    Mockito.verify(progressBar, Mockito.times(1)).setProgress(progress);
    Mockito.verify(progressBar, Mockito.times(1)).setMax(analysisDuration);
  }
}
