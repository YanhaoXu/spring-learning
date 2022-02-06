package com.github.xuyh.tacos.domain.service;

import com.github.xuyh.tacos.domain.model.Order;

import java.util.List;

public interface OrderService {

  Order save(Order order);

  List<Order> findAll();

  Order findById(Long id);

  void deleteById(Long id);
}
