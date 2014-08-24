package com.orange.latens.core.stats;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(suppressConstructorProperties=true)
@Getter
/** Measured latens at a given date */
public class LatensPoint {
  private final float latensMs;
  private final long dateMs;
}
