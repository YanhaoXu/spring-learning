package com.github.xuyh.tacocloud.domain.repository.jpa;

import com.github.xuyh.tacocloud.domain.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface JpaOrderRepository extends CrudRepository<Order, Long> {}
