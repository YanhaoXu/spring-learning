package com.github.xuyh.tacos.api;

import com.github.xuyh.tacos.domain.model.Taco;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

import java.util.Date;
import java.util.List;

public class TacoResource extends ResourceSupport {

  private static final IngredientResourceAssembler ingredientResourceAssembler =
      new IngredientResourceAssembler();

  @Getter private final String name;

  @Getter private final Date createdAt;

  @Getter private final List<IngredientResource> ingredients;

  public TacoResource(Taco taco) {
    this.name = taco.getName();
    this.createdAt = taco.getCreatedAt();
    this.ingredients = ingredientResourceAssembler.toResources(taco.getIngredients());
  }
}
