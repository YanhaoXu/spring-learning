package com.github.xuyh.app.service.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.xuyh.app.domain.entity.Role;
import com.github.xuyh.app.domain.entity.UserRole;
import com.github.xuyh.app.mapper.RoleMapper;
import com.github.xuyh.app.service.RoleService;
import com.google.common.collect.Lists;

@Service
public class RoleServiceImpl implements RoleService {

  @Autowired
  private RoleMapper roleMapper;

  @Override
  public List<Role> getRolesByUserId(long userId) {
    return roleMapper.getRolesByUserId(userId);
  }

  @Override
  public Role getRoleByCode(String roleCode) {
    return roleMapper.getByCode(roleCode);
  }

  @Override
  public List<UserRole> queryUserRolesByUserIds(List<Long> userIds) {
    if (CollectionUtils.isEmpty(userIds)) {
      return Lists.newArrayList();
    }
    return roleMapper.queryUserRolesByUserIds(userIds);
  }

  @Override
  public List<Role> findAllRoles() {
    return roleMapper.selectAll();
  }
}
