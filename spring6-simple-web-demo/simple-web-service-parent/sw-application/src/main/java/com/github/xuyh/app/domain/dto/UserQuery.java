package com.github.xuyh.app.domain.dto;

import com.github.xuyh.common.domain.dto.PageQueryParam;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class UserQuery extends PageQueryParam {
  private String username;
  private Integer gender;
  private Boolean enable;
}
