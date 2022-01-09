package com.github.xuyh.tacocloud.repository;

import com.github.xuyh.tacocloud.web.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface JpaIngredientRepository extends CrudRepository<Ingredient, String> {}
