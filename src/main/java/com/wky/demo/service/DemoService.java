package com.wky.demo.service;

import com.wky.demo.model.vo.ExcelVO;

import java.util.List;

/**
 * @author: wangkunyang
 * @date 2021/08/27 11:16
 */
public interface DemoService {

    /**
     * 处理数据
     * @param list
     */
    void processData(List<ExcelVO> list);
}
