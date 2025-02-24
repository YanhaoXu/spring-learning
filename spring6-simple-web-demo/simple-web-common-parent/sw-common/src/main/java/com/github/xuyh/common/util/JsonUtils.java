package com.github.xuyh.common.util;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class JsonUtils {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  static {
    // 反序列化遇到未知字段, 不报错
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  /**
   * Convert t.
   *
   * @param <T> the type parameter
   * @param object the object
   * @param returnType the return type
   * @return the t
   */
  public static <T> T convert(Object object, Class<T> returnType) {
    if (object == null) {
      return null;
    }

    return parse(stringify(object), returnType);
  }

  /**
   * Stringify string.
   *
   * @param object the object
   * @return the string
   */
  public static String stringify(Object object) {
    try {
      if (object == null) {
        return null;
      } else if (object instanceof String) {
        return object.toString();
      }
      return objectMapper.writeValueAsString(object);
    } catch (Exception e) {
      throw new IllegalArgumentException("对象转化成json字符串出错", e);
    }
  }


  /**
   * Parse t.
   *
   * @param <T> the type parameter
   * @param json the json
   * @param targetType the target type
   * @return the t
   */
  public static <T> T parse(String json, Type targetType) {
    try {
      return objectMapper.readValue(json, TypeFactory.defaultInstance().constructType(targetType));
    } catch (Exception e) {
      throw new IllegalArgumentException("将JSON转换为对象时发生错误" + json, e);
    }
  }

  /**
   * Parse t.
   *
   * @param <T> the type parameter
   * @param json the json
   * @param targetType the target type
   * @return the t
   */
  public static <T> T parse(String json, Class<T> targetType) {
    try {
      return objectMapper.readValue(json, TypeFactory.defaultInstance().constructType(targetType));
    } catch (Exception e) {
      throw new IllegalArgumentException("将JSON转换为对象时发生错误" + json, e);
    }
  }

  /**
   * Parse t.
   *
   * @param <T> the type parameter
   * @param json the json
   * @param typeReference the type reference
   * @return the t
   */
  public static <T> T parse(String json, TypeReference<T> typeReference) {
    if (StringUtils.isNotEmpty(json)) {
      try {
        return objectMapper.readValue(json, typeReference);
      } catch (IOException e) {
        throw new RuntimeException("将JSON转换为对象时发生错误", e);
      }
    } else {
      return null;
    }
  }


  public static Map<String, Object> parseToMap(String json) {
    return parse(json, HashMap.class);
  }

}
