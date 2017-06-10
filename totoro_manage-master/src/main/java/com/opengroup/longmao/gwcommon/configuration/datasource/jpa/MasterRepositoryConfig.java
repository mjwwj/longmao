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
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.opengroup.longmao.gwcommon.configuration.jpa.BaseSimpleJpaRepositoryEx;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "masterEntityManagerFactory",
//                       transactionManagerRef = "masterTransactionManager",
                       basePackages = {"com.opengroup.longmao.gwcommon.repository.master"},//设置dao（repo）所在位置
                       repositoryBaseClass = BaseSimpleJpaRepositoryEx.class)//jpa增強配置

public class MasterRepositoryConfig {
	
    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    @Qualifier("masterDataSource")
    private DataSource masterDataSource;

    @Bean(name = "masterEntityManager")
    @Primary
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return masterEntityManagerFactory(builder).getObject().createEntityManager();
    }

    @Bean(name = "masterEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean masterEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(masterDataSource)
                .properties(getVendorProperties(masterDataSource))
                .packages("com.opengroup.longmao.gwcommon.entity.po")//设置实体类所在位置
                .persistenceUnit("masterPersistenceUnit")
                .build(); 
    }

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }

    /*
     * 事务管理
     */
//    @Bean(name = "masterTransactionManager")
//    @Primary
//    PlatformTransactionManager masterTransactionManager(EntityManagerFactoryBuilder builder) {
//        return new JpaTransactionManager(masterEntityManagerFactory(builder).getObject());
//    }

}