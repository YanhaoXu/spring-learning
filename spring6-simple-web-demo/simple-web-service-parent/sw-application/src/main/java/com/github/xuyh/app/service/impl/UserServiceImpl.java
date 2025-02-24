package com.github.xuyh.app.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.xuyh.app.domain.convertor.RoleConvertor;
import com.github.xuyh.app.domain.dto.UserLoginInfo;
import com.github.xuyh.app.domain.dto.UserQuery;
import com.github.xuyh.app.domain.entity.Role;
import com.github.xuyh.app.domain.entity.User;
import com.github.xuyh.app.domain.entity.UserRole;
import com.github.xuyh.app.domain.vo.RoleVO;
import com.github.xuyh.app.domain.vo.UserDetailVO;
import com.github.xuyh.app.domain.vo.UserItemVO;
import com.github.xuyh.app.mapper.UserMapper;
import com.github.xuyh.app.service.RoleService;
import com.github.xuyh.app.service.UserService;
import com.github.xuyh.app.uaa.core.util.LoginSessionTool;
import com.github.xuyh.common.domain.dto.PageResult;
import com.github.xuyh.common.util.PageUtils;
import com.github.xuyh.database.util.PageQueryUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private RoleService roleService;

  @Override
  public User getUserByUsername(String username) {
    return userMapper.getByUsername(username);
  }


  @Override
  public PageResult<UserItemVO> pageQueryUsers(UserQuery userQuery) {

    // 设置分页
    PageQueryUtils.startPage(userQuery);
    // 获取user
    List<UserItemVO> userPage = userMapper.pageQueryUser(userQuery);

    List<Long> userIdList = userPage.stream().map(UserItemVO::getId).toList();

    List<UserRole> userRoleList = roleService.queryUserRolesByUserIds(userIdList);

    Map<Long, List<RoleVO>> userRoleMap = Maps.newHashMap();
    List<Role> allRoleList = roleService.findAllRoles();

    Map<Long, Role> roleMap =
        allRoleList.stream().collect(Collectors.toMap(Role::getId, role -> role));

    for (UserRole userRole : userRoleList) {
      List<RoleVO> roleVOList =
          userRoleMap.computeIfAbsent(userRole.getUserId(), it -> Lists.newArrayList());
      Role role = roleMap.get(userRole.getRoleId());
      roleVOList.add(RoleConvertor.INSTANCE.toRoleVO(role));
    }
    userPage.forEach(it -> it.setRoles(userRoleMap.get(it.getId())));

    return PageUtils.result(userPage.size(), userPage);
  }

  @Override
  public UserDetailVO getCurrentUserDetail() {
    UserLoginInfo currentUser = LoginSessionTool.getCurrentUser();
    Long userId = currentUser.getUserId();

    UserDetailVO userDetailVO = new UserDetailVO();
    userDetailVO.setId(userId);
    // userDetailVO.setUsername(currentUser.get);
    return null;
  }
}
