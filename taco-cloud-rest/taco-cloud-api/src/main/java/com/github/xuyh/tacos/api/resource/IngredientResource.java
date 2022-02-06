package com.github.xuyh.tacos.api.resource;

import com.github.xuyh.tacos.domain.model.Ingredient;
import com.github.xuyh.tacos.domain.model.Ingredient.Type;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

public class IngredientResource extends ResourceSupport {
  @Getter private String name;

  @Getter private Type type;

  public IngredientResource(Ingredient ingredient) {
    this.name = ingredient.getName();
    this.type = ingredient.getType();
  }
}
