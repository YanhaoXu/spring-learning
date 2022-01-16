package com.github.xuyh.tacocloud.domain.service;

import com.github.xuyh.tacocloud.domain.entity.Order;
import com.github.xuyh.tacocloud.domain.entity.User;
import com.github.xuyh.tacocloud.domain.repository.jpa.JpaOrderRepository;
import com.github.xuyh.tacocloud.web.OrderProps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
  //  @Autowired private JdbcOrderRepository orderRepo;

  @Autowired private JpaOrderRepository orderRepo;
  @Autowired private OrderProps props;

  public Order save(Order order) {
    return orderRepo.save(order);
  }

  @Override
  public List<Order> findByUser(User user) {
    Pageable pageable = PageRequest.of(0, props.getPageSize());
    return orderRepo.findByUserOrderByPlacedAtDesc(user, pageable);
  }
}
