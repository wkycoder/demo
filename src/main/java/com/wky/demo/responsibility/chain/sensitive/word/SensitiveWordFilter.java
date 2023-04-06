package com.wky.demo.responsibility.chain.sensitive.word;

/**
 * 敏感词过滤器
 *
 * @author wangkunyang
 * @date 2023/04/06 16:01
 */
public interface SensitiveWordFilter {

    /**
     * 执行过滤
     *
     * @param content
     * @return
     */
    boolean doFilter(Content content);

}
