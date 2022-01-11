package com.github.xuyh.tacocloud.service;

import com.github.xuyh.tacocloud.web.model.Ingredient;

import java.util.List;

public interface IngredientService {
  List<Ingredient> getAllIngredients();

  Ingredient getIngredientById(String id);

  Ingredient saveIngredient(Ingredient ingredient);
}
