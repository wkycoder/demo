package com.wky.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author wangkunyang
 * @date 2023/02/01 10:48
 */
@Configuration
public class ThreadPoolConfig {

    @Bean
    public ThreadPoolTaskExecutor fillTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(500);
        executor.setAllowCoreThreadTimeOut(true);
        executor.setKeepAliveSeconds(600);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setThreadNamePrefix("fill-task-");
        executor.setTaskDecorator(new FillerTaskDecorator());
        return executor;
    }

    static class FillerTaskDecorator implements TaskDecorator {

        @Override
        public Runnable decorate(Runnable runnable) {
//            UserInfo userInfo = UserHelper.getUserInfo();
            return () -> {
                try {
//                    UserHelper.setUserInfo(userInfo);
                    runnable.run();
                } finally {
//                    UserHelper.clear();
                }
            };
        }

    }







}
