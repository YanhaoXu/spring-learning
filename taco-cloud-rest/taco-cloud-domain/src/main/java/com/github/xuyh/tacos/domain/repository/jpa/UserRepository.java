package com.github.xuyh.tacos.domain.repository.jpa;

import com.github.xuyh.tacos.domain.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
  User findByUsername(String username);
}
