package com.github.xuyh.tacos.api.resource;

import com.github.xuyh.tacos.api.controller.IngredientController;
import com.github.xuyh.tacos.domain.model.Ingredient;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class IngredientResourceAssembler
    extends ResourceAssemblerSupport<Ingredient, IngredientResource> {
  public IngredientResourceAssembler() {
    super(IngredientController.class, IngredientResource.class);
  }

  @Override
  public IngredientResource toResource(Ingredient ingredient) {
    return createResourceWithId(ingredient.getId(), ingredient);
  }

  @Override
  protected IngredientResource instantiateResource(Ingredient ingredient) {
    return new IngredientResource(ingredient);
  }
}
