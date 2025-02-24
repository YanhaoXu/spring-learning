package com.github.xuyh.app.domain.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProfileVO {

  private Long id;

  private Integer gender;

  private String avatar;

  private String address;

  private String email;

  private Long userId;

  private String nickName;
}
