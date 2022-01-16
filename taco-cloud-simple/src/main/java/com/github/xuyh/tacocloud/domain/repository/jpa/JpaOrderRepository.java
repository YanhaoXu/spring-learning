package com.github.xuyh.tacocloud.domain.repository.jpa;

import com.github.xuyh.tacocloud.domain.entity.Order;
import com.github.xuyh.tacocloud.domain.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JpaOrderRepository extends CrudRepository<Order, Long> {
  List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
