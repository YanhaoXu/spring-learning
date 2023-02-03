package com.github.xuyh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.xuyh.entity.User;
import com.github.xuyh.mapper.UserMapper;
import com.github.xuyh.service.UserService;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserMapper userMapper;

  @Override
  public User getUserById(int uid) {
    return userMapper.getUserById(uid);
  }
}
