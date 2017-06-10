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
public class EnvironmentConfig {
	
	@Value("${spring.domain}")
	private String webDomain;

	@Value("${db.showsql}")
	private Boolean showSql = false;
	
	@Value("${swagger.enable}")
	private String swagger_enable;
	
	public String getSwagger_enable() {
		return swagger_enable;
	}

	public void setSwagger_enable(String swagger_enable) {
		this.swagger_enable = swagger_enable;
	}

	public String getWebDomain() {
		return webDomain;
	}

	public void setWebDomain(String webDomain) {
		this.webDomain = webDomain;
	}

	public Boolean getShowSql() {
		return showSql;
	}

	public void setShowSql(Boolean showSql) {
		this.showSql = showSql;
	}

}
