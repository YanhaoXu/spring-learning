package com.github.xuyh.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RefreshScope
public class TestController {

  @Value("${test.txt}")
  private String txt;

  @GetMapping("/test")
  public String test() {
    return txt;
  }

}
