package com.wky.demo.design.responsibility.chain.list;

/**
 * 这里我们可以使用模板方法模式抽取公共的内容
 * 使得代码变得更优雅
 * @author wangkunyang
 * @date 2023/04/06 14:50
 */
public abstract class Handler {

    /**
     * 下一个处理器
     */
    protected Handler next;

    public void setNext(Handler next) {
        this.next = next;
    }

    /**
     * 处理方法（处理职责）
     */
//    protected abstract void handle();


    public void handle() {
        boolean handled = doHandle();
        // 不处理（不支持）并且下一个处理器不为空，执行下一个处理器的处理方法
        if (!handled && next != null) {
            next.handle();
        }
    }

    /**
     * 执行具体的处理逻辑
     *
     * @return
     */
    protected abstract boolean doHandle();


}
