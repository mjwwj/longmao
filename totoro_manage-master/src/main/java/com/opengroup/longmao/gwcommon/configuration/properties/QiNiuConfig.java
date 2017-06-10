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
public class QiNiuConfig {
	
	@Value("${qiniu.access.key}")
	private String qiniuAccessKey;
	
	@Value("${qiniu.secret.key}")
	private String qiniuSecretKey;
	
	@Value("${qiniu.bucket.file}")
	private String qiniuBucketName;
	
	@Value("${qiniu.domain.file}")
	private String qiniuDomainName;

	@Value("${qiniu.file.maxsize}")
	private String qiniuFileMaxSize;

	public String getQiniuAccessKey() {
		return qiniuAccessKey;
	}

	public void setQiniuAccessKey(String qiniuAccessKey) {
		this.qiniuAccessKey = qiniuAccessKey;
	}

	public String getQiniuSecretKey() {
		return qiniuSecretKey;
	}

	public void setQiniuSecretKey(String qiniuSecretKey) {
		this.qiniuSecretKey = qiniuSecretKey;
	}

	public String getQiniuBucketName() {
		return qiniuBucketName;
	}

	public void setQiniuBucketName(String qiniuBucketName) {
		this.qiniuBucketName = qiniuBucketName;
	}

	public String getQiniuDomainName() {
		return qiniuDomainName;
	}

	public void setQiniuDomainName(String qiniuDomainName) {
		this.qiniuDomainName = qiniuDomainName;
	}

	public String getQiniuFileMaxSize() {
		return qiniuFileMaxSize;
	}

	public void setQiniuFileMaxSize(String qiniuFileMaxSize) {
		this.qiniuFileMaxSize = qiniuFileMaxSize;
	}


}