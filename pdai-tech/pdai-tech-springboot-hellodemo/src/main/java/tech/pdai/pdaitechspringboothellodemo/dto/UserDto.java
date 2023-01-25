package tech.pdai.pdaitechspringboothellodemo.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class UserDto implements Serializable {

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
