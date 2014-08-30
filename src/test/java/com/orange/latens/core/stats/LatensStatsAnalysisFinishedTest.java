package com.orange.latens.core.stats;

import android.content.Context;

import com.orange.latens.TestTools;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class LatensStatsAnalysisFinishedTest {

  @Test
  public void shouldHandleAnalysisFinished() throws IllegalAccessException {
    // given
    LatensStats latensStats = Mockito.mock(LatensStats.class);
    Mockito.doCallRealMethod().when(latensStats).analysisFinished();
    AnalysisData analysisData = Mockito.mock(AnalysisData.class);
    Context ctx = Mockito.mock(Context.class);
    TestTools.setObj("analysisData", LatensStats.class, latensStats, analysisData);
    TestTools.setObj("ctx", LatensStats.class, latensStats, ctx);
    // do
    latensStats.analysisFinished();
    // then
    Assert.assertTrue((Boolean) TestTools.getObj("analysisFinished", LatensStats.class, latensStats));
    Mockito.verify(analysisData, Mockito.times(1)).updateTouchMeanPeriod(ctx);
  }
}
