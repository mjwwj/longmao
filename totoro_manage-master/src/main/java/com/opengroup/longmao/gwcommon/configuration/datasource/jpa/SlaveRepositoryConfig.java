package com.opengroup.longmao.gwcommon.configuration.datasource.jpa;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.opengroup.longmao.gwcommon.configuration.jpa.BaseSimpleJpaRepositoryEx;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "slaveEntityManagerFactory",
//                       transactionManagerRef = "slaveTransactionManager",
                       basePackages = {"com.opengroup.longmao.gwcommon.repository.slave"},//设置dao（repo）所在位置
                       repositoryBaseClass = BaseSimpleJpaRepositoryEx.class)//jpa增強配置

public class SlaveRepositoryConfig {
	
    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    @Qualifier("slaveDataSource")
    private DataSource slaveDataSource;

    @Bean(name = "slaveEntityManager")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return slaveEntityManagerFactory(builder).getObject().createEntityManager();
    }

    @Bean(name = "slaveEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean slaveEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(slaveDataSource)
                .properties(getVendorProperties(slaveDataSource))
                .packages("com.opengroup.longmao.gwcommon.entity.po")//设置实体类所在位置
                .persistenceUnit("slavePersistenceUnit")
                .build(); 
    }

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }

    /*
     * 事务管理
     */
//    @Bean(name = "slaveTransactionManager")
//    PlatformTransactionManager slaveTransactionManager(EntityManagerFactoryBuilder builder) {
//        return new JpaTransactionManager(slaveEntityManagerFactory(builder).getObject());
//    }

}