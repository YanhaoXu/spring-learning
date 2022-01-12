package com.github.xuyh.tacocloud.domain.repository;

import com.github.xuyh.tacocloud.domain.model.Taco;
import org.springframework.data.repository.CrudRepository;

public interface JpaTacoRepository extends CrudRepository<Taco, Long> {}
