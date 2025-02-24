package com.github.xuyh.app;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.FilterChainProxy;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@MapperScan({"com.github.xuyh.app.mapper", "com.github.xuyh.app.uaa.dao.mapper"})
@Slf4j
public class SimpleWebApplication {
  public static void main(String[] args) {
    SpringApplication.run(SimpleWebApplication.class, args);
  }

  /**
   * TODO 使用自定义 Bean 打印所有过滤器.
   */
  @Bean
  public ApplicationRunner printSecurityFilters(FilterChainProxy filterChainProxy) {
    return args -> filterChainProxy.getFilterChains().forEach(chain -> {
      log.info("Filter chain:");
      chain.getFilters().forEach(filter -> log.info(" - {}", filter.getClass().getSimpleName()));
    });
  }
}
