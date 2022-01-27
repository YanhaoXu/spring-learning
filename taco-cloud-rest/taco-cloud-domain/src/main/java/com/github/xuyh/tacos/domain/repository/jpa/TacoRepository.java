package com.github.xuyh.tacos.domain.repository.jpa;

import com.github.xuyh.tacos.domain.model.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
