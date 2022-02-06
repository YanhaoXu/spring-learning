package com.github.xuyh.tacos.api.controller;

import com.github.xuyh.tacos.domain.model.Taco;
import com.github.xuyh.tacos.domain.service.TacoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/design", produces = "application/json")
public class DesignTacoController {

  @Autowired private TacoService tacoService;

  @GetMapping
  public List<Taco> recentTacos() {
    return tacoService.recentTacos();
  }

  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public Taco postTaco(@RequestBody Taco taco) {
    return tacoService.save(taco);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
    Taco taco = tacoService.findById(id);
    if (taco != null) {
      return new ResponseEntity<>(taco, HttpStatus.OK);
    }
    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
  }
}
