package com.wky.demo.responsibility.chain.leave;

import lombok.Data;

/**
 * 请假请求信息
 *
 * @author wangkunyang
 * @date 2023/04/07 10:44
 */
@Data
public class LeaveRequest {

    /**
     * 员工信息
     */
    private String name;

    /**
     * 请假天数
     */
    private int days;

}
