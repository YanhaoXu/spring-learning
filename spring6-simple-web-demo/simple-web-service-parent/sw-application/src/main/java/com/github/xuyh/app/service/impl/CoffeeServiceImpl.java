package com.github.xuyh.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.xuyh.app.domain.entity.Coffee;
import com.github.xuyh.app.mapper.CoffeeMapper;
import com.github.xuyh.app.service.CoffeeService;

@Service
public class CoffeeServiceImpl implements CoffeeService {

  @Autowired
  private CoffeeMapper coffeeMapper;

  @Override
  public List<Coffee> getAllCoffee() {
    return coffeeMapper.findAll();
  }
}
