package com.github.xuyh.tacocloud.domain.service;

import com.github.xuyh.tacocloud.domain.entity.Ingredient;

import java.util.List;

public interface IngredientService {
  List<Ingredient> getAllIngredients();

  Ingredient getIngredientById(String id);

  Ingredient saveIngredient(Ingredient ingredient);
}
