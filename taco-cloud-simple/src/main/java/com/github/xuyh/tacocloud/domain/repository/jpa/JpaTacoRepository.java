package com.github.xuyh.tacocloud.domain.repository.jpa;

import com.github.xuyh.tacocloud.domain.entity.Taco;
import org.springframework.data.repository.CrudRepository;

public interface JpaTacoRepository extends CrudRepository<Taco, Long> {}
