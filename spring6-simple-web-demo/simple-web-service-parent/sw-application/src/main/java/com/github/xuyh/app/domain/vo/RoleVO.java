package com.github.xuyh.app.domain.vo;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RoleVO {

  // roleId
  private Long id;

  @Length(max = 50)
  private String code;

  @Length(max = 50)
  private String name;

  private Boolean enable;
}
