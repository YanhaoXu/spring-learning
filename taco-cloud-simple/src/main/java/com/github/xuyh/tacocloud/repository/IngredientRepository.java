package com.github.xuyh.tacocloud.repository;

import com.github.xuyh.tacocloud.web.model.Ingredient;

public interface IngredientRepository {
  Iterable<Ingredient> findAll();

  Ingredient findOne(String id);

  Ingredient save(Ingredient ingredient);
}
