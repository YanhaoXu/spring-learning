package tech.pdai.pdaitechspringboothellodemo.service.impl;

import com.google.common.collect.Lists;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.pdai.pdaitechspringboothellodemo.dao.UserRepository;
import tech.pdai.pdaitechspringboothellodemo.entity.User;
import tech.pdai.pdaitechspringboothellodemo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public void save(User user) {
    userRepository.save(user);
  }

  @Override
  public void update(User user) {
    userRepository.save(user);
  }

  @Override
  public boolean exists(long userId) {
    return userRepository.existsById(userId);
  }

  @Override
  public List<User> list() {
    return Lists.newArrayList(userRepository.findAll());
  }
}
