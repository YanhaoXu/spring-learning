package com.github.xuyh.tacocloud.domain.service;

import com.github.xuyh.tacocloud.domain.entity.User;
import com.github.xuyh.tacocloud.domain.repository.jpa.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  @Autowired private JpaUserRepository userRepo;

  @Override
  public User findByUsername(String username) {
    return userRepo.findByUsername(username);
  }
}
