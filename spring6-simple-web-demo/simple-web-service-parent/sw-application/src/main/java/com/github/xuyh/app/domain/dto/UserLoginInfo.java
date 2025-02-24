package com.github.xuyh.app.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户登录后的用户信息, 会序列化到Jwt的payload
 */
@NoArgsConstructor
@Data
public class UserLoginInfo {

  /**
   * 每次登录都生成一个, 用于有状态登录, 判断Token是否过期
   */
  private String sessionId;

  /**
   * 过期时间
   */
  private Long expiredTime;

  private Long userId;

  private String nickname;

  private String roleCode;
}
