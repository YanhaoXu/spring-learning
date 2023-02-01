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

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BorrowServiceImpl implements BorrowService {

  @Resource
  private BorrowMapper borrowMapper;

  // @Autowired
  // private RestTemplate restTemplate;

  @Resource
  private UserClient userClient;

  @Resource
  private BookClient bookClient;

  @Override
  public UserBorrowDetail getUserBorrowDetailByUid(int uid) {
    log.info("正常执行方法：{}", "getUserBorrowDetailByUid");
    List<Borrow> borrowList = borrowMapper.getBorrowsByUid(uid);

    // User user = restTemplate.getForObject("http://userservice:8101/user/" + uid, User.class);
    // List<Book> bookList = borrowList.stream().map(
    // it -> restTemplate.getForObject("http://bookservice:8201/book/" + it.getBid(), Book.class))
    // .toList();

    User user = userClient.getUserById(uid);
    List<Book> bookList =
        borrowList.stream().map(it -> bookClient.findBookById(it.getBid())).toList();

    return new UserBorrowDetail(user, bookList);
  }
}
