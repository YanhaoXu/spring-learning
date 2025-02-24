package com.github.xuyh.app.uaa.authentication.login.username;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.github.xuyh.app.domain.entity.User;
import com.github.xuyh.app.service.UserService;

/**
 * 账号密码登录认证.
 */
@Component
public class UsernameAuthenticationProvider implements AuthenticationProvider {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserService userService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    // 获取用户提交的用户名和密码
    UsernameAuthentication reqParam = (UsernameAuthentication) authentication;

    // 查询数据库, 匹配用户信息
    User user = userService.getUserByUsername(reqParam.getUsername());
    if (Objects.isNull(user)
        || !passwordEncoder.matches(reqParam.getPassword(), user.getPassword())) {
      // 密码错误直接抛出异常.
      // 根据SpringSecurity框架的代码逻辑, 认证失败时, 应该抛出这个异常:
      // org.springframework.security.core.AuthenticationException
      // BadCredentialsException就是这个异常的子类,
      // 抛出异常后, AuthenticationFailureHandler的实现类会处理这个的异常.
      throw new BadCredentialsException("用户名或密码不正确");
    }

    reqParam.setUser(user);
    // 认证通过, 这里一定要设成true
    reqParam.setAuthenticated(true);
    return reqParam;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.isAssignableFrom(UsernameAuthentication.class);
  }
}
