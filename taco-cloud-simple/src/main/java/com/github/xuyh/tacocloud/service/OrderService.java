package com.github.xuyh.tacocloud.service;

import com.github.xuyh.tacocloud.repository.JdbcOrderRepository;
import com.github.xuyh.tacocloud.web.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
  @Autowired private JdbcOrderRepository jdbcOrderRepository;

  public Order save(Order order) {
    return jdbcOrderRepository.save(order);
  }
}
