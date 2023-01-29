package com.github.xuyh.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.github.xuyh.entity.Book;

@Mapper
public interface BookMapper {
  @Select("select * from DB_BOOK where bid = #{bid}")
  Book getBookById(int bid);
}
