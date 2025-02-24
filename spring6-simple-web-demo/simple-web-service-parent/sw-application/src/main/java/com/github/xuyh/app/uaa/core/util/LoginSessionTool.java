package com.github.xuyh.app.uaa.core.util;

import java.util.Objects;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.github.xuyh.app.domain.dto.UserLoginInfo;
import com.github.xuyh.web.exception.ExceptionUtils;

public class LoginSessionTool {

  public static UserLoginInfo getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (Objects.isNull(authentication) || !authentication.isAuthenticated()
        || Objects.isNull(authentication.getPrincipal())
        || !(authentication.getPrincipal() instanceof UserLoginInfo)) {
      ExceptionUtils.throwException("{required.login:请登录后再访问!}");
    }

    return (UserLoginInfo) authentication.getPrincipal();
  }
}
