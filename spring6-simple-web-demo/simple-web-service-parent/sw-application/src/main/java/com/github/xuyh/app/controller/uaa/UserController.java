package com.github.xuyh.app.controller.uaa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.xuyh.app.domain.dto.UserQuery;
import com.github.xuyh.app.domain.vo.UserItemVO;
import com.github.xuyh.app.service.UserService;
import com.github.xuyh.common.domain.dto.PageResult;
import com.github.xuyh.web.model.ApiResult;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

  @Autowired
  private UserService userService;

  public ApiResult<Void> getCurrentUserDetial() {
    userService.getCurrentUserDetail();
    return ApiResult.success();
  }

  @GetMapping
  public ApiResult<PageResult<UserItemVO>> pageQueryUser(UserQuery userQuery) {
    return ApiResult.success(userService.pageQueryUsers(userQuery));
  }

}
