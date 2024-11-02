package com.wky.demo.design.responsibility.chain.recursion;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 参考：
 * @see org.apache.catalina.core.ApplicationFilterChain
 * @see org.springframework.aop.framework.ReflectiveMethodInvocation
 *
 * @author wuming
 * @date 2024/3/29/03/29 09:04
 */
@Component
public class FilterChain {

//    @Resource
    private List<Filter> filters;

    /**
     * 持有的业务service
     */
    private final BusinessService businessService = new BusinessService();

    /**
     * 当前执行的过滤器对应的下标
     */
    private int currentIndex = 0;

    public void addFilter(Filter filter) {
        if (filters == null) {
            filters = new ArrayList<>();
        }
        filters.add(filter);
    }


    /**
     * 可以学Spring MVC的过滤器将返回值作为参数传递给下一个拦截器
     * 也可以学Spring Aop将返回值直接返回给调用方（目标方法存在返回值）
     * @param request
     */
    public void filter(String request) {
        // 使用递归方式执行，是为了实现双向拦截
        // 既能拦截用户请求，也能拦截响应结果
        if (currentIndex > filters.size() - 1) {
            // 过滤器全部执行完毕，开始执行业务逻辑
            businessService.test(request);
            return;
        }
        // 递归执行过滤器
        Filter filter = filters.get(currentIndex++);
        filter.filter(request, this);
    }


}
