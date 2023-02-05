package com.github.xuyh.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.xuyh.entity.Book;
import com.github.xuyh.service.BookService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/")
@Slf4j
public class BookController {

  @Resource
  private BookService bookService;

  @GetMapping("/book/{bid}")
  public Book findBookById(@PathVariable("bid") int bid) {
    log.info("调用图书服务");
    return bookService.getBookById(bid);
  }

}
