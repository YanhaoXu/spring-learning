package com.github.xuyh.service.client;

import org.springframework.stereotype.Component;

import com.github.xuyh.entity.Book;

@Component
public class BookFallbackClient implements BookClient {

  @Override
  public Book findBookById(int bid) {
    return new Book();
  }
}
