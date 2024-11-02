package com.wky.demo.design.responsibility.chain.array;

/**
 * 数组版本
 * @author wangkunyang
 * @date 2023/04/06 15:33
 */
public abstract class Handler {

    /**
     * 执行具体的处理逻辑
     *
     * @return
     */
    protected abstract boolean handle();

    /**
     *
     * @return
     */
//    protected abstract int getPriority();

}
