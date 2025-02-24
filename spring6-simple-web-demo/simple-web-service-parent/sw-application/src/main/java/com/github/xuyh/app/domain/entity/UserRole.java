package com.github.xuyh.app.domain.entity;

import java.io.Serial;
import java.io.Serializable;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class UserRole implements Serializable {

  @Serial
  private static final long serialVersionUID = -4207158550920289791L;

  private Long userId;

  private Long roleId;
}
