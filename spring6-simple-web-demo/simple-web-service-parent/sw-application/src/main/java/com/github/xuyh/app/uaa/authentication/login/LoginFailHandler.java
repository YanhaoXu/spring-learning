package com.github.xuyh.app.uaa.authentication.login;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.github.xuyh.common.util.JsonUtils;
import com.github.xuyh.web.model.ApiResult;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * AbstractAuthenticationProcessingFilter抛出的AuthenticationException异常会在这里处理.
 */
@Component
public class LoginFailHandler implements AuthenticationFailureHandler {
  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException exception) throws IOException, ServletException {
    String errorMessage = exception.getMessage();
    // TODO
    response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
    try (PrintWriter writer = response.getWriter()) {
      ApiResult<Object> errorResult =
          ApiResult.error(HttpStatus.BAD_REQUEST.value(), errorMessage, null);
      writer.print(JsonUtils.stringify(errorResult));
      writer.flush();
    }
  }
}
