package com.opengroup.longmao.gwcommon.configuration.druid;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;

/**
 *  sql监控拦截器配置，
 *  note：有多种方式可以进行监控，由于分库分表组件异步了，threadlocal绑定操作比较复杂这里对连接池监控
 * @version 
 * @author liuyi  2016年7月18日 上午11:32:36
 * 
 */
@Configuration
public class DataSouceMonitorConfig {
	@Bean
	public DruidStatInterceptor druidStatInterceptor() {
		return new DruidStatInterceptor();
	}
	
	@Bean
	public BeanNameAutoProxyCreator beanNameAutoProxyCreator() {
		
		BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
		creator.setProxyTargetClass(true);
		creator.setBeanNames("classesController");
		creator.setInterceptorNames("druidStatInterceptor");		
		return creator;
	}
}
