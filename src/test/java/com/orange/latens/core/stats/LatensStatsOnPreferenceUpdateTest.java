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
