package com.github.xuyh.tacos.domain.repository.jpa;

import com.github.xuyh.tacos.domain.model.Taco;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {
}
