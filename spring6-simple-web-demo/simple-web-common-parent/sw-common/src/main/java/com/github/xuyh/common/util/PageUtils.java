package com.github.xuyh.common.util;

import java.util.List;

import com.github.xuyh.common.domain.dto.PageResult;

public class PageUtils {
  public static <T> PageResult<T> result(long total, List<T> list) {
    return new PageResult<>(total, list);
  }
}
