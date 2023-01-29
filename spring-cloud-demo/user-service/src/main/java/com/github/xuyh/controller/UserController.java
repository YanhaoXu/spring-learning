package com.github.xuyh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.xuyh.entity.User;
import com.github.xuyh.service.UserService;

@RestController
@RequestMapping("/")
public class UserController {

  @Autowired
  UserService userService;

  @GetMapping("/user/{uid}")
  public User findUserById(@PathVariable("uid") int uid) {
    return userService.getUserById(uid);
  }
}
