package com.github.xuyh.tacos.domain.service;

import com.github.xuyh.tacos.domain.model.Ingredient;

import java.util.List;

public interface IngredientService {
  List<Ingredient> findAll();
}
