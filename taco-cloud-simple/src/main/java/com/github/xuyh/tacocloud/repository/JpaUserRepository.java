package com.github.xuyh.tacocloud.repository;

import com.github.xuyh.tacocloud.web.model.User;
import org.springframework.data.repository.CrudRepository;

public interface JpaUserRepository extends CrudRepository<User, Long> {
  User findByUsername(String username);
}
