package com.github.xuyh.app.service;

import com.github.xuyh.app.domain.dto.UserQuery;
import com.github.xuyh.app.domain.entity.User;
import com.github.xuyh.app.domain.vo.UserDetailVO;
import com.github.xuyh.app.domain.vo.UserItemVO;
import com.github.xuyh.common.domain.dto.PageResult;

public interface UserService {

  User getUserByUsername(String username);

  PageResult<UserItemVO> pageQueryUsers(UserQuery userQuery);

  UserDetailVO getCurrentUserDetail();
}
