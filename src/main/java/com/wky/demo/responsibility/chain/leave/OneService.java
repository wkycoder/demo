package com.wky.demo.responsibility.chain.leave;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wangkunyang
 * @date 2023/04/07 13:33
 */
@Service
public class OneService {

    @Resource
    private LeaveHandlerChain chain;


    public void test(LeaveRequest request) {
        chain.handle(request);

        // ....


    }



}
