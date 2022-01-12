package com.github.xuyh.tacocloud.domain.repository;

import com.github.xuyh.tacocloud.domain.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface JpaIngredientRepository extends CrudRepository<Ingredient, String> {}
