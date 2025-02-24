package com.github.xuyh.app.uaa.authentication.filter;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.github.xuyh.app.domain.dto.UserLoginInfo;
import com.github.xuyh.app.service.JwtService;
import com.github.xuyh.app.uaa.core.UaaConstants;
import com.github.xuyh.app.uaa.exception.UaaExceptions;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


/**
 * jwt token过滤器, 验证token有效性
 */
@RequiredArgsConstructor
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    String authorization = request.getHeader(UaaConstants.HEADER_KEY_AUTHORIZATION);
    if (StringUtils.isEmpty(authorization)) {
      throw UaaExceptions.MISSING_TOKEN.exception();
    }

    String jwtToken;
    if (StringUtils.startsWith(authorization, UaaConstants.TOKEN_PREFIX)) {
      jwtToken = StringUtils.substringAfter(authorization, UaaConstants.TOKEN_PREFIX);
    } else {
      throw UaaExceptions.NOT_BEARER_TOKEN.exception();
    }

    // 认证开始前, 按照SpringSecurity的设计, 要将Authentication设置到SecurityContext中
    ApiAuthentication authentication = new ApiAuthentication();
    authentication.setJwtToken(jwtToken);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    UserLoginInfo currentUserLoginInfo;
    try {
      currentUserLoginInfo = jwtService.verifyJwt(jwtToken, UserLoginInfo.class);
    } catch (ExpiredJwtException e) {
      throw UaaExceptions.TOKEN_EXPIRED.exception();
    } catch (Exception e) {
      throw UaaExceptions.TOKEN_INVALID.exception();
    }

    // 认证通过 设置为true
    authentication.setAuthenticated(true);
    authentication.setCurrentUser(currentUserLoginInfo);

    // 放行
    filterChain.doFilter(request, response);
  }
}
