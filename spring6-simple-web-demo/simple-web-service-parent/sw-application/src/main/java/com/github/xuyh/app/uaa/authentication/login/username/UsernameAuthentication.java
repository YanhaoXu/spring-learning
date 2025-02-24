package com.github.xuyh.app.uaa.authentication.login.username;

import com.github.xuyh.app.uaa.authentication.login.LoginAuthentication;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsernameAuthentication extends LoginAuthentication {
  /**
   * 用户名, 前端传过来
   */
  private String username;

  /**
   * 密码, 前端传过来
   */
  private String password;

  /**
   * 验证码, 前端传过来
   */
  private String captcha;

  @Override
  public Object getCredentials() {
    // 根据SpringSecurity的设计
    // 授权成功之后(true), Credential(比如, 登录密码)信息需要被清空
    return isAuthenticated() ? null : password;
  }

  @Override
  public Object getPrincipal() {
    // 根据SpringSecurity的设计
    // 授权成功之前, getPrincipal返回的客户端传过来的数据
    // 授权成功之后(true), getPrincipal返回当前用户登录信息
    return isAuthenticated() ? user : username;
  }
}
