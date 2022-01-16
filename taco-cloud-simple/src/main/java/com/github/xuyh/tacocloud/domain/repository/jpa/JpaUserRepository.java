package com.github.xuyh.tacocloud.domain.repository.jpa;

import com.github.xuyh.tacocloud.domain.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface JpaUserRepository extends CrudRepository<User, Long> {
  User findByUsername(String username);
}
