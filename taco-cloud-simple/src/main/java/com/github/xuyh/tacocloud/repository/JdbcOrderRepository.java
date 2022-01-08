package com.github.xuyh.tacocloud.repository;

import com.github.xuyh.tacocloud.web.model.Order;

public interface JdbcOrderRepository {
  Order save(Order order);
}
