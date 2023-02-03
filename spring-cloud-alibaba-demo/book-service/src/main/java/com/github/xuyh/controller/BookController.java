package com.github.xuyh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.xuyh.entity.Book;
import com.github.xuyh.service.BookService;

@RestController
@RequestMapping("/")
public class BookController {

  @Autowired
  private BookService bookService;

  @GetMapping("/book/{bid}")
  public Book findBookById(@PathVariable("bid") int bid) {
    return bookService.getBookById(bid);
  }

}
