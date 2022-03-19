package com.github.xuyh.think.in.spring.aop.overview.echo;

public class DefaultEchoService implements EchoService {
  @Override
  public String echo(String message) {
    return "[ECHO]" + message;
  }
}
