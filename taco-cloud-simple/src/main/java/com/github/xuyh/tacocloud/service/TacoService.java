package com.github.xuyh.tacocloud.service;

import com.github.xuyh.tacocloud.repository.JdbcTacoRepositoryImpl;
import com.github.xuyh.tacocloud.web.model.Taco;
import org.springframework.beans.factory.annotation.Autowired;

public class TacoService {
  @Autowired private JdbcTacoRepositoryImpl jdbcTacoRepository;

  public Taco save(Taco taco) {
    return jdbcTacoRepository.save(taco);
  }
}
