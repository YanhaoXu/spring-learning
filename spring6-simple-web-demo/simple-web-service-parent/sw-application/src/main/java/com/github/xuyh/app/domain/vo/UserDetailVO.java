package com.github.xuyh.app.domain.vo;

import java.time.ZonedDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDetailVO {

  // userId
  private long id;
  private String username;
  private Boolean enable;
  private ZonedDateTime createTime;
  private ZonedDateTime updateTime;

  // 个人信息
  private ProfileVO profile;

  // 角色
  private List<RoleVO> roles;
  private RoleVO currentRole;
}
