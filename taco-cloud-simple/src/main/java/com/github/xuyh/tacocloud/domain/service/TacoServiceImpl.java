package com.github.xuyh.tacocloud.domain.service;

import com.github.xuyh.tacocloud.domain.model.Taco;
import com.github.xuyh.tacocloud.domain.repository.JpaTacoRepository;
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
