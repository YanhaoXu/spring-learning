package com.github.xuyh.app.uaa.authentication.login;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.github.xuyh.app.domain.entity.User;

import lombok.Getter;
import lombok.Setter;

/**
 * SpringSecurity传输登录认证的数据的载体, 相当于一个DTO.<br>
 * 必须是{@link Authentication} 实现类.<br>
 * 这里选择extends{@link AbstractAuthenticationToken}, 而不是直接实现{@link Authentication},<br>
 * 是为了少写代码. 因为{@link Authentication}定义的许多接口用不到.
 */
@Getter
@Setter
public abstract class LoginAuthentication extends AbstractAuthenticationToken {

  /**
   * 认证成功后, 后台从数据库获取信息.
   */
  protected User user;

  /**
   * 指定role角色登录, 非必须项目.
   */
  protected String roleCode;

  protected LoginAuthentication() {
    // 不用SpringSecurity自带的权限校验
    // 因为权限逻辑校验每个系统差异比较大
    // SpringSecurity也没怎么实现
    super(null);
  }

}
