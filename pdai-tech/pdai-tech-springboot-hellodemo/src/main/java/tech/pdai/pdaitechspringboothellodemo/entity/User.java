package tech.pdai.pdaitechspringboothellodemo.entity;

import java.time.LocalDateTime;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tb_user")
public class User {
  @Id
  private Long userId;
  private String userName;

  private LocalDateTime createTime;
  private LocalDateTime updateTime;
}
