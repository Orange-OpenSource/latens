package com.orange.latens.core.stats;

import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class LatensPointTest {

  @Test
  public void shouldInitAndHaveGettersReturningCorrectValues() {
    // given
    float latensMs = 1234.5678f;
    int dateMs = 123456789;

    // do
    LatensPoint latensPoint = new LatensPoint(latensMs, dateMs);

    // then
    float epsilon = 0;
    Assert.assertEquals(latensMs, latensPoint.getLatensMs(), epsilon);
    Assert.assertEquals(dateMs, latensPoint.getDateMs());
  }

}
