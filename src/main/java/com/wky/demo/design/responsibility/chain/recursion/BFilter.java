package com.wky.demo.design.responsibility.chain.recursion;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author wuming
 * @date 2024/3/29/03/29 09:13
 */
@Order(1)
@Component
public class BFilter implements Filter {


    @Override
    public void filter(String request, FilterChain chain) {
        System.out.println("BFilter start execute");
        chain.filter(request);
        System.out.println("BFilter end execute");
        // 如果有返回值，可以在最后返回
    }
}
