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
  private BorrowMapper borrowMapper;

  @Autowired
  private RestTemplate restTemplate;

  @Override
  public UserBorrowDetail getUserBorrowDetailByUid(int uid) {
    List<Borrow> borrowList = borrowMapper.getBorrowsByUid(uid);

    User user = restTemplate.getForObject("http://userservice:8101/user/" + uid, User.class);

    List<Book> bookList = borrowList.stream().map(
        it -> restTemplate.getForObject("http://bookservice:8201/book/" + it.getBid(), Book.class))
        .toList();

    return new UserBorrowDetail(user, bookList);
  }
}
