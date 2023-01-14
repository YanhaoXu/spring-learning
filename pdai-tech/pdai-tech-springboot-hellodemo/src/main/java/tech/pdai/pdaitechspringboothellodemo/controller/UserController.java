package tech.pdai.pdaitechspringboothellodemo.controller;

import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.pdai.pdaitechspringboothellodemo.config.response.ResponseResult;
import tech.pdai.pdaitechspringboothellodemo.entity.User;
import tech.pdai.pdaitechspringboothellodemo.param.UserParam;
import tech.pdai.pdaitechspringboothellodemo.service.UserService;
import tech.pdai.pdaitechspringboothellodemo.validation.group.AddValidationGroup;
import tech.pdai.pdaitechspringboothellodemo.validation.group.EditValidationGroup;

@RestController
@RequestMapping("/user")
@Slf4j
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

  @PostMapping("check")
  public ResponseEntity<String> checkUserAdd(
      @Validated(AddValidationGroup.class) @RequestBody UserParam userParam,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      List<ObjectError> errors = bindingResult.getAllErrors();
      errors.forEach(e -> {
        FieldError fieldError = (FieldError) e;
        log.error("Invalid Parameter : object - {}, field - {}, errorMessage - {}",
            fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
      });
      return ResponseEntity.badRequest().body("invalid parameter");
    }

    return ResponseEntity.ok("success");
  }

  @PostMapping("checkedit")
  public ResponseEntity<String> checkUserEdit(
      @Validated(EditValidationGroup.class) @RequestBody UserParam userParam,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      List<ObjectError> errors = bindingResult.getAllErrors();
      errors.forEach(e -> {
        FieldError fieldError = (FieldError) e;
        log.error("Invalid Parameter : object - {}, field - {}, errorMessage - {}",
            fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
      });
      return ResponseEntity.badRequest().body("invalid parameter");
    }

    return ResponseEntity.ok("success");
  }

  @GetMapping("list")
  public ResponseResult<List<User>> list() {
    return ResponseResult.success(userService.list());
  }
}
