package com.github.xuyh.app.service.impl;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.xuyh.app.service.JwtService;
import com.github.xuyh.common.util.JsonUtils;
import com.github.xuyh.web.exception.ExceptionUtils;
import com.google.common.collect.Maps;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtServiceImpl implements JwtService, InitializingBean {

  @Value("${simple-web.login.jwt.public-key}")
  private String publicKeyBase64;

  @Value("${simple-web.login.jwt.private-key}")
  private String privateKeyBase64;

  private PrivateKey privateKey;

  private JwtParser jwtParser;

  /**
   * 获取私钥, 用于生成Jwt
   */
  public PrivateKey getPrivateKey() {
    try {
      // 利用JDK自带的工具生成秘钥
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      PKCS8EncodedKeySpec keySpec =
          new PKCS8EncodedKeySpec(Decoders.BASE64.decode(privateKeyBase64));
      return keyFactory.generatePrivate(keySpec);
    } catch (Exception e) {
      // 获取Jwt私钥失败
      log.error(e.getMessage(), e);
      ExceptionUtils.throwException("获取Jwt私钥失败");
      return null;
    }
  }

  /**
   * 公钥, 用于解析Jwt
   */
  public JwtParser getJwtParser() {
    try {
      X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Decoders.BASE64.decode(publicKeyBase64));
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      PublicKey publicKey = keyFactory.generatePublic(keySpec);
      return Jwts.parserBuilder().setSigningKey(publicKey).build();
    } catch (Exception e) {
      // 获取Jwt公钥失败
      log.error(e.getMessage(), e);
      ExceptionUtils.throwException("获取Jwt公钥失败");
      return null;
    }
  }

  @Override
  public String createJwt(Object jwtPayload, long expiredAt) {
    // 添加工程JWT的参数
    Map<String, Object> headMap = Maps.newHashMap();
    headMap.put("alg", SignatureAlgorithm.RS256.getValue());
    headMap.put("typ", "JWT");

    Map<String, Object> body =
        JsonUtils.parse(JsonUtils.stringify(jwtPayload), new TypeReference<>() {});
    String jwt = Jwts.builder().setHeader(headMap).setClaims(body)
        .setExpiration(new Date(expiredAt)).signWith(privateKey).compact();
    log.info("createJwt:{}", jwt);
    return jwt;
  }

  @Override
  public <T> T verifyJwt(String jwt, Class<T> jwtPayloadClass) {
    if (StringUtils.isEmpty(jwt)) {
      return null;
    }
    Jws<Claims> jws = this.jwtParser.parseClaimsJws(jwt);
    Claims jwtPayload = jws.getBody();
    if (Objects.isNull(jwtPayload)) {
      return null;
    }
    return JsonUtils.convert(jwtPayload, jwtPayloadClass);
  }

  @Override
  public void afterPropertiesSet() {
    this.privateKey = getPrivateKey();
    this.jwtParser = getJwtParser();
  }
}
