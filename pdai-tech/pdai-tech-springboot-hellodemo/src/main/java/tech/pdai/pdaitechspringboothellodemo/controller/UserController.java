package tech.pdai.pdaitechspringboothellodemo.controller;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.pdai.pdaitechspringboothellodemo.config.response.ResponseResult;
import tech.pdai.pdaitechspringboothellodemo.entity.User;
import tech.pdai.pdaitechspringboothellodemo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("add")
  public ResponseResult<User> add(@RequestBody User user) {

    if (user.getUserId() == null || !userService.exists(user.getUserId())) {
      user.setCreateTime(LocalDateTime.now());
      user.setUpdateTime(LocalDateTime.now());
      userService.save(user);
    } else {
      user.setUpdateTime(LocalDateTime.now());
      userService.update(user);
    }

    return ResponseResult.success(user);
  }

  @GetMapping("list")
  public ResponseResult<List<User>> list() {
    return ResponseResult.success(userService.list());
  }
}
