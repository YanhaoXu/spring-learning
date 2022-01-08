package com.github.xuyh.tacocloud.controller;

import com.github.xuyh.tacocloud.pojo.Ingredient;
import com.github.xuyh.tacocloud.pojo.Ingredient.Type;
import com.github.xuyh.tacocloud.pojo.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {
  @GetMapping
  public String showDesignFrom(Model model) {
    List<Ingredient> ingredients =
        Arrays.asList(
            new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
            new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
            new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
            new Ingredient("CARN", "Carnitas", Type.PROTEIN),
            new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
            new Ingredient("LETC", "Lettuce", Type.VEGGIES),
            new Ingredient("CHED", "Cheddar", Type.CHEESE),
            new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
            new Ingredient("SLSA", "Salsa", Type.SAUCE),
            new Ingredient("SRCR", "Sour Cream", Type.SAUCE));

    for (Type type : Type.values()) {
      model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
    }
    model.addAttribute("design", new Taco());

    return "design";
  }


  @PostMapping
  public String processDesign(Taco design) {
    log.info("Process design:{}", design);
    return "redirect:/orders/current";
  }

  private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
    return ingredients.stream()
        .filter(ingredient -> ingredient.getType().equals(type))
        .collect(toList());
  }
}
