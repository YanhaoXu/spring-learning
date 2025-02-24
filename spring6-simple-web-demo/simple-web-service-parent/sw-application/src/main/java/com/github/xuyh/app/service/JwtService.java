package com.github.xuyh.app.service;

/**
 * The interface Jwt service.
 */
public interface JwtService {
  /**
   * 生成jwt.
   *
   * @param jwtPayload the jwt payload
   * @param expiredAt the expired at
   * @return the string
   */
  String createJwt(Object jwtPayload, long expiredAt);

  /**
   * 校验jwt签名, 并返回jwt的payload.
   *
   * @param <T> the type parameter
   * @param jwt the jwt
   * @param jwtPayloadClass the jwt payload class
   * @return the t
   */
  <T> T verifyJwt(String jwt, Class<T> jwtPayloadClass);
}

