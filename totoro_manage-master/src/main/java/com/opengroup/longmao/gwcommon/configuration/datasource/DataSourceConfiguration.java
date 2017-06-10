package com.opengroup.longmao.gwcommon.configuration.datasource;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
//import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;
import com.opengroup.longmao.gwcommon.configuration.log.GwsLogger;
import com.opengroup.longmao.gwcommon.configuration.properties.DataSourceConfig;

/**
 * 配置两个DatasourceBean
 * 
 * @author Hermit
 *
 */
@Configuration
public class DataSourceConfiguration {

	@Autowired
	private DataSourceConfig dataSourceConfig;
	
	@Bean(name = "masterDataSource")
	@Primary
//	@ConfigurationProperties(prefix = "master.spring.datasource")
	@Qualifier("masterDataSource")
	public DataSource masterDataSource() throws Exception {
		GwsLogger.info("master主库数据源初始化中...");
		System.out.println("master datasource init ...");
		
		// dbcp，dbcp2连接池
		// return DataSourceBuilder.create().build();
		
		// druid连接池
		DruidDataSource masterDruidDataSource = new DruidDataSource();
		
        masterDruidDataSource.setUrl(dataSourceConfig.getMasterDbUrl());  
        masterDruidDataSource.setUsername(dataSourceConfig.getMasterUsername());  
        masterDruidDataSource.setPassword(dataSourceConfig.getMasterPassword());  
        masterDruidDataSource.setDriverClassName(dataSourceConfig.getMasterDriverClassName());  
          
        //configuration  
        masterDruidDataSource.setInitialSize(dataSourceConfig.getMasterInitialSize());  
        masterDruidDataSource.setMinIdle(dataSourceConfig.getMasterMinIdle());  
        masterDruidDataSource.setMaxActive(dataSourceConfig.getMasterMaxActive());  
        masterDruidDataSource.setMaxWait(dataSourceConfig.getMasterMaxWait());  
        masterDruidDataSource.setTimeBetweenEvictionRunsMillis(dataSourceConfig.getMasterTimeBetweenEvictionRunsMillis());  
        masterDruidDataSource.setMinEvictableIdleTimeMillis(dataSourceConfig.getMasterMinEvictableIdleTimeMillis());  
        masterDruidDataSource.setValidationQuery(dataSourceConfig.getMasterValidationQuery());  
        masterDruidDataSource.setTestWhileIdle(dataSourceConfig.getMasterTestWhileIdle());  
        masterDruidDataSource.setTestOnBorrow(dataSourceConfig.getMasterTestOnBorrow());  
        masterDruidDataSource.setTestOnReturn(dataSourceConfig.getMasterTestOnReturn());  
        masterDruidDataSource.setPoolPreparedStatements(dataSourceConfig.getMasterPoolPreparedStatements());  
        masterDruidDataSource.setMaxPoolPreparedStatementPerConnectionSize(dataSourceConfig.getMasterMppspcs());  
        try {
            masterDruidDataSource.setFilters(dataSourceConfig.getMasterFilters());  
        } catch (SQLException e) {  
        	GwsLogger.error("master druid configuration initialization filter", e);  
        }  
        masterDruidDataSource.setConnectionProperties(dataSourceConfig.getMasterConnectionProperties());
        
		GwsLogger.info("master主库数据源初始化成功。");
		System.out.println("master datasource init success.");
		
		return masterDruidDataSource;
	}

	@Bean(name = "slaveDataSource")
	@Qualifier("slaveDataSource")
//	@ConfigurationProperties(prefix = "slave.spring.datasource")
	public DataSource slaveDataSource() throws Exception {
		GwsLogger.info("slave从库数据源初始化中...");
		System.out.println("slave datasource init ...");
		
		// dbcp，dbcp2连接池
		// return DataSourceBuilder.create().build();
		
		// druid连接池
		DruidDataSource slaveDruidDataSource = new DruidDataSource();  
		
        slaveDruidDataSource.setUrl(dataSourceConfig.getSlaveDbUrl());  
        slaveDruidDataSource.setUsername(dataSourceConfig.getSlaveUsername());  
        slaveDruidDataSource.setPassword(dataSourceConfig.getSlavePassword());  
        slaveDruidDataSource.setDriverClassName(dataSourceConfig.getSlaveDriverClassName());  
          
        //configuration  
        slaveDruidDataSource.setInitialSize(dataSourceConfig.getSlaveInitialSize());  
        slaveDruidDataSource.setMinIdle(dataSourceConfig.getSlaveMinIdle());  
        slaveDruidDataSource.setMaxActive(dataSourceConfig.getSlaveMaxActive());  
        slaveDruidDataSource.setMaxWait(dataSourceConfig.getSlaveMaxWait());  
        slaveDruidDataSource.setTimeBetweenEvictionRunsMillis(dataSourceConfig.getSlaveTimeBetweenEvictionRunsMillis());  
        slaveDruidDataSource.setMinEvictableIdleTimeMillis(dataSourceConfig.getSlaveMinEvictableIdleTimeMillis());  
        slaveDruidDataSource.setValidationQuery(dataSourceConfig.getSlaveValidationQuery());  
        slaveDruidDataSource.setTestWhileIdle(dataSourceConfig.getSlaveTestWhileIdle());  
        slaveDruidDataSource.setTestOnBorrow(dataSourceConfig.getSlaveTestOnBorrow());  
        slaveDruidDataSource.setTestOnReturn(dataSourceConfig.getSlaveTestOnReturn());  
        slaveDruidDataSource.setPoolPreparedStatements(dataSourceConfig.getSlavePoolPreparedStatements());  
        slaveDruidDataSource.setMaxPoolPreparedStatementPerConnectionSize(dataSourceConfig.getSlaveMppspcs());  
        try {  
            slaveDruidDataSource.setFilters(dataSourceConfig.getSlaveFilters());  
        } catch (SQLException e) {  
        	GwsLogger.error("slave druid configuration initialization filter", e);  
        }  
        slaveDruidDataSource.setConnectionProperties(dataSourceConfig.getSlaveConnectionProperties());
        
		GwsLogger.info("slave主库数据源初始化成功。");
		System.out.println("slave datasource init success.");
		
		return slaveDruidDataSource;
	}
}