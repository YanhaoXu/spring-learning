package com.github.xuyh.app.domain.entity;

import java.io.Serial;

import com.github.xuyh.database.entity.BaseEntity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Coffee extends BaseEntity {

  @Serial
  private static final long serialVersionUID = -2235273019343941399L;

  private String name;

  private Long price;
}
