package com.github.xuyh.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.github.xuyh.app.domain.entity.Role;
import com.github.xuyh.app.domain.entity.UserRole;

@Mapper
public interface RoleMapper {

  @Select("select * from t_role where id in (select role_id"
      + " from t_user_role where user_id = #{userId}) order by id")
  List<Role> getRolesByUserId(long userId);

  @Select("select id, create_time, update_time, code, name, enable from t_role where code=#{roleCode}")
  Role getByCode(String roleCode);

  List<UserRole> queryUserRolesByUserIds(List<Long> userIds);

  @Select("select * from t_role")
  List<Role> selectAll();
}
