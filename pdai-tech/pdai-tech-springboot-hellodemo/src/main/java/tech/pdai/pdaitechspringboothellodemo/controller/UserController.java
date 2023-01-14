package tech.pdai.pdaitechspringboothellodemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.pdai.pdaitechspringboothellodemo.entity.User;
import tech.pdai.pdaitechspringboothellodemo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("add")
  public User add(@RequestBody User user) {
    userService.addUser(user);
    return user;
  }

  @GetMapping("list")
  public List<User> list() {
    return userService.list();
  }
}
