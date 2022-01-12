package com.github.xuyh.tacocloud.domain.repository;

import com.github.xuyh.tacocloud.domain.model.Taco;

public interface JdbcTacoRepository {
  Taco save(Taco taco);
}
