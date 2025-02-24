package com.github.xuyh.app.uaa.authentication.handler.exception;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.github.xuyh.common.util.JsonUtils;
import com.github.xuyh.web.model.ApiResult;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 认证成功(Authentication), 但无权访问时。会执行这个方法<br>
 * 或者SpringSecurity框架捕捉到 AccessDeniedException时，会转出
 */
@Component
public class CustomAuthorizationExceptionHandler implements AccessDeniedHandler {
  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException accessDeniedException) throws IOException {
    response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
    response.setStatus(HttpStatus.FORBIDDEN.value());
    try (PrintWriter printWriter = response.getWriter()) {
      printWriter.print(JsonUtils.stringify(ApiResult.error("无权限")));
      printWriter.flush();
    }
  }
}
