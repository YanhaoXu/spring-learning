package com.github.xuyh.tacocloud.web.support.convert;

import com.github.xuyh.tacocloud.domain.entity.Ingredient;
import com.github.xuyh.tacocloud.domain.service.IngredientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

  @Autowired private IngredientServiceImpl ingredientService;

  @Override
  public Ingredient convert(String id) {
    return ingredientService.getIngredientById(id);
  }
}
