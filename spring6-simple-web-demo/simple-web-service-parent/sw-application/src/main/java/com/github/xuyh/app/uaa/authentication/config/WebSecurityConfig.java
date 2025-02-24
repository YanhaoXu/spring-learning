package com.github.xuyh.app.uaa.authentication.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.github.xuyh.app.service.JwtService;
import com.github.xuyh.app.uaa.authentication.filter.JwtTokenAuthenticationFilter;
import com.github.xuyh.app.uaa.authentication.handler.exception.CustomAuthenticationEntryPoint;
import com.github.xuyh.app.uaa.authentication.handler.exception.CustomAuthorizationExceptionHandler;
import com.github.xuyh.app.uaa.authentication.handler.exception.CustomSecurityExceptionHandlerFilter;
import com.github.xuyh.app.uaa.authentication.login.LoginFailHandler;
import com.github.xuyh.app.uaa.authentication.login.LoginSuccessHandler;
import com.github.xuyh.app.uaa.authentication.login.username.UsernameAuthenticationFilter;
import com.github.xuyh.app.uaa.authentication.login.username.UsernameAuthenticationProvider;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@Slf4j
public class WebSecurityConfig {

  @Autowired
  private ApplicationContext applicationContext;

  @Autowired
  private LoginSuccessHandler loginSuccessHandler;

  @Autowired
  private LoginFailHandler loginFailHandler;

  @Autowired
  private CustomAuthenticationEntryPoint authenticationEntryPoint;

  @Autowired
  private CustomAuthorizationExceptionHandler accessDeniedHandler;

  @Autowired
  private CustomSecurityExceptionHandlerFilter customSecurityExceptionHandlerFilter;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }


  /**
   * 登录api.
   */
  @Bean
  public SecurityFilterChain loginFilterChain(HttpSecurity http) throws Exception {

    commonHttpSetting(http);

    String usernameLoginPath = "/auth/login";

    // 使用securityMatcher限定当前配置作用的路径
    http.securityMatcher(usernameLoginPath)
        .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated());

    // 加一个登录方式, 用户名密码登录
    UsernameAuthenticationFilter usernameAuthenticationFilter = new UsernameAuthenticationFilter(
        new AntPathRequestMatcher(usernameLoginPath, HttpMethod.POST.name()),
        new ProviderManager(
            List.of(applicationContext.getBean(UsernameAuthenticationProvider.class))),
        loginSuccessHandler, loginFailHandler);
    http.addFilterBefore(usernameAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  /**
   * 不鉴权放行的api.
   */
  @Bean
  public SecurityFilterChain openFilterChain(HttpSecurity http) throws Exception {
    commonHttpSetting(http);
    http.securityMatcher("/api/spring6bucks/**")
        .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll());
    return http.build();
  }

  /**
   * 默认过滤链.
   */
  @Bean
  @Order
  public SecurityFilterChain defaultApiFilterChain(HttpSecurity http) throws Exception {
    commonHttpSetting(http);
    // 不用securityMatcher表示缺省, 匹配不上其他过滤链时, 全走这个链
    http.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated());
    // 要求登录后才能访问
    http.addFilterBefore(
        new JwtTokenAuthenticationFilter(applicationContext.getBean(JwtService.class)),
        UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  /**
   * 禁用不必要的默认filter, 处理异常响应内容.
   */
  private void commonHttpSetting(HttpSecurity http) throws Exception {
    // 禁用默认表单登录
    http.formLogin(AbstractHttpConfigurer::disable)
        // 禁用HTTP基本认证 前后端分离架构中通常使用Token认证
        .httpBasic(AbstractHttpConfigurer::disable)
        // 禁用默认的登出功能
        .logout(AbstractHttpConfigurer::disable)
        // 禁用session管理 前后端分离项目用Token维持用户会话, 不需要session
        .sessionManagement(AbstractHttpConfigurer::disable)
        // 禁用CSRF防护 前后端分离项目使用AJAX请求不会用表单提交, 不需要CSRF防护CustomAuthenticationExceptionHandler
        .csrf(AbstractHttpConfigurer::disable)
        // 禁用请求缓存 Spring Security 默认缓存请求在用户认证后重定向回来, 前后端分离项目不需要这个功能
        .requestCache(cache -> cache.requestCache(new NullRequestCache()))
        // 禁用全局匿名访问功能
        .anonymous(AbstractHttpConfigurer::disable);

    // 处理SpringSecurity 异常响应结果
    http.exceptionHandling(
        exceptionHandling -> exceptionHandling.authenticationEntryPoint(authenticationEntryPoint)
            .accessDeniedHandler(accessDeniedHandler));
    http.addFilterBefore(customSecurityExceptionHandlerFilter, SecurityContextHolderFilter.class);
  }
}
