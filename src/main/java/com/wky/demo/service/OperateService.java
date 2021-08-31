package com.wky.demo.service;

import java.math.BigDecimal;

/**
 * 运算
 * @author: wangkunyang
 * @date 2021/08/30 17:19
 */
public interface OperateService {

    /**
     * 指定用户的账户减去指定的金额
     * @param username
     * @param amount
     */
    void sub(String username, BigDecimal amount);

    /**
     * 指定用户的账户加上指定的金额
     * @param username
     * @param amount
     */
    void add(String username, BigDecimal amount);
}
