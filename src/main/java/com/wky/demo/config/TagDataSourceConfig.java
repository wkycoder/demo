package com.wky.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        entityManagerFactoryRef = "entityManagerFactoryTag",
        transactionManagerRef = "transactionManagerTag",
        basePackages = "com.wky.demo.tag.repository")
public class TagDataSourceConfig {

    @Autowired
    @Qualifier("tagDataSource")
    private DataSource tagDataSource;

    @Autowired
    private JpaProperties jpaProperties;

    @Bean("entityManagerFactoryTag")
    public EntityManagerFactory entityManagerFactoryDemo() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        // 使用hibernate
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        // entity 扫描路径
        factory.setPackagesToScan("com.wky.demo.tag.model");
        // 数据源
        factory.setDataSource(tagDataSource);
        // 配置
        factory.setJpaPropertyMap(jpaProperties.getProperties());
        //在完成了其它所有相关的配置加载以及属性设置后,才初始化
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean("entityManagerTag")
    public EntityManager entityManagerDemo(@Qualifier("entityManagerFactoryTag") EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }

    @Bean("transactionManagerTag")
    public PlatformTransactionManager transactionManagerDemo(@Qualifier("entityManagerFactoryTag") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
