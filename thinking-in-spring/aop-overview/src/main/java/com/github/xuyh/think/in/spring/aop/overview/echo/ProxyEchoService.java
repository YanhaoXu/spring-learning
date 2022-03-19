package com.github.xuyh.think.in.spring.aop.overview.echo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProxyEchoService implements EchoService {
  private final EchoService echoService;

  public ProxyEchoService(EchoService echoService) {
    this.echoService = echoService;
  }

  @Override
  public String echo(String message) {

    long startTime = System.currentTimeMillis();

    String result = echoService.echo(message);

    long costTime = System.currentTimeMillis() - startTime;
    log.info("Method echo cost {} ms.", costTime);
    return result;
  }
}
