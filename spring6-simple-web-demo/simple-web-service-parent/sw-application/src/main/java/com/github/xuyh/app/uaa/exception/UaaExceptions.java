package com.github.xuyh.app.uaa.exception;

import static com.github.xuyh.web.exception.BaseException.DEFAULT_CODE;

import org.springframework.http.HttpStatus;

import com.github.xuyh.web.exception.BaseException;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UaaExceptions {
  TOKEN_EXPIRED("${token.expired:token过期}", 11007), TOKEN_INVALID("${token.invalid:无效的token}",
      11008), MISSING_TOKEN("${miss.token:缺少token}", DEFAULT_CODE), NOT_BEARER_TOKEN(
          "${not.bearer.token:token格式不对，请加上\"bearer \"}", DEFAULT_CODE),

  USER_IS_DISABLE("${user.is.disable:账号已被停用}", DEFAULT_CODE), USER_NO_ROLE(
      "${user.no.role:当前用户未授权角色}",
      DEFAULT_CODE), ROLE_IS_DISABLE("${role.is.disable:角色已被停用}", DEFAULT_CODE),

  FORBID_DELETE("${forbid.delete:禁止删除操作}", DEFAULT_CODE),

  FORBID_STOP_SUPER_USER("${forbid.stop.super.user:禁止停用超管账号}",
      DEFAULT_CODE), SUPER_USER_NO_SUPER_PERMISSION(
          "${super.admin.must.has.super.auth:超级管理员必须拥有超管权限！}", DEFAULT_CODE),

  PASSWORD_INCORRECT("${password.incorrect:密码不正确！}", DEFAULT_CODE),;

  public BaseException exception() {
    return new BaseException(getMessage(), getCode(), HttpStatus.BAD_REQUEST);
  }

  @Getter
  private final String message;

  @Getter
  private final int code;
}
