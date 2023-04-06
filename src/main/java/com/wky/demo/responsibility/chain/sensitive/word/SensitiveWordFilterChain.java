package com.wky.demo.responsibility.chain.sensitive.word;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author wangkunyang
 * @date 2023/04/06 16:48
 */
public class SensitiveWordFilterChain {

    private List<SensitiveWordFilter> filters = new ArrayList<>();

    public void addFilter(SensitiveWordFilter filter) {
        filters.add(filter);
    }

    public boolean doFilter(Content content) {
        for (SensitiveWordFilter filter : filters) {
            boolean result = filter.doFilter(content);
            // 存在敏感词汇
            if (!result) {
                return false;
            }
        }
        return true;
    }


}
