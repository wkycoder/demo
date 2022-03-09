package com.wky.demo.observer;

import org.springframework.context.ApplicationEvent;

/**
 * @author: wangkunyang
 * @date 2022/01/26 09:47
 */
public class DemoEvent extends ApplicationEvent {

    private String message;

    public DemoEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
