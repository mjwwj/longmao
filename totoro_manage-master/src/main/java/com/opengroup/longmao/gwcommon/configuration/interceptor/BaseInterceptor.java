package com.opengroup.longmao.gwcommon.configuration.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 
 * 【拦截器入口】
 *
 * @version
 * @author Hermit 2016年4月11日 下午5:50:06
 *
 */
@Configuration
@ComponentScan("com.opengroup.longmao.gwcommon.configuration.interceptor")
public class BaseInterceptor extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 多个拦截器组成一个拦截器链
		// addPathPatterns 用于添加拦截规则
		// excludePathPatterns 用户排除拦截
		registry.addInterceptor(accessInterceptor()).addPathPatterns("/**");
		registry.addInterceptor(httpInterceptor()).addPathPatterns("/**");
		registry.addInterceptor(RightsHandlerInterceptor()).addPathPatterns("/**");
		super.addInterceptors(registry);
	}

	@Bean
	public HandlerInterceptor accessInterceptor() {
		return new HttpInterceptor();
	}

	@Bean
	public HandlerInterceptor httpInterceptor() {
		return new LoginInterceptor();
	}

	@Bean
	public HandlerInterceptor RightsHandlerInterceptor() {
		return new RightsHandlerInterceptor();
	}
}
