package com.opengroup.longmao.gwcommon.configuration.properties;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 使用tomcat配置
 *
 * @version 
 * @author liuyi  2016年4月21日 下午8:02:53
 * 
 */
@Configuration
public class TomcatConfig {
	@Value("${spring.server.port}")
	private String port;
	@Value("${spring.server.acceptorThreadCount}")
	private String acceptorThreadCount;
	@Value("${spring.server.minSpareThreads}")
	private String minSpareThreads;
	@Value("${spring.server.maxSpareThreads}")
	private String maxSpareThreads;
	@Value("${spring.server.maxThreads}")
	private String maxThreads;
	@Value("${spring.server.maxConnections}")
	private String maxConnections;
	@Value("${spring.server.protocol}")
	private String protocol;
	@Value("${spring.server.redirectPort}")
	private String redirectPort;
	@Value("${spring.server.compression}")
	private String compression;
	@Value("${spring.server.connectionTimeout}")
	private String connectionTimeout;
	
	@Bean
	public TomcatEmbeddedServletContainerFactory tomcatFactory() {
		TomcatEmbeddedServletContainerFactory tomcatFactory = new TomcatEmbeddedServletContainerFactory();
		tomcatFactory.addConnectorCustomizers(new GwsTomcatConnectionCustomizer());
		return tomcatFactory;
	}
	
	public class GwsTomcatConnectionCustomizer implements TomcatConnectorCustomizer {

		public GwsTomcatConnectionCustomizer() {
		}

		@Override
		public void customize(Connector connector) {
			connector.setPort(Integer.valueOf(port));			
			connector.setAttribute("connectionTimeout", connectionTimeout);
	        connector.setAttribute("acceptorThreadCount", acceptorThreadCount);
	        connector.setAttribute("minSpareThreads", minSpareThreads);
	        connector.setAttribute("maxSpareThreads", maxSpareThreads);
	        connector.setAttribute("maxThreads", maxThreads);
	        connector.setAttribute("maxConnections", maxConnections);
	        connector.setAttribute("protocol", protocol);
	        connector.setAttribute("redirectPort", "redirectPort");
	        connector.setAttribute("compression", "compression");
		}
	}
}
