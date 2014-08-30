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

import com.orange.latens.TestTools;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class LatensStatsCtorTest {

  @Test
  public void shouldConstruct() throws IllegalAccessException {
    // given
    Context context = Mockito.mock(Context.class);
    TextView textView = Mockito.mock(TextView.class);
    ProgressBar progressBar = Mockito.mock(ProgressBar.class);
    Mockito.when(textView.getContext()).thenReturn(context);
    // do
    LatensStats latensStats = new LatensStats(textView, progressBar);
    // then
    Assert.assertEquals(context, TestTools.getObj("ctx", LatensStats.class, latensStats));
    Assert.assertEquals(textView, TestTools.getObj("textView", LatensStats.class, latensStats));
    Assert.assertEquals(progressBar, TestTools.getObj("progressBar", LatensStats.class, latensStats));
    Assert.assertNotNull(TestTools.getObj("softReferenceAnalysisData", LatensStats.class, latensStats));
  }
}
