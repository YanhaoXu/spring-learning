package com.github.xuyh.database.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BaseEntity implements Serializable {

  @Serial
  private static final long serialVersionUID = -7920135239189558197L;

  private Long id;

  private LocalDateTime createTime;

  private LocalDateTime updateTime;

}
