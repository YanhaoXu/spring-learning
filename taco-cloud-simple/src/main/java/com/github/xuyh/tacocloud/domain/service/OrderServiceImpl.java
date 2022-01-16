package com.github.xuyh.tacocloud.domain.service;

import com.github.xuyh.tacocloud.domain.entity.Order;
import com.github.xuyh.tacocloud.domain.repository.jpa.JpaOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
  //  @Autowired private JdbcOrderRepository orderRepo;
  @Autowired private JpaOrderRepository orderRepo;

  public Order save(Order order) {
    return orderRepo.save(order);
  }
}
