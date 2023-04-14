package com.wky.demo.responsibility.chain.leave;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 主管
 *
 * @author wangkunyang
 * @date 2023/04/07 10:50
 */
@Order(1)
@Component
public class Director extends LeaveHandler {


    @Override
    public boolean handle(LeaveRequest request) {
        return false;
    }


}
