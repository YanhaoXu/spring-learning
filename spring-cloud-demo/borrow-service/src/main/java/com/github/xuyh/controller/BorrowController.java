package com.github.xuyh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.xuyh.entity.UserBorrowDetail;
import com.github.xuyh.service.BorrowService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/")
@Slf4j
public class BorrowController {

  @Autowired
  private BorrowService borrowService;

  // @HystrixCommand(fallbackMethod = "onError") 使用openfegin熔断降级
  @GetMapping("/borrow/{uid}")
  public UserBorrowDetail findUserBorrows(@PathVariable("uid") int uid) {
    return borrowService.getUserBorrowDetailByUid(uid);
  }

  // 使用openfegin熔断降级
  // public UserBorrowDetail onError(int uid) {
  // log.info("执行补救方法:{}", "findUserBorrows-onError");
  // return new UserBorrowDetail(null, Collections.emptyList());
  // }

}
