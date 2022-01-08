package com.github.xuyh.tacocloud.repository;

import com.github.xuyh.tacocloud.web.model.Taco;

public interface JdbcTacoRepository {
  Taco save(Taco taco);
}
