package com.github.xuyh.tacocloud.web.controller;

import com.github.xuyh.tacocloud.domain.repository.jpa.JpaUserRepository;
import com.github.xuyh.tacocloud.web.form.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {
  @Autowired private JpaUserRepository userRepo;
  @Autowired private PasswordEncoder passwordEncoder;

  @GetMapping
  public String registerForm() {
    return "registration";
  }

  @PostMapping
  public String processRegistration(RegistrationForm form) {
    userRepo.save(form.toUser(passwordEncoder));
    return "redirect:/login";
  }
}
