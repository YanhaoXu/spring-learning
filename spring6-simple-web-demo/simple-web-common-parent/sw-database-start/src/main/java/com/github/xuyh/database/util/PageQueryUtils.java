package com.github.xuyh.database.util;

import com.github.pagehelper.PageHelper;
import com.github.xuyh.common.domain.dto.PageQueryParam;

public class PageQueryUtils extends PageHelper {

  public static void startPage(PageQueryParam pageQueryParam) {
    PageHelper.startPage(pageQueryParam.getPageNo(), pageQueryParam.getPageSize())
        .setReasonable(pageQueryParam.getReasonable());
  }

  public static void clearPage() {
    PageHelper.clearPage();
  }
}
