package com.github.xuyh.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.xuyh.web.model.ApiResult;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/hello-redis")
@Slf4j
public class HelloRedisController {

  @Autowired
  private RedisTemplate<String, Object> redisTemplate;
  @Autowired
  private ObjectMapper objectMapper;

  @GetMapping("/get")
  public ApiResult<Map<String, String>> getFromRedis(@RequestParam String key) {
    log.info("getFromRedis-key:{}", key);
    Map<String, String> value = (Map<String, String>) redisTemplate.opsForValue().get(key);
    log.info("getFromRedis-value:{}", value);
    return ApiResult.success(value);
  }

  @PostMapping("/set")
  public ApiResult<String> setToRedis(@RequestParam String key, @RequestParam String value) {
    Map<String, String> map = new HashMap<>();
    map.put("value", value);
    redisTemplate.opsForValue().set(key, map);
    return ApiResult.success("Data saved successfully");
  }
}
