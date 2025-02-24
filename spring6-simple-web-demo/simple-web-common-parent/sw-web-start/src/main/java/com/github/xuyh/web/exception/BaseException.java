package com.github.xuyh.web.exception;

import java.io.Serial;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = 2746258574369679642L;

  public static final HttpStatus DEFAULT_HTTP_STATUS = HttpStatus.BAD_REQUEST;
  public static final int DEFAULT_CODE = HttpStatus.BAD_REQUEST.value();

  private final HttpStatus httpStatus;

  private final Integer code; // 自定义一个全局唯一code

  public BaseException(String message, Integer code, HttpStatus httpStatus) {
    super(message);
    this.code = code;
    this.httpStatus = httpStatus;
  }
}
