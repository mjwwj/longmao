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
public class MessagePushConfig {
	
	@Value("${getui.app.id}")
	private String appId;

	@Value("${getui.app.key}")
	private String appKey;

	@Value("${getui.master.secret}")
	private String masterSecret;
	
	@Value("${getui.push.url}")
	private String pushUrl;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getMasterSecret() {
		return masterSecret;
	}

	public void setMasterSecret(String masterSecret) {
		this.masterSecret = masterSecret;
	}

	public String getPushUrl() {
		return pushUrl;
	}

	public void setPushUrl(String pushUrl) {
		this.pushUrl = pushUrl;
	}
	
	

}
