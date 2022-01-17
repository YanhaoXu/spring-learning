package com.github.xuyh.tacocloud.domain.service;

import com.github.xuyh.tacocloud.domain.entity.User;

public interface UserService {
  User findByUsername(String username);
}
