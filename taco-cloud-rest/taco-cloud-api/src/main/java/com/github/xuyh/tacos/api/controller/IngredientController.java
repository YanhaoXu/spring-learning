package com.github.xuyh.tacos.api.controller;

import com.github.xuyh.tacos.domain.model.Ingredient;
import com.github.xuyh.tacos.domain.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/ingredients", produces = "application/json")
@CrossOrigin(origins = "*")
public class IngredientController {
  @Autowired private IngredientService ingredientService;

  @GetMapping
  public List<Ingredient> findAll() {
    return ingredientService.findAll();
  }
}
