package com.github.xuyh.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.github.xuyh.app.domain.dto.UserQuery;
import com.github.xuyh.app.domain.entity.User;
import com.github.xuyh.app.domain.vo.UserItemVO;

@Mapper
public interface UserMapper {

  @Select("select id, create_time, update_time, username, password, enable from simple_web.t_user")
  User selectById(String userId);

  @Select("select id, create_time, update_time, username, password, enable from simple_web.t_user where username=#{username}")
  User getByUsername(String username);

  List<UserItemVO> pageQueryUser(@Param("param") UserQuery userQuery);
}
