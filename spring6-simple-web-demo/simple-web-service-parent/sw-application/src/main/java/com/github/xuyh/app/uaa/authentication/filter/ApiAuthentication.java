package com.github.xuyh.app.uaa.authentication.filter;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import com.github.xuyh.app.domain.dto.UserLoginInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiAuthentication extends AbstractAuthenticationToken {

  /**
   * 前端传来的信息
   */
  private String jwtToken;

  /**
   * 当前登陆用户的信息
   */
  private UserLoginInfo currentUser;

  public ApiAuthentication() {
    // 不使用父类的鉴权方式
    super(null);
  }

  @Override
  public Object getCredentials() {
    // 根据SpringSecurity的设计
    // 授权成功后, Credential(如登录密码要清空)信息需要清空
    return isAuthenticated() ? null : jwtToken;
  }

  @Override
  public Object getPrincipal() {
    // 根据SpringSecurity的设计
    // 授权成功之前, getPrincipal返回的客户端传来的数据.
    // 授权成功之后, 返回当前登陆用户的信息
    return isAuthenticated() ? currentUser : jwtToken;
  }
}
