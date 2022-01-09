package com.github.xuyh.tacocloud.repository;

import com.github.xuyh.tacocloud.web.model.Taco;
import org.springframework.data.repository.CrudRepository;

public interface JpaTacoRepository extends CrudRepository<Taco, Long> {}
