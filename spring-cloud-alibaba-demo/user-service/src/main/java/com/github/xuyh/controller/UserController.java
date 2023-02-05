package com.github.xuyh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.xuyh.entity.User;
import com.github.xuyh.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/")
@Slf4j
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/user/{uid}")
  public User findUserById(@PathVariable("uid") int uid) {
    log.info("调用用户服务");
    return userService.getUserById(uid);
  }
}
