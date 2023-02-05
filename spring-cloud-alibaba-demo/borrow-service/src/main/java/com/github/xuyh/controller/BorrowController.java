package com.github.xuyh.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.xuyh.entity.UserBorrowDetail;
import com.github.xuyh.service.BorrowService;

@RestController
@RequestMapping("/")
public class BorrowController {

  @Resource
  BorrowService borrowService;

  @GetMapping("/borrow/{uid}")
  public UserBorrowDetail findUserBorrows(@PathVariable("uid") int uid) {
    return borrowService.getUserBorrowDetailByUid(uid);
  }
}
