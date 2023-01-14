package tech.pdai.pdaitechspringboothellodemo.service;

import java.util.List;
import tech.pdai.pdaitechspringboothellodemo.entity.User;

public interface UserService {

  void save(User user);

  void update(User user);

  boolean exists(long userId);

  List<User> list();
}
