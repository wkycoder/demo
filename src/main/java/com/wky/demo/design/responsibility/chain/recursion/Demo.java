package com.wky.demo.design.responsibility.chain.recursion;

/**
 * @author wuming
 * @date 2024/3/29/03/29 10:03
 */
public class Demo {


    public static void main(String[] args) {
        FilterChain filterChain = new FilterChain();
        filterChain.addFilter(new AFilter());
        filterChain.addFilter(new BFilter());

        filterChain.filter("测试请求");
    }





}
