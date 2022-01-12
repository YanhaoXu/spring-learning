package com.github.xuyh.tacocloud.domain.repository;

import com.github.xuyh.tacocloud.domain.model.Order;

public interface JdbcOrderRepository {
  Order save(Order order);
}
