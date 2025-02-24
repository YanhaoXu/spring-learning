package com.github.xuyh.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.github.xuyh.common.exception.BusinessException;
import com.github.xuyh.web.model.ApiResult;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 全局异常处理.
 */
@RestControllerAdvice
@Slf4j
public class WebGlobalExceptionHandler {

  /**
   * 服务器异常.<br>
   * 用于兜底的全局处理器
   */
  @ExceptionHandler(value = Throwable.class)
  @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
  public ApiResult<Void> handleException(Throwable th) {
    log.error("服务器异常", th);
    return ApiResult.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
  }

  /**
   * 拦截未知的运行时异常.
   */
  @ExceptionHandler(value = RuntimeException.class)
  @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
  public ApiResult<Void> handleRuntimeException(RuntimeException e, HttpServletRequest request) {
    String requestURI = request.getRequestURI();
    log.error("请求地址'{}', 发生系统异常", requestURI, e);
    return ApiResult.error(e.getMessage());
  }

  @ExceptionHandler(value = NoResourceFoundException.class)
  @ResponseStatus(code = HttpStatus.NOT_FOUND)
  public ApiResult<Void> handleNoResourceFoundException(NoResourceFoundException e) {
    log.error("未找到目标资源", e);
    return ApiResult.error("api not found");
  }

  @ExceptionHandler(value = BaseException.class)
  public ApiResult<Void> handleBaseException(BaseException e, HttpServletResponse response) {
    response.setStatus(e.getHttpStatus().value());
    log.error("系统异常", e);
    return ApiResult.error(e);
  }


  @ExceptionHandler(value = BusinessException.class)
  public ApiResult<Void> handleBaseException(BusinessException e, HttpServletResponse response) {
    log.error(e.getMessage(), e);
    return ApiResult.error(e);
  }

}
