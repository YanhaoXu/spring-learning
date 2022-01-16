package com.github.xuyh.tacocloud.domain.repository.jdbc;

import com.github.xuyh.tacocloud.domain.entity.Ingredient;

public interface JdbcIngredientRepository {
  Iterable<Ingredient> findAll();

  Ingredient findOne(String id);

  Ingredient save(Ingredient ingredient);
}
