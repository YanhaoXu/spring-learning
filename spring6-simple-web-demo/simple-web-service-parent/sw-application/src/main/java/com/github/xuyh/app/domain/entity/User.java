package com.github.xuyh.app.domain.entity;

import java.io.Serial;

import org.hibernate.validator.constraints.Length;

import com.github.xuyh.database.entity.BaseEntity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class User extends BaseEntity {

  @Serial
  private static final long serialVersionUID = -4392683072145283624L;

  @Length(max = 50)
  private String username;

  @Length(max = 255)
  private String password;

  private Boolean enable;
}
