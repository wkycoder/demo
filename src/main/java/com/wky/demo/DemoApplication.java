package com.wky.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author User
 */
@EnableAsync
//@EnableAspectJAutoProxy(exposeProxy = true)
//@EnableTransactionManagement // 这个注解其实并不需要写，TransactionAutoConfiguration#TransactionAutoConfiguration
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
