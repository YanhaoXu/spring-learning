package com.github.xuyh.tacocloud.web.controller;

import com.github.xuyh.tacocloud.domain.entity.Ingredient;
import com.github.xuyh.tacocloud.domain.entity.Ingredient.Type;
import com.github.xuyh.tacocloud.domain.entity.Order;
import com.github.xuyh.tacocloud.domain.entity.Taco;
import com.github.xuyh.tacocloud.domain.entity.User;
import com.github.xuyh.tacocloud.domain.service.IngredientService;
import com.github.xuyh.tacocloud.domain.service.TacoService;
import com.github.xuyh.tacocloud.domain.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

  @Autowired private IngredientService ingredientService;

  @Autowired private TacoService tacoService;

  @Autowired private UserService userService;

  @ModelAttribute(name = "taco")
  public Taco taco() {
    return new Taco();
  }

  @ModelAttribute(name = "order")
  public Order order() {
    return new Order();
  }

  @GetMapping
  public String showDesignFrom(Model model, Principal principal) {
    List<Ingredient> ingredients = ingredientService.getAllIngredients();

    Arrays.stream(Type.values())
        .forEach(
            type ->
                model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type)));

    User user = userService.findByUsername(principal.getName());

    model.addAttribute("user", user);

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
