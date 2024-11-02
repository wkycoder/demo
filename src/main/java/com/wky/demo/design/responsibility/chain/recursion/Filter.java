package com.wky.demo.design.responsibility.chain.recursion;

/**
 * @author wuming
 * @date 2024/3/29/03/29 09:04
 */
public interface Filter {


    void filter(String request, FilterChain chain);


}
