package tech.pdai.pdaitechspringboothellodemo.service;

import tech.pdai.pdaitechspringboothellodemo.entity.User;

import java.util.List;

public interface UserService {
  void addUser(User user);

  List<User> list();
}
