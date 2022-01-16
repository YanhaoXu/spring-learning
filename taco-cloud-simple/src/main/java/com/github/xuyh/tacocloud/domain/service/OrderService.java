package com.github.xuyh.tacocloud.domain.service;

import com.github.xuyh.tacocloud.domain.entity.Order;
import com.github.xuyh.tacocloud.domain.entity.User;

import java.util.List;

public interface OrderService {
  Order save(Order order);

  List<Order> findByUser(User user);
}
