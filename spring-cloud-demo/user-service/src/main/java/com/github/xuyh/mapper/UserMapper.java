package com.github.xuyh.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.github.xuyh.entity.User;

@Mapper
public interface UserMapper {

  @Select("select * from DB_USER where uid = #{uid}")
  User getUserById(int uid);
}
