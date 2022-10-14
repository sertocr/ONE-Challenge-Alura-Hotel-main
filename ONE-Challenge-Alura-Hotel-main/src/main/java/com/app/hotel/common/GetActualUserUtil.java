package com.app.hotel.common;

import org.springframework.security.core.context.SecurityContextHolder;

public class GetActualUserUtil {

  public static String getActualUser() {
    return SecurityContextHolder.getContext().getAuthentication().getName();
  }
}
