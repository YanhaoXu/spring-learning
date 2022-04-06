package com.github.xuyh.think.in.spring.aop.overview.proxydemo;

import com.github.xuyh.think.in.spring.aop.overview.echo.DefaultEchoService;
import com.github.xuyh.think.in.spring.aop.overview.echo.EchoService;
import com.github.xuyh.think.in.spring.aop.overview.echo.ProxyEchoService;

/**
 * 静态代理示例
 */
public class StaticProxyDemo {
  public static void main(String[] args) {
    EchoService echoService = new ProxyEchoService(new DefaultEchoService());
    echoService.echo("Hello, Spring!");
  }
}
