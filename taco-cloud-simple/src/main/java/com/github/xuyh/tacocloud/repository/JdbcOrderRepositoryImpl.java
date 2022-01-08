package com.github.xuyh.tacocloud.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.xuyh.tacocloud.web.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

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

    return null;
  }
}
