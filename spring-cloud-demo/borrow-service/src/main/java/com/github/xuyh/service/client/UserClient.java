package com.github.xuyh.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.github.xuyh.entity.User;

@FeignClient(value = "userservice", fallback = UserFallbackClient.class)
public interface UserClient {

  @GetMapping("/user/{uid}")
  User getUserById(@PathVariable("uid") int uid);
}
