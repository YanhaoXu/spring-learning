package com.github.xuyh.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.xuyh.entity.UserBorrowDetail;
import com.github.xuyh.service.BorrowService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/")
@Slf4j
public class BorrowController {

  @Autowired
  private BorrowService borrowService;

  @HystrixCommand(fallbackMethod = "onError")
  @GetMapping("/borrow/{uid}")
  public UserBorrowDetail findUserBorrows(@PathVariable("uid") int uid) {
    return borrowService.getUserBorrowDetailByUid(uid);
  }

  UserBorrowDetail onError(int uid) {
    log.info("执行补救方法:{}", "findUserBorrows-onError");
    return new UserBorrowDetail(null, Collections.emptyList());
  }
}
