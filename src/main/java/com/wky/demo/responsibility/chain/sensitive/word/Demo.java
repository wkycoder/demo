package com.wky.demo.responsibility.chain.sensitive.word;

/**
 * @author wangkunyang
 * @date 2023/04/06 18:38
 */
public class Demo {

    public static void main(String[] args) {
        SensitiveWordFilterChain chain = new SensitiveWordFilterChain();
        chain.addFilter(new SexyWordFilter());

        boolean result = chain.doFilter(new Content());

        if (!result) {
            // 存在敏感词汇，不予提交，抛出异常
        }


    }

}
