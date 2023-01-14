package tech.pdai.pdaitechspringboothellodemo.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_user")
public class User {

  @Id
  private Long userId;
  private String userName;

  private String email;
  private String cardNo;
  private String nickName;
  private int sex;
  private int age;

  private LocalDateTime createTime;
  private LocalDateTime updateTime;
}
