package com.orange.latens.core.stats;

/** Measured latens at a given date */
public class LatensPoint {
  private final float latensMs;
  private final long dateMs;

  public LatensPoint(float latensMs, long dateMs) {
    this.latensMs = latensMs;
    this.dateMs = dateMs;
  }

  /**
   * @return latens in milliseconds
   */
  public float getLatensMs() {
    return latensMs;
  }

  /**
   * @return date in milliseconds
   */
  public long getDateMs() {
    return dateMs;
  }
}
