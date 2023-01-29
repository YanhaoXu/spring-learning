package com.github.xuyh.service;

import com.github.xuyh.entity.UserBorrowDetail;

public interface BorrowService {

  UserBorrowDetail getUserBorrowDetailByUid(int uid);
}
