package com.github.xuyh.tacocloud.domain.repository;

import com.github.xuyh.tacocloud.domain.model.Ingredient;

public interface JdbcIngredientRepository {
  Iterable<Ingredient> findAll();

  Ingredient findOne(String id);

  Ingredient save(Ingredient ingredient);
}
