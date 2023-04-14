package com.wky.demo.utils.excel;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author wangkunyang
 * @date 2023/02/28 16:31
 */
@Data
@Builder
public class ExcelSheet {

    private String sheetName;

    private Class<?> sheetHead;

    private List<?> sheetData;

}
