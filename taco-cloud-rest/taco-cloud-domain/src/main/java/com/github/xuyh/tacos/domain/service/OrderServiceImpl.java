package com.github.xuyh.tacos.domain.service;

import com.github.xuyh.tacos.domain.model.Order;
import com.github.xuyh.tacos.domain.repository.jpa.OrderRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
  @Autowired private OrderRepository orderRepository;

  @Override
  public Order save(Order order) {
    return orderRepository.save(order);
  }

  @Override
  public List<Order> findAll() {
    return Lists.newArrayList(orderRepository.findAll());
  }

  @Override
  public Order findById(Long id) {
    return orderRepository.findById(id).get();
  }

  @Override
  public void deleteById(Long id) {
    orderRepository.deleteById(id);
  }
}
