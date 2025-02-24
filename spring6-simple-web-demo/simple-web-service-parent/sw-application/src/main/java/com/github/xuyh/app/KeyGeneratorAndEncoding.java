package com.github.xuyh.app;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class KeyGeneratorAndEncoding {

  public static void main(String[] args) throws Exception {
    // 生成 RSA 密钥对
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
    keyPairGenerator.initialize(2048); // 密钥长度 2048 位
    KeyPair keyPair = keyPairGenerator.generateKeyPair();

    // // 获取私钥（PKCS#8 格式）
    PrivateKey privateKey = keyPair.getPrivate();
    byte[] privateKeyBytes = privateKey.getEncoded();

    // // 获取公钥（X.509 格式）
    PublicKey publicKey = keyPair.getPublic();
    byte[] publicKeyBytes = publicKey.getEncoded();

    // 使用 PKCS#8 格式获取私钥
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
    String pkcs8PrivateKey = Base64.getEncoder().encodeToString(pkcs8EncodedKeySpec.getEncoded());

    // 获取公钥（X.509 格式）
    X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey.getEncoded());
    String x509PublicKey = Base64.getEncoder().encodeToString(x509EncodedKeySpec.getEncoded());

    // 输出结果
    System.out.println("Base64 Encoded PKCS#8 Private Key:pkcs8PrivateKey");
    System.out.println(pkcs8PrivateKey);

    System.out.println("\nBase64 Encoded X.509 Public Key:x509PublicKey");
    System.out.println(x509PublicKey);
  }
}
