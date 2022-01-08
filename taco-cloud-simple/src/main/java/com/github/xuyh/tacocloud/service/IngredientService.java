package com.github.xuyh.tacocloud.service;

import com.github.xuyh.tacocloud.repository.IngredientRepository;
import com.github.xuyh.tacocloud.web.model.Ingredient;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {
  @Autowired private IngredientRepository ingredientRepository;

  public List<Ingredient> getAllIngredients() {
    List<Ingredient> ingredientList = Lists.newArrayList();
    ingredientRepository.findAll().forEach(ingredientList::add);
    return ingredientList;
  }

  public Ingredient getIngredientById(String id) {
    return ingredientRepository.findOne(id);
  }

  public Ingredient saveIngredient(Ingredient ingredient) {
    return ingredientRepository.save(ingredient);
  }
}
