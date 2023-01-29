package com.github.xuyh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.github.xuyh.entity.Borrow;

@Mapper
public interface BorrowMapper {
  @Select("select * from DB_BORROW where uid = #{uid}")
  List<Borrow> getBorrowsByUid(int uid);

  @Select("select * from DB_BORROW where bid = #{bid}")
  List<Borrow> getBorrowsByBid(int bid);

  @Select("select * from DB_BORROW where bid = #{bid} and uid = #{uid}")
  Borrow getBorrow(int uid, int bid);
}
