package com.github.xuyh.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.xuyh.web.exception.ExceptionUtils;
import com.github.xuyh.web.model.ApiResult;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/hello")
@Slf4j
public class HelloController {

  @GetMapping
  public ApiResult<Void> hello() {
    log.info("hello");
    return ApiResult.success("hello");
  }

  @GetMapping("/error")
  public ApiResult<Void> error() {
    ExceptionUtils.throwException("hello excepiton");
    return ApiResult.success("hello");
  }
}
