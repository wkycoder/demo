package com.wky.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 多数据源配置类
 * @author: wangkunyang
 * @date 2021/10/20 15:02
 */
@Configuration
public class DataSourceConfig {

    @Primary
    @Bean("demoDataSource")
    @ConfigurationProperties("spring.datasource.demo")
    public DataSource demoDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("tagDataSource")
    @ConfigurationProperties("spring.datasource.tag")
    public DataSource tagDataSource() {
        return DataSourceBuilder.create().build();
    }

}
