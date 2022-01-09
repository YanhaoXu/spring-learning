package com.github.xuyh.tacocloud.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.xuyh.tacocloud.web.model.Order;
import com.github.xuyh.tacocloud.web.model.Taco;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class JdbcOrderRepositoryImpl implements JdbcOrderRepository {
  private SimpleJdbcInsert orderInserter;
  private SimpleJdbcInsert orderTacoInserter;
  private ObjectMapper objectMapper;

  @Autowired
  public JdbcOrderRepositoryImpl(JdbcTemplate jdbc) {
    this.orderInserter =
        new SimpleJdbcInsert(jdbc).withTableName("Taco_Order").usingGeneratedKeyColumns("id");

    this.orderTacoInserter = new SimpleJdbcInsert(jdbc).withTableName("Taco_Order_Tacos");

    this.objectMapper = new ObjectMapper();
  }

  @Override
  public Order save(Order order) {

    order.setPlacedAt(new Date());
    long orderId = saveOrderDetails(order);
    order.setId(orderId);

    order.getTacos().forEach(taco -> saveTacoToOrder(taco, orderId));

    return order;
  }

  private long saveOrderDetails(Order order) {
    Map<String, Object> values = objectMapper.convertValue(order, Map.class);

    values.put("placedAt", order.getPlacedAt());

    return orderInserter.executeAndReturnKey(values).longValue();
  }

  private void saveTacoToOrder(Taco taco, long orderId) {
    HashMap<String, Object> values = Maps.newHashMap();
    values.put("tacoOrder", orderId);
    values.put("taco", taco.getId());
    orderTacoInserter.execute(values);
  }
}
