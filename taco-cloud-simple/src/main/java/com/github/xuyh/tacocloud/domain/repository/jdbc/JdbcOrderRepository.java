package com.github.xuyh.tacocloud.domain.repository.jdbc;

import com.github.xuyh.tacocloud.domain.entity.Order;

public interface JdbcOrderRepository {
  Order save(Order order);
}
