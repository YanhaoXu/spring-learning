package com.github.xuyh.app.uaa.authentication.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.github.xuyh.app.domain.dto.UserLoginInfo;
import com.github.xuyh.app.domain.entity.Role;
import com.github.xuyh.app.domain.entity.User;
import com.github.xuyh.app.service.JwtService;
import com.github.xuyh.app.service.RoleService;
import com.github.xuyh.app.uaa.exception.UaaExceptions;
import com.github.xuyh.common.util.JsonUtils;
import com.github.xuyh.common.util.TimeUtils;
import com.github.xuyh.web.exception.BaseException;
import com.github.xuyh.web.model.ApiResult;
import com.google.common.collect.Maps;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoginSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler
    implements AuthenticationSuccessHandler {

  @Autowired
  private RoleService roleService;

  @Autowired
  private JwtService jwtService;

  public LoginSuccessHandler() {
    this.setRedirectStrategy((request, response, url) -> {
      // 更改重定向策略, 前后端分离项目后端使用Restful风格, 无需做重定向
      // Do nothing, no redirects in REST
    });
  }

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException {

    if (!(authentication instanceof LoginAuthentication loginAuthentication)) {
      throw new BaseException("Authentication必须继承LoginAuthentication", BaseException.DEFAULT_CODE,
          BaseException.DEFAULT_HTTP_STATUS);
    }
    User user = loginAuthentication.getUser();
    if (Objects.isNull(user)) {
      throw new BaseException("LoginAuthentication未设置User信息", BaseException.DEFAULT_CODE,
          BaseException.DEFAULT_HTTP_STATUS);
    }

    if (Boolean.FALSE.equals(user.getEnable())) {
      // 账号被停用
      throw UaaExceptions.USER_IS_DISABLE.exception();
    }

    // 登录成功后, 获取角色 权限相关信息
    Role role = null;
    if (Objects.isNull(loginAuthentication.getRoleCode())) {
      // 没有指定的角色登录, 随便选一个角色登录
      List<Role> roleList = roleService.getRolesByUserId(user.getId());
      if (CollectionUtils.isNotEmpty(roleList)) {
        role = roleList.getFirst();
      } else {
        // 指定登录角色
        role = roleService.getRoleByCode(loginAuthentication.getRoleCode());
      }
    }

    if (Objects.isNull(role)) {
      // 没有权限
      throw UaaExceptions.USER_NO_ROLE.exception();
    } else if (Boolean.FALSE.equals(role.getEnable())) {
      // 角色被禁用
      throw UaaExceptions.ROLE_IS_DISABLE.exception();
    }

    UserLoginInfo userLoginInfo = new UserLoginInfo();
    userLoginInfo.setSessionId(UUID.randomUUID().toString());
    userLoginInfo.setUserId(user.getId());
    userLoginInfo.setRoleCode(role.getCode());

    Map<String, Object> responseData = Maps.newLinkedHashMap();
    responseData.put("accessToken", generateToken(userLoginInfo));
    responseData.put("refreshToken", generateRefreshToken(userLoginInfo));

    response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

    try (PrintWriter writer = response.getWriter()) {
      writer.print(JsonUtils.stringify(ApiResult.success("登录成功！", responseData)));
      writer.flush();
    }
  }

  public String generateToken(UserLoginInfo currentUser) {
    long expiredTime = TimeUtils.nowMilli() + TimeUnit.DAYS.toMillis(10);
    currentUser.setExpiredTime(expiredTime);
    return jwtService.createJwt(currentUser, expiredTime);
  }

  public String generateRefreshToken(UserLoginInfo currentUser) {
    return jwtService.createJwt(currentUser, TimeUtils.nowMilli() + TimeUnit.DAYS.toMillis(10));
  }
}
