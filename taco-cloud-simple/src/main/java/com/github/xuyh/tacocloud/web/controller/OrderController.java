package com.github.xuyh.tacocloud.web.controller;

import com.github.xuyh.tacocloud.domain.entity.Order;
import com.github.xuyh.tacocloud.domain.entity.User;
import com.github.xuyh.tacocloud.domain.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

  @Autowired private OrderService orderService;
  @Autowired private UserDetailsService userDetailsService;

  @PostMapping
  public String processOrder(
      @Valid Order order,
      Errors errors,
      SessionStatus sessionStatus,
      @AuthenticationPrincipal User user) {
    if (errors.hasErrors()) {
      return "orderForm";
    }
    order.setUser(user);

    orderService.save(order);
    sessionStatus.setComplete();

    log.info("Order submitted: {}", order);
    return "redirect:/";
  }

  @GetMapping("/current")
  public String orderForm(@AuthenticationPrincipal User user, @ModelAttribute Order order) {
    if (order.getDeliveryName() == null) {
      order.setDeliveryName(user.getFullname());
    }
    if (order.getDeliveryStreet() == null) {
      order.setDeliveryStreet(user.getStreet());
    }
    if (order.getDeliveryCity() == null) {
      order.setDeliveryCity(user.getCity());
    }
    if (order.getDeliveryState() == null) {
      order.setDeliveryState(user.getState());
    }
    if (order.getDeliveryZip() == null) {
      order.setDeliveryZip(user.getZip());
    }

    return "orderForm";
  }

  @GetMapping
  public String ordersForUser(@AuthenticationPrincipal User user, Model model) {

    model.addAttribute("orders", orderService.findByUser(user));
    return "orderList";
  }
}
