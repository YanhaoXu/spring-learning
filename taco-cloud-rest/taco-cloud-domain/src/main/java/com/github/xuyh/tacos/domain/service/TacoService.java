package com.github.xuyh.tacos.domain.service;

import com.github.xuyh.tacos.domain.model.Taco;

import java.util.List;

public interface TacoService {
  Taco save(Taco taco);

  List<Taco> findAll();

  List<Taco> recentTacos();
}
