package com.github.xuyh.tacocloud.web.controller;

import com.github.xuyh.tacocloud.service.IngredientService;
import com.github.xuyh.tacocloud.web.model.Ingredient;
import com.github.xuyh.tacocloud.web.model.Ingredient.Type;
import com.github.xuyh.tacocloud.web.model.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

  @Autowired private IngredientService ingredientService;

  @GetMapping
  public String showDesignFrom(Model model) {
    List<Ingredient> ingredients = ingredientService.getAllIngredients();

    for (Type type : Type.values()) {
      model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
    }
    model.addAttribute("design", new Taco());

    return "design";
  }

  @PostMapping
  public String processDesign(@Valid @ModelAttribute("design") Taco design, Errors errors) {
    if (errors.hasErrors()) {
      return "design";
    }
    log.info("Process design:{}", design);
    return "redirect:/orders/current";
  }

  private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
    return ingredients.stream()
        .filter(ingredient -> ingredient.getType().equals(type))
        .collect(toList());
  }
}
