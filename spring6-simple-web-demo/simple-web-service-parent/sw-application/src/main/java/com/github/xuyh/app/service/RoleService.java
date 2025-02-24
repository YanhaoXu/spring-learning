package com.github.xuyh.app.service;

import java.util.List;

import com.github.xuyh.app.domain.entity.Role;
import com.github.xuyh.app.domain.entity.UserRole;

public interface RoleService {

  List<Role> getRolesByUserId(long userId);

  Role getRoleByCode(String roleCode);

  List<UserRole> queryUserRolesByUserIds(List<Long> userIds);

  List<Role> findAllRoles();
}
