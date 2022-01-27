package com.github.xuyh.tacos.domain.repository.jpa;

import com.github.xuyh.tacos.domain.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
