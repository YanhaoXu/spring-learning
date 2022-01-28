package com.github.xuyh.tacos.domain.service;

import com.github.xuyh.tacos.domain.model.Taco;
import com.github.xuyh.tacos.domain.repository.jpa.TacoRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TacoServiceImpl implements TacoService {

  @Autowired TacoRepository tacoRepo;

  @Override
  public Taco save(Taco taco) {
    return tacoRepo.save(taco);
  }

  @Override
  public List<Taco> findAll() {
    return Lists.newArrayList(tacoRepo.findAll());
  }

  public List<Taco> recentTacos() {
    PageRequest page = PageRequest.of(0, 10, Sort.by("createdAt").descending());
    return Lists.newArrayList(tacoRepo.findAll(page).getContent());
  }
}
