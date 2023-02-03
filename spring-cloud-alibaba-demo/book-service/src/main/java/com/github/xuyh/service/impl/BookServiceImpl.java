package com.github.xuyh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.xuyh.entity.Book;
import com.github.xuyh.mapper.BookMapper;
import com.github.xuyh.service.BookService;

@Service
public class BookServiceImpl implements BookService {
  @Autowired
  private BookMapper bookMapper;

  @Override
  public Book getBookById(int bid) {
    return bookMapper.getBookById(bid);
  }
}
