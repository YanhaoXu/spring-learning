package com.github.xuyh.tacocloud.domain.repository.jpa;

import com.github.xuyh.tacocloud.domain.entity.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface JpaIngredientRepository extends CrudRepository<Ingredient, String> {}
