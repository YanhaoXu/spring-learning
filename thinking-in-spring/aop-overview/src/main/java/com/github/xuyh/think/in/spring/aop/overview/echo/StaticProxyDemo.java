package com.github.xuyh.think.in.spring.aop.overview.echo;

public class StaticProxyDemo {
  public static void main(String[] args) {
    EchoService echoService = new ProxyEchoService(new DefaultEchoService());
    echoService.echo("Hello, Spring!");
  }
}
