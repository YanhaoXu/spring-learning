package com.github.xuyh.tacocloud.web.controller;

import com.github.xuyh.tacocloud.domain.model.Ingredient;
import com.github.xuyh.tacocloud.domain.model.Ingredient.Type;
import com.github.xuyh.tacocloud.domain.model.Order;
import com.github.xuyh.tacocloud.domain.model.Taco;
import com.github.xuyh.tacocloud.domain.service.IngredientService;
import com.github.xuyh.tacocloud.domain.service.TacoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

  @Autowired private IngredientService ingredientService;

  @Autowired private TacoService tacoService;

  @ModelAttribute(name = "taco")
  public Taco taco() {
    return new Taco();
  }

  @ModelAttribute(name = "order")
  public Order order() {
    return new Order();
  }

  @GetMapping
  public String showDesignFrom(Model model) {
    List<Ingredient> ingredients = ingredientService.getAllIngredients();

    for (Type type : Type.values()) {
      model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
    }

    return "design";
  }

  @PostMapping
  public String processDesign(
      @Valid @ModelAttribute("design") Taco design, Errors errors, @ModelAttribute Order order) {
    if (errors.hasErrors()) {
      return "design";
    }
    Taco savedTaco = tacoService.save(design);
    order.addDesign(savedTaco);

    log.info("Process design:{}", design);
    return "redirect:/orders/current";
  }

  private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
    return ingredients.stream()
        .filter(ingredient -> ingredient.getType().equals(type))
        .collect(toList());
  }
}
