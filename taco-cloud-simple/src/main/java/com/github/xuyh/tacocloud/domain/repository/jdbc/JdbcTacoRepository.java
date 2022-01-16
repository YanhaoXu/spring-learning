package com.github.xuyh.tacocloud.domain.repository.jdbc;

import com.github.xuyh.tacocloud.domain.entity.Taco;

public interface JdbcTacoRepository {
  Taco save(Taco taco);
}
