package com.orange.latens;

import org.powermock.api.mockito.PowerMockito;

public class TestTools {

  public static Object getObj(String fieldName, Class clazz, Object instance) throws IllegalArgumentException, IllegalAccessException {
    return PowerMockito.field(clazz, fieldName).get(instance);
  }

  public static void setObj(String fieldName, Class clazz, Object instance, Object value) throws IllegalArgumentException, IllegalAccessException {
    PowerMockito.field(clazz, fieldName).set(instance, value);
  }
}
