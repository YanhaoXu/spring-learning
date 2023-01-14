package tech.pdai.pdaitechspringboothellodemo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.pdai.pdaitechspringboothellodemo.dao.UserRepository;
import tech.pdai.pdaitechspringboothellodemo.entity.User;
import tech.pdai.pdaitechspringboothellodemo.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public void addUser(User user) {
    userRepository.save(user);
  }

  @Override
  public List<User> list() {
    return userRepository.findAll();
  }
}
