package com.github.xuyh.common.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * The type Time utils.
 */
public class TimeUtils {

  public static Date nowDate() {
    return Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
  }

  /**
   * 当前时间戳, 毫秒.
   */
  public static long nowMilli() {
    return System.currentTimeMillis();
  }

  /**
   * 当前时间戳, 秒.
   */
  public static long nowSecond() {
    return System.currentTimeMillis() / 1000;
  }
}
