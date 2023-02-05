package com.github.xuyh.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.github.xuyh.entity.Book;

@FeignClient("bookservice")
public interface BookClient {

  @GetMapping("/book/{bid}")
  Book findBookById(@PathVariable("bid") int bid);
}
