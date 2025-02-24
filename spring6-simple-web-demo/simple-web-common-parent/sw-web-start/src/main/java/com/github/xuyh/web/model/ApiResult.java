package com.github.xuyh.web.model;

import org.apache.commons.lang3.StringUtils;

import com.github.xuyh.common.exception.BusinessException;
import com.github.xuyh.web.exception.BaseException;

import lombok.*;

/**
 * 响应信息主体
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ApiResult<T> {

  private int code;

  private String msg;

  private T data;

  private long timestamp;

  /**
   * Response code 类型.
   */
  @AllArgsConstructor
  @Getter
  public enum ResponseCode {
    SUCCESS(0, StringUtils.EMPTY), FAIL(1, "fail");

    private final int code;
    private final String message;
  }

  /**
   * 返回默认成功消息.
   */
  public static <T> ApiResult<T> success() {
    return success(ResponseCode.SUCCESS.getMessage());
  }

  /**
   * 返回成功消息.
   */
  public static <T> ApiResult<T> success(String message) {
    return success(message, null);
  }

  /**
   * 返回成功数据.
   */
  public static <T> ApiResult<T> success(T data) {
    return success(ResponseCode.SUCCESS.getMessage(), data);
  }

  /**
   * 返回成功消息&数据.
   */
  public static <T> ApiResult<T> success(String message, T data) {
    return ApiResult.<T>builder().code(ResponseCode.SUCCESS.getCode()).msg(message).data(data)
        .timestamp(System.currentTimeMillis()).build();
  }

  /**
   * 返回默认错误消息.
   */
  public static <T> ApiResult<T> error() {
    return error(null, null);
  }

  /**
   * 返回错误消息.
   */
  public static <T> ApiResult<T> error(String message) {
    return error(message, null);
  }

  /**
   * 返回错误数据.
   */
  public static <T> ApiResult<T> error(T data) {
    return error(ResponseCode.FAIL.getMessage(), data);
  }

  /**
   * 返回错误消息&数据.
   */
  public static <T> ApiResult<T> error(String message, T data) {
    return ApiResult.<T>builder().code(ResponseCode.FAIL.getCode()).msg(message).data(data)
        .timestamp(System.currentTimeMillis()).build();
  }

  /**
   * 根据异常返回错误code&消息&数据.
   */
  public static <T> ApiResult<T> error(BaseException e) {
    return error(e.getCode() == null ? ApiResult.ResponseCode.FAIL.getCode() : e.getCode(),
        e.getMessage(), null);
  }


  /**
   * 根据业务异常返回错误code&消息&数据.
   */
  public static <T> ApiResult<T> error(BusinessException e) {
    return error(e.getCode() == null ? ApiResult.ResponseCode.FAIL.getCode() : e.getCode(),
        e.getMessage(), null);
  }

  /**
   * 返回错误code&消息&数据.
   */
  public static <T> ApiResult<T> error(int code, String message, T data) {
    return ApiResult.<T>builder().code(code).msg(message).data(data)
        .timestamp(System.currentTimeMillis()).build();
  }

}
