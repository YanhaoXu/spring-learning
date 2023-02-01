package com.github.xuyh.service.client;

import org.springframework.stereotype.Component;

import com.github.xuyh.entity.User;

@Component
public class UserFallbackClient implements UserClient {

  @Override
  public User getUserById(int uid) {
    User user = new User();
    user.setName("我是替代方案");
    return user;
  }
}
