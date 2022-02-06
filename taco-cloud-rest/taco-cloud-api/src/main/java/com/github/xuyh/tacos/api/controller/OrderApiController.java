package com.github.xuyh.tacos.api.controller;

import com.github.xuyh.tacos.domain.model.Order;
import com.github.xuyh.tacos.domain.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/orders", produces = "application/json")
public class OrderApiController {

  @Autowired private OrderService orderService;

  @GetMapping(produces = "application/json")
  public List<Order> allOrders() {
    return orderService.findAll();
  }

  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public Order postOrder(@RequestBody Order order) {
    return orderService.save(order);
  }

  @PutMapping(path = "/{orderId}", consumes = "application/json")
  public Order putOrder(@RequestBody Order order) {
    return orderService.save(order);
  }

  @PatchMapping(path = "/{orderId}", consumes = "application/json")
  public Order patchOrderOrder(
      @PathVariable("orderId") Long orderId, @RequestBody Order patchOrder) {
    Order order = orderService.findById(orderId);

    if (patchOrder.getDeliveryName() != null) {
      order.setDeliveryName(patchOrder.getDeliveryName());
    }
    if (patchOrder.getDeliveryStreet() != null) {
      order.setDeliveryStreet(patchOrder.getDeliveryStreet());
    }
    if (patchOrder.getDeliveryCity() != null) {
      order.setDeliveryCity(patchOrder.getDeliveryCity());
    }
    if (patchOrder.getDeliveryState() != null) {
      order.setDeliveryState(patchOrder.getDeliveryState());
    }
    if (patchOrder.getDeliveryZip() != null) {
      order.setDeliveryZip(patchOrder.getDeliveryState());
    }
    if (patchOrder.getCcNumber() != null) {
      order.setCcNumber(patchOrder.getCcNumber());
    }
    if (patchOrder.getCcExpiration() != null) {
      order.setCcExpiration(patchOrder.getCcExpiration());
    }
    if (patchOrder.getCcCVV() != null) {
      order.setCcCVV(patchOrder.getCcCVV());
    }
    return orderService.save(order);
  }

  @DeleteMapping("/{orderId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteOrder(@PathVariable("orderId") Long orderId) {
    orderService.deleteById(orderId);
  }
}
