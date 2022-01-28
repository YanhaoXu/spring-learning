package com.github.xuyh.tacos.api;

import com.github.xuyh.tacos.domain.model.Taco;
import com.github.xuyh.tacos.domain.service.TacoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/design", produces = "application/json")
public class DesignTacoController {

  @Autowired private TacoService tacoService;

  @GetMapping
  public List<Taco> recentTacos() {
    return tacoService.recentTacos();
  }
}
