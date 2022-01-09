package com.github.xuyh.tacocloud.repository;

import com.github.xuyh.tacocloud.web.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface JpaOrderRepository extends CrudRepository<Order, Long> {}
