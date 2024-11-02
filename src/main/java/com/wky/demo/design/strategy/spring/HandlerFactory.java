package com.wky.demo.design.strategy.spring;

import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.Map;

/**
 * 策略工厂，用于创建具体的策略对象
 * spring版本，依托于spring的容器，利用spring的依赖注入功能，自动注入策略对象
 * 可以用List接收也是利用Map接收，这里使用Map接收
 *
 * @author wuming
 * @date 2024/6/15/06/15 19:25
 */
@Component
public class HandlerFactory {

    // key是bean名称，value是bean对象
    @Resource
    private Map<String, Handler> handlers;

    public Handler getHandler(Integer type) {
        return handlers.get(HandlerEnum.getHandler(type));
    }


}
