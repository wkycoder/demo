package com.wky.demo.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: wangkunyang
 * @date 2021/08/27 11:05
 */
@AllArgsConstructor
public enum  UrlSuffixEnum {

    /**
     * url后缀枚举
     */
    XLS(1, "xls"),
    XLSX(2, "xlsx");

    @Getter
    private Integer code;
    @Getter
    private String value;
}
