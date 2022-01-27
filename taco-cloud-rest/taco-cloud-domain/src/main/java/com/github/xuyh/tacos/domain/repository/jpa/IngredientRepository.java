package com.github.xuyh.tacos.domain.repository.jpa;

import com.github.xuyh.tacos.domain.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
