package com.github.xuyh.app.domain.vo;

import java.time.ZonedDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserItemVO {

  // userId
  private Long id;

  private String username;
  private Boolean enable;
  private ZonedDateTime createTime;
  private ZonedDateTime updateTime;

  // 个人信息
  private Integer gender;
  private String avatar;
  private String address;
  private String email;
  private String nickName;

  private List<RoleVO> roles;
}
