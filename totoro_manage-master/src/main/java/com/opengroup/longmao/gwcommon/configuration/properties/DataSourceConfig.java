package com.opengroup.longmao.gwcommon.configuration.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 配置文件
 *
 * @version
 * @author Hermit 2017年2月19日 下午9:22:32
 * 
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class DataSourceConfig {

	// master
	@Value("${master.spring.datasource.url}")
	private String masterDbUrl;

	@Value("${master.spring.datasource.username}")
	private String masterUsername;

	@Value("${master.spring.datasource.password}")
	private String masterPassword;

	@Value("${master.spring.datasource.driverClassName}")
	private String masterDriverClassName;

	@Value("${master.spring.datasource.type}")
	private String masterType;

	@Value("${master.spring.datasource.initialSize}")
	private int masterInitialSize;

	@Value("${master.spring.datasource.minIdle}")
	private int masterMinIdle;

	@Value("${master.spring.datasource.maxActive}")
	private int masterMaxActive;

	@Value("${master.spring.datasource.maxWait}")
	private int masterMaxWait;

	@Value("${master.spring.datasource.timeBetweenEvictionRunsMillis}")
	private int masterTimeBetweenEvictionRunsMillis;

	@Value("${master.spring.datasource.minEvictableIdleTimeMillis}")
	private int masterMinEvictableIdleTimeMillis;

	@Value("${master.spring.datasource.validationQuery}")
	private String masterValidationQuery;

	@Value("${master.spring.datasource.testWhileIdle}")
	private Boolean masterTestWhileIdle;

	@Value("${master.spring.datasource.testOnBorrow}")
	private Boolean masterTestOnBorrow;

	@Value("${master.spring.datasource.testOnReturn}")
	private Boolean masterTestOnReturn;

	@Value("${master.spring.datasource.poolPreparedStatements}")
	private Boolean masterPoolPreparedStatements;

	@Value("${master.spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
	private int masterMppspcs;

	@Value("${master.spring.datasource.filters}")
	private String masterFilters;

	@Value("${master.spring.datasource.connectionProperties}")
	private String masterConnectionProperties;

	@Value("${master.spring.datasource.useGlobalDataSourceStat}")
	private Boolean masterUseGlobalDataSourceStat = false;

	// slave
	@Value("${slave.spring.datasource.url}")
	private String slaveDbUrl;

	@Value("${slave.spring.datasource.username}")
	private String slaveUsername;

	@Value("${slave.spring.datasource.password}")
	private String slavePassword;

	@Value("${slave.spring.datasource.driverClassName}")
	private String slaveDriverClassName;

	@Value("${slave.spring.datasource.type}")
	private String slaveType;

	@Value("${slave.spring.datasource.initialSize}")
	private int slaveInitialSize;

	@Value("${slave.spring.datasource.minIdle}")
	private int slaveMinIdle;

	@Value("${slave.spring.datasource.maxActive}")
	private int slaveMaxActive;

	@Value("${slave.spring.datasource.maxWait}")
	private int slaveMaxWait;

	@Value("${slave.spring.datasource.timeBetweenEvictionRunsMillis}")
	private int slaveTimeBetweenEvictionRunsMillis;

	@Value("${slave.spring.datasource.minEvictableIdleTimeMillis}")
	private int slaveMinEvictableIdleTimeMillis;

	@Value("${slave.spring.datasource.validationQuery}")
	private String slaveValidationQuery;

	@Value("${slave.spring.datasource.testWhileIdle}")
	private Boolean slaveTestWhileIdle;

	@Value("${slave.spring.datasource.testOnBorrow}")
	private Boolean slaveTestOnBorrow;

	@Value("${slave.spring.datasource.testOnReturn}")
	private Boolean slaveTestOnReturn;

	@Value("${slave.spring.datasource.poolPreparedStatements}")
	private Boolean slavePoolPreparedStatements;

	@Value("${slave.spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
	private int slaveMppspcs;

	@Value("${slave.spring.datasource.filters}")
	private String slaveFilters;

	@Value("${slave.spring.datasource.connectionProperties}")
	private String slaveConnectionProperties;

	@Value("${slave.spring.datasource.useGlobalDataSourceStat}")
	private Boolean slaveUseGlobalDataSourceStat = false;

	public String getMasterDbUrl() {
		return masterDbUrl;
	}

	public void setMasterDbUrl(String masterDbUrl) {
		this.masterDbUrl = masterDbUrl;
	}

	public String getMasterUsername() {
		return masterUsername;
	}

	public void setMasterUsername(String masterUsername) {
		this.masterUsername = masterUsername;
	}

	public String getMasterPassword() {
		return masterPassword;
	}

	public void setMasterPassword(String masterPassword) {
		this.masterPassword = masterPassword;
	}

	public String getMasterDriverClassName() {
		return masterDriverClassName;
	}

	public void setMasterDriverClassName(String masterDriverClassName) {
		this.masterDriverClassName = masterDriverClassName;
	}

	public String getMasterType() {
		return masterType;
	}

	public void setMasterType(String masterType) {
		this.masterType = masterType;
	}

	public int getMasterInitialSize() {
		return masterInitialSize;
	}

	public void setMasterInitialSize(int masterInitialSize) {
		this.masterInitialSize = masterInitialSize;
	}

	public int getMasterMinIdle() {
		return masterMinIdle;
	}

	public void setMasterMinIdle(int masterMinIdle) {
		this.masterMinIdle = masterMinIdle;
	}

	public int getMasterMaxActive() {
		return masterMaxActive;
	}

	public void setMasterMaxActive(int masterMaxActive) {
		this.masterMaxActive = masterMaxActive;
	}

	public int getMasterMaxWait() {
		return masterMaxWait;
	}

	public void setMasterMaxWait(int masterMaxWait) {
		this.masterMaxWait = masterMaxWait;
	}

	public int getMasterTimeBetweenEvictionRunsMillis() {
		return masterTimeBetweenEvictionRunsMillis;
	}

	public void setMasterTimeBetweenEvictionRunsMillis(int masterTimeBetweenEvictionRunsMillis) {
		this.masterTimeBetweenEvictionRunsMillis = masterTimeBetweenEvictionRunsMillis;
	}

	public int getMasterMinEvictableIdleTimeMillis() {
		return masterMinEvictableIdleTimeMillis;
	}

	public void setMasterMinEvictableIdleTimeMillis(int masterMinEvictableIdleTimeMillis) {
		this.masterMinEvictableIdleTimeMillis = masterMinEvictableIdleTimeMillis;
	}

	public String getMasterValidationQuery() {
		return masterValidationQuery;
	}

	public void setMasterValidationQuery(String masterValidationQuery) {
		this.masterValidationQuery = masterValidationQuery;
	}

	public Boolean getMasterTestWhileIdle() {
		return masterTestWhileIdle;
	}

	public void setMasterTestWhileIdle(Boolean masterTestWhileIdle) {
		this.masterTestWhileIdle = masterTestWhileIdle;
	}

	public Boolean getMasterTestOnBorrow() {
		return masterTestOnBorrow;
	}

	public void setMasterTestOnBorrow(Boolean masterTestOnBorrow) {
		this.masterTestOnBorrow = masterTestOnBorrow;
	}

	public Boolean getMasterTestOnReturn() {
		return masterTestOnReturn;
	}

	public void setMasterTestOnReturn(Boolean masterTestOnReturn) {
		this.masterTestOnReturn = masterTestOnReturn;
	}

	public Boolean getMasterPoolPreparedStatements() {
		return masterPoolPreparedStatements;
	}

	public void setMasterPoolPreparedStatements(Boolean masterPoolPreparedStatements) {
		this.masterPoolPreparedStatements = masterPoolPreparedStatements;
	}

	public int getMasterMppspcs() {
		return masterMppspcs;
	}

	public void setMasterMppspcs(int masterMppspcs) {
		this.masterMppspcs = masterMppspcs;
	}

	public String getMasterFilters() {
		return masterFilters;
	}

	public void setMasterFilters(String masterFilters) {
		this.masterFilters = masterFilters;
	}

	public String getMasterConnectionProperties() {
		return masterConnectionProperties;
	}

	public void setMasterConnectionProperties(String masterConnectionProperties) {
		this.masterConnectionProperties = masterConnectionProperties;
	}

	public Boolean getMasterUseGlobalDataSourceStat() {
		return masterUseGlobalDataSourceStat;
	}

	public void setMasterUseGlobalDataSourceStat(Boolean masterUseGlobalDataSourceStat) {
		this.masterUseGlobalDataSourceStat = masterUseGlobalDataSourceStat;
	}

	public String getSlaveDbUrl() {
		return slaveDbUrl;
	}

	public void setSlaveDbUrl(String slaveDbUrl) {
		this.slaveDbUrl = slaveDbUrl;
	}

	public String getSlaveUsername() {
		return slaveUsername;
	}

	public void setSlaveUsername(String slaveUsername) {
		this.slaveUsername = slaveUsername;
	}

	public String getSlavePassword() {
		return slavePassword;
	}

	public void setSlavePassword(String slavePassword) {
		this.slavePassword = slavePassword;
	}

	public String getSlaveDriverClassName() {
		return slaveDriverClassName;
	}

	public void setSlaveDriverClassName(String slaveDriverClassName) {
		this.slaveDriverClassName = slaveDriverClassName;
	}

	public String getSlaveType() {
		return slaveType;
	}

	public void setSlaveType(String slaveType) {
		this.slaveType = slaveType;
	}

	public int getSlaveInitialSize() {
		return slaveInitialSize;
	}

	public void setSlaveInitialSize(int slaveInitialSize) {
		this.slaveInitialSize = slaveInitialSize;
	}

	public int getSlaveMinIdle() {
		return slaveMinIdle;
	}

	public void setSlaveMinIdle(int slaveMinIdle) {
		this.slaveMinIdle = slaveMinIdle;
	}

	public int getSlaveMaxActive() {
		return slaveMaxActive;
	}

	public void setSlaveMaxActive(int slaveMaxActive) {
		this.slaveMaxActive = slaveMaxActive;
	}

	public int getSlaveMaxWait() {
		return slaveMaxWait;
	}

	public void setSlaveMaxWait(int slaveMaxWait) {
		this.slaveMaxWait = slaveMaxWait;
	}

	public int getSlaveTimeBetweenEvictionRunsMillis() {
		return slaveTimeBetweenEvictionRunsMillis;
	}

	public void setSlaveTimeBetweenEvictionRunsMillis(int slaveTimeBetweenEvictionRunsMillis) {
		this.slaveTimeBetweenEvictionRunsMillis = slaveTimeBetweenEvictionRunsMillis;
	}

	public int getSlaveMinEvictableIdleTimeMillis() {
		return slaveMinEvictableIdleTimeMillis;
	}

	public void setSlaveMinEvictableIdleTimeMillis(int slaveMinEvictableIdleTimeMillis) {
		this.slaveMinEvictableIdleTimeMillis = slaveMinEvictableIdleTimeMillis;
	}

	public String getSlaveValidationQuery() {
		return slaveValidationQuery;
	}

	public void setSlaveValidationQuery(String slaveValidationQuery) {
		this.slaveValidationQuery = slaveValidationQuery;
	}

	public Boolean getSlaveTestWhileIdle() {
		return slaveTestWhileIdle;
	}

	public void setSlaveTestWhileIdle(Boolean slaveTestWhileIdle) {
		this.slaveTestWhileIdle = slaveTestWhileIdle;
	}

	public Boolean getSlaveTestOnBorrow() {
		return slaveTestOnBorrow;
	}

	public void setSlaveTestOnBorrow(Boolean slaveTestOnBorrow) {
		this.slaveTestOnBorrow = slaveTestOnBorrow;
	}

	public Boolean getSlaveTestOnReturn() {
		return slaveTestOnReturn;
	}

	public void setSlaveTestOnReturn(Boolean slaveTestOnReturn) {
		this.slaveTestOnReturn = slaveTestOnReturn;
	}

	public Boolean getSlavePoolPreparedStatements() {
		return slavePoolPreparedStatements;
	}

	public void setSlavePoolPreparedStatements(Boolean slavePoolPreparedStatements) {
		this.slavePoolPreparedStatements = slavePoolPreparedStatements;
	}

	public int getSlaveMppspcs() {
		return slaveMppspcs;
	}

	public void setSlaveMppspcs(int slaveMppspcs) {
		this.slaveMppspcs = slaveMppspcs;
	}

	public String getSlaveFilters() {
		return slaveFilters;
	}

	public void setSlaveFilters(String slaveFilters) {
		this.slaveFilters = slaveFilters;
	}

	public String getSlaveConnectionProperties() {
		return slaveConnectionProperties;
	}

	public void setSlaveConnectionProperties(String slaveConnectionProperties) {
		this.slaveConnectionProperties = slaveConnectionProperties;
	}

	public Boolean getSlaveUseGlobalDataSourceStat() {
		return slaveUseGlobalDataSourceStat;
	}

	public void setSlaveUseGlobalDataSourceStat(Boolean slaveUseGlobalDataSourceStat) {
		this.slaveUseGlobalDataSourceStat = slaveUseGlobalDataSourceStat;
	}

}
