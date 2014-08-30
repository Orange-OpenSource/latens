package com.orange.latens.core.stats;
/*
 * Copyright (C) 2014 Orange
 * Authors: Christophe Maldivi
 *
 * This software is the confidential and proprietary information of Orange.
 * You shall not disclose such confidential information and shall use it only
 * in accordance with the terms of the license agreement you entered into
 * with Orange.
 */

import com.orange.latens.TestTools;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class LatensStatsInitAnalysisTest {

  @Test
  public void shouldInitAnalysis() throws IllegalAccessException {
    // given
    long date = 12345;
    float beta = 0.1234f;
    LatensStats latensStats = Mockito.mock(LatensStats.class);
    Mockito.doCallRealMethod().when(latensStats).initAnalysis();
    Mockito.when(latensStats.getDate()).thenReturn(date);
    Mockito.when(latensStats.getBeta()).thenReturn(beta);
    AnalysisData analysisData = Mockito.mock(AnalysisData.class);
    TestTools.setObj("analysisData", LatensStats.class, latensStats, analysisData);
    // do
    latensStats.initAnalysis();
    // then
    Assert.assertEquals(0, (Float) TestTools.getObj("latensN", LatensStats.class, latensStats), 0);
    Assert.assertFalse((Boolean) TestTools.getObj("analysisFinished", LatensStats.class, latensStats));
    Assert.assertEquals(date, TestTools.getObj("lastViewUpdate", LatensStats.class, latensStats));
    Assert.assertEquals(date, TestTools.getObj("analysisStartDate", LatensStats.class, latensStats));
    Assert.assertEquals(beta, (Float) TestTools.getObj("beta", LatensStats.class, latensStats), 0);
    Mockito.verify(latensStats, Mockito.times(1)).setProgressBar(0);
    Mockito.verify(analysisData, Mockito.times(1)).clear();
  }
}
