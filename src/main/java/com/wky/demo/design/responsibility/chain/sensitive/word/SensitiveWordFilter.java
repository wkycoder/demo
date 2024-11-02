package com.wky.demo.design.responsibility.chain.sensitive.word;

/**
 * 敏感词过滤器
 *
 * @author wangkunyang
 * @date 2023/04/06 16:01
 */
public interface SensitiveWordFilter {

    /**
     * 过滤
     *
     * @param content
     * @return
     */
    boolean filter(Content content);

}
