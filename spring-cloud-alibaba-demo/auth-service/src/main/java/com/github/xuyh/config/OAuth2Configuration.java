package com.github.xuyh.config;

import javax.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class OAuth2Configuration extends AuthorizationServerConfigurerAdapter {
  @Resource
  private AuthenticationManager manager;

  @Resource
  private UserDetailsService userDetailsService;

  @Resource
  private BCryptPasswordEncoder encoder;

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.inMemory()// 硬编码
        .withClient("web")//客户端名称
        .secret(encoder.encode("654321"))//只与客户端分享的secret 注意加密
        //        .autoApprove(false)// 自动审批
        .autoApprove(true)// 自动审批
        .scopes("book", "user", "borrow")//授权范围
        .redirectUris("http://localhost:8101/login", "http://localhost:8201/login",
            "http://localhost:8301/login")
        // 授权模式,一共有五种
        .authorizedGrantTypes("client_credentials", "password", "implicit", "authorization_code",
            "refresh_token");
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security.passwordEncoder(encoder)// 设定编码器
        .allowFormAuthenticationForClients()// 允许客户端使用表单验证
        .checkTokenAccess("permitAll()");//允许所有的Token查询请求
  }


  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    // 配置authenticationManager
    endpoints.userDetailsService(userDetailsService).authenticationManager(manager);
  }
}
