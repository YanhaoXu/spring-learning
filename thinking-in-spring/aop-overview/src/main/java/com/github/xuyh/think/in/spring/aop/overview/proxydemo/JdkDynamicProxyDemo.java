package com.github.xuyh.think.in.spring.aop.overview.proxydemo;

import com.github.xuyh.think.in.spring.aop.overview.echo.DefaultEchoService;
import com.github.xuyh.think.in.spring.aop.overview.echo.EchoService;
import com.github.xuyh.think.in.spring.aop.overview.echo.ProxyEchoService;

import java.lang.reflect.Proxy;

public class JdkDynamicProxyDemo {
  public static void main(String[] args) {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class}, (proxy12, method, args12) -> {
      if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
        ProxyEchoService echoService = new ProxyEchoService(new DefaultEchoService());
        return echoService.echo((String) args12[0]);
      }
      return null;
    });

    EchoService echoService = (EchoService) proxy;
    echoService.echo("Hello, World!");

    Proxy.newProxyInstance(classLoader, new Class[]{Comparable.class}, (proxy1, method, args1) -> null);
  }
}
