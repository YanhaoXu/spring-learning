package com.github.xuyh.tacos.domain.service;

import com.github.xuyh.tacos.domain.model.Ingredient;
import com.github.xuyh.tacos.domain.repository.jpa.IngredientRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {
  @Autowired private IngredientRepository ingredientRepository;

  @Override
  public List<Ingredient> findAll() {
    return Lists.newArrayList(ingredientRepository.findAll());
  }
}
