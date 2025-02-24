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
public class Role extends BaseEntity {

  @Serial
  private static final long serialVersionUID = 7439756009482512201L;

  @Length(max = 50)
  private String code;

  @Length(max = 50)
  private String name;

  private Boolean enable;
}
