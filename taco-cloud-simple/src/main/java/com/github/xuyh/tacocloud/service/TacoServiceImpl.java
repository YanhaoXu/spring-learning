package com.github.xuyh.tacocloud.service;

import com.github.xuyh.tacocloud.repository.JpaTacoRepository;
import com.github.xuyh.tacocloud.web.model.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TacoServiceImpl implements TacoService {
  //  @Autowired private JdbcTacoRepositoryImpl jdbcTacoRepository;
  @Autowired private JpaTacoRepository tacoRepo;

  public Taco save(Taco taco) {
    return tacoRepo.save(taco);
  }
}
