package com.github.xuyh.app.mapper;

import java.util.List;

import com.github.xuyh.app.domain.entity.Coffee;

public interface CoffeeMapper {
  List<Coffee> findAll();
}
