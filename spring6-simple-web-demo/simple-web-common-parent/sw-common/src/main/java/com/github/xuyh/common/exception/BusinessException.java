package com.github.xuyh.common.exception;

import java.io.Serial;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BusinessException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 5424464462007150187L;

  private Integer code;
  private String message;


  public BusinessException(Integer code, String message) {
    super(message);
    this.code = code;
    this.message = message;
  }

  public BusinessException(String message) {
    super(message);
    this.message = message;
  }
}
