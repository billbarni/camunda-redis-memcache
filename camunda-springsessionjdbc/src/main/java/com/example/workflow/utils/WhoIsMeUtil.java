package com.example.workflow.utils;

import java.util.UUID;

public class WhoIsMeUtil {

  private static String whoIsMe;

  public static String whoIsMe() {
    if (whoIsMe == null) {
      whoIsMe = UUID.randomUUID().toString();
    }
    return whoIsMe;
  }

}
