package com.github.xuyh.app.uaa.authentication.handler.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.AccessDeniedException;

import javax.security.sasl.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.github.xuyh.common.util.JsonUtils;
import com.github.xuyh.web.exception.BaseException;
import com.github.xuyh.web.model.ApiResult;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 捕捉 Spring Security filter chain 中抛出的未知异常.
 */
@Component
@Slf4j
public class CustomSecurityExceptionHandlerFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws IOException {
    try {
      filterChain.doFilter(request, response);
    } catch (BaseException e) {
      // 自定义异常
      ApiResult<Void> apiResult =
          ApiResult.<Void>builder().code(e.getCode()).msg(e.getMessage()).build();
      response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
      response.setStatus(e.getHttpStatus().value());

      writeResponse(response, JsonUtils.stringify(apiResult));
    } catch (AuthenticationException | AccessDeniedException e) {
      // 认证鉴权异常
      response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
      response.setStatus(HttpStatus.FORBIDDEN.value());

      writeResponse(response, JsonUtils.stringify(ApiResult.error(e.getMessage())));
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      // 未知异常
      ApiResult<Void> apiResult = ApiResult.<Void>builder()
          .code(ApiResult.ResponseCode.FAIL.getCode()).msg("System Error.").build();
      response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
      writeResponse(response, JsonUtils.stringify(apiResult));
    }
  }

  private void writeResponse(HttpServletResponse response, String body) throws IOException {
    try (PrintWriter printWriter = response.getWriter()) {
      printWriter.write(body);
      printWriter.flush();
    }
  }
}
