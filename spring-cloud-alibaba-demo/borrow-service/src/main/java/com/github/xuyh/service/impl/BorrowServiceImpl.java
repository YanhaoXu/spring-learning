package com.github.xuyh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.xuyh.entity.Book;
import com.github.xuyh.entity.Borrow;
import com.github.xuyh.entity.User;
import com.github.xuyh.entity.UserBorrowDetail;
import com.github.xuyh.mapper.BorrowMapper;
import com.github.xuyh.service.BorrowService;
import com.github.xuyh.service.client.BookClient;
import com.github.xuyh.service.client.UserClient;

@Service
public class BorrowServiceImpl implements BorrowService {

  @Resource
  private BorrowMapper borrowMapper;

  @Resource
  private UserClient userClient;

  @Resource
  private BookClient bookClient;

  @Override
  public UserBorrowDetail getUserBorrowDetailByUid(int uid) {
    List<Borrow> borrowList = borrowMapper.getBorrowsByUid(uid);

    User user = userClient.getUserById(uid);

    List<Book> bookList =
        borrowList.stream().map(it -> bookClient.findBookById(it.getBid())).toList();

    return new UserBorrowDetail(user, bookList);
  }
}
