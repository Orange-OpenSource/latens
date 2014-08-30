package com.orange.latens.core.stats;

import android.widget.TextView;

import com.orange.latens.TestTools;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class LatensStatsDoStatViewUpdateTest {

  @Test
  public void shouldDoStatViewUpdate() throws IllegalAccessException {
    // given
    float latensN = 34.56f;
    TextView textView = Mockito.mock(TextView.class);
    LatensStats latensStats = Mockito.mock(LatensStats.class);
    Mockito.doCallRealMethod().when(latensStats).doStatViewUpdate();
    TestTools.setObj("textView", LatensStats.class, latensStats, textView);
    TestTools.setObj("latensN", LatensStats.class, latensStats, latensN);
    String msg= String.format("%d ms", (int)latensN);
    // do
    latensStats.doStatViewUpdate();
    // then
    Mockito.verify(textView, Mockito.times(1)).setText(msg);
  }
}
