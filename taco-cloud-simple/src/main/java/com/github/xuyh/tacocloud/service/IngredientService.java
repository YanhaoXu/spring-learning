package com.github.xuyh.tacocloud.service;

import com.github.xuyh.tacocloud.repository.JpaIngredientRepository;
import com.github.xuyh.tacocloud.web.model.Ingredient;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {
  //  @Autowired private JdbcIngredientRepository ingredientRepo;
  @Autowired private JpaIngredientRepository ingredientRepo;

  public List<Ingredient> getAllIngredients() {
    List<Ingredient> ingredientList = Lists.newArrayList();
    ingredientRepo.findAll().forEach(ingredientList::add);
    return ingredientList;
  }

  public Ingredient getIngredientById(String id) {
    //    return jdbcIngredientRepository.findOne(id);
    return ingredientRepo.findById(id).orElse(null);
  }

  public Ingredient saveIngredient(Ingredient ingredient) {

    return ingredientRepo.save(ingredient);
  }
}
