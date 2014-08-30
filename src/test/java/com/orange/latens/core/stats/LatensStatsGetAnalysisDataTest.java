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

import android.content.Context;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class LatensStatsGetAnalysisDataTest {

  @Test
  public void shouldGetAnalysisData() {
    // given
    Context context = Mockito.mock(Context.class);
    TextView textView = Mockito.mock(TextView.class);
    ProgressBar progressBar = Mockito.mock(ProgressBar.class);
    Mockito.when(textView.getContext()).thenReturn(context);
    LatensStats latensStats = new LatensStats(textView, progressBar);
    // do
    AnalysisData analysisData = LatensStats.getAnalysisData();
    // then
    Assert.assertNotNull(analysisData);
  }
}
