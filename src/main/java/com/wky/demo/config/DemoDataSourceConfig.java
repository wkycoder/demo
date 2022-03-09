package com.wky.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * 主要的数据源
 * @author: wangkunyang
 * @date 2021/10/20 15:10
 */
@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryDemo",
        transactionManagerRef = "transactionManagerDemo",
        basePackages = "com.wky.demo.repository")
public class DemoDataSourceConfig {

    @Autowired
    @Qualifier("demoDataSource")
    private DataSource demoDataSource;

    @Autowired
    private JpaProperties jpaProperties;

    @Primary
    @Bean("entityManagerFactoryDemo")
    public EntityManagerFactory entityManagerFactoryDemo() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        // 使用hibernate
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        // entity 扫描路径
        factory.setPackagesToScan("com.wky.demo.model");
        // 数据源
        factory.setDataSource(demoDataSource);
        // 配置
        factory.setJpaPropertyMap(jpaProperties.getProperties());
        //在完成了其它所有相关的配置加载以及属性设置后,才初始化
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Primary
    @Bean("entityManagerDemo")
    public EntityManager entityManagerDemo(@Qualifier("entityManagerFactoryDemo") EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }

    @Primary
    @Bean("transactionManagerDemo")
    public PlatformTransactionManager transactionManagerDemo(@Qualifier("entityManagerFactoryDemo") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
