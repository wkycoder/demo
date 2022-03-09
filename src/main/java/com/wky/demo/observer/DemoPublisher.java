package com.wky.demo.observer;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: wangkunyang
 * @date 2022/01/26 10:18
 */
@Component
public class DemoPublisher {

    @Resource
    private ApplicationContext applicationContext;


    public void publishEvent(DemoEvent event) {
        applicationContext.publishEvent(event);
    }


}
