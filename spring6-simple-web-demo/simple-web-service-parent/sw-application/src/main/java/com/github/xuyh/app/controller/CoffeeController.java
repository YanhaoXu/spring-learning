package com.github.xuyh.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.xuyh.app.domain.entity.Coffee;
import com.github.xuyh.app.service.CoffeeService;
import com.github.xuyh.web.model.ApiResult;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/spring6bucks/coffee")
@Slf4j
public class CoffeeController {
  @Autowired
  private CoffeeService coffeeService;

  @GetMapping
  public ApiResult<List<Coffee>> getAllCoffee() {
    List<Coffee> coffeeList = coffeeService.getAllCoffee();
    log.info("coffee list:{}", coffeeList);
    return ApiResult.success(coffeeList);
  }
}
