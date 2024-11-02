package com.wky.demo.design.responsibility.chain.sensitive.word;

/**
 * @author wangkunyang
 * @date 2023/04/06 18:35
 */
public class SexyWordFilter implements SensitiveWordFilter {

    @Override
    public boolean filter(Content content) {
        return !content.getText().contains("sex");
    }

}
