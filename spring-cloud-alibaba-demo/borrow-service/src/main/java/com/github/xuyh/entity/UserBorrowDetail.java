package com.github.xuyh.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserBorrowDetail {
  User user;
  List<Book> bookList;
}
