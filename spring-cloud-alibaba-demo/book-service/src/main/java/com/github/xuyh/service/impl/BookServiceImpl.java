package com.github.xuyh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.xuyh.entity.Book;
import com.github.xuyh.mapper.BookMapper;
import com.github.xuyh.service.BookService;

@Service
public class BookServiceImpl implements BookService {
  @Resource
  private BookMapper bookMapper;

  @Override
  public Book getBookById(int bid) {
    return bookMapper.getBookById(bid);
  }
}
