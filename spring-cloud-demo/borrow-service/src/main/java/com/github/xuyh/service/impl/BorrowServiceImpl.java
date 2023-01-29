package com.github.xuyh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.github.xuyh.entity.Book;
import com.github.xuyh.entity.Borrow;
import com.github.xuyh.entity.User;
import com.github.xuyh.entity.UserBorrowDetail;
import com.github.xuyh.mapper.BorrowMapper;
import com.github.xuyh.service.BorrowService;

@Service
public class BorrowServiceImpl implements BorrowService {

  @Autowired
  BorrowMapper borrowMapper;

  @Override
  public UserBorrowDetail getUserBorrowDetailByUid(int uid) {
    List<Borrow> borrowList = borrowMapper.getBorrowsByUid(uid);

    RestTemplate restTemplate = new RestTemplate();

    User user = restTemplate.getForObject("http://localhost:8083/user/" + uid, User.class);

    List<Book> bookList = borrowList.stream().map(
        it -> restTemplate.getForObject("http://localhost:8081/book/" + it.getBid(), Book.class))
        .toList();

    return new UserBorrowDetail(user, bookList);
  }
}
