package com.github.xuyh.common.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
public class PageQueryParam {

  /**
   * 当前页
   */
  private Integer pageNo;

  /**
   * 每页数据条数
   */
  private Integer pageSize;

  /**
   * 分页合理化参数
   */
  @Getter
  private Boolean reasonable = true;


  public Integer getPageNo() {
    if (pageNo == null || pageNo <= 0) {
      return 1;
    }
    return pageNo;
  }

  public Integer getPageSize() {

    if (pageSize == null || pageSize <= 0) {
      return 10;
    }

    // 防止一次查询太多, 搞垮数据库
    if (pageSize > 5000) {
      return 5000;
    }
    return pageSize;
  }
}
