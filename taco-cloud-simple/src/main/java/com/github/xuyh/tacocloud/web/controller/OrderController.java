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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

  @Autowired private OrderService orderService;
  @Autowired private UserDetailsService userDetailsService;

  @GetMapping("/current")
  public String orderForm() {
    return "orderForm";
  }

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

  @GetMapping
  public String ordersForUser(@AuthenticationPrincipal User user, Model model) {

    model.addAttribute("orders", orderService.findByUser(user));
    return "orderList";
  }
}
