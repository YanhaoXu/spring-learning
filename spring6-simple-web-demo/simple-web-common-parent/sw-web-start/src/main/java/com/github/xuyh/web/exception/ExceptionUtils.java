package com.github.xuyh.web.exception;

import org.springframework.http.HttpStatus;

/**
 * 抛出系统异常的工具类
 */
public class ExceptionUtils {

  public static void throwException(String message) {
    throw new BaseException(message, BaseException.DEFAULT_CODE, BaseException.DEFAULT_HTTP_STATUS);
  }

  public static void throwException(String message, Integer code) {
    throw new BaseException(message, code, BaseException.DEFAULT_HTTP_STATUS);
  }

  public static void throwException(String message, HttpStatus httpStatus) {
    throw new BaseException(message, BaseException.DEFAULT_CODE, httpStatus);
  }

  public static void throwException(String message, Integer code, HttpStatus httpStatus) {
    throw new BaseException(message, code, httpStatus);
  }
}
