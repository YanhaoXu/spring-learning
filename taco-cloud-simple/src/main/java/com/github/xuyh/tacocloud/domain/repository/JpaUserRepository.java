package com.github.xuyh.tacocloud.domain.repository;

import com.github.xuyh.tacocloud.domain.model.User;
import org.springframework.data.repository.CrudRepository;

public interface JpaUserRepository extends CrudRepository<User, Long> {
  User findByUsername(String username);
}
