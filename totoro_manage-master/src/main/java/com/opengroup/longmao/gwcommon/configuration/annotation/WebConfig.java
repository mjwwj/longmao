package com.opengroup.longmao.gwcommon.configuration.annotation;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		// equivalent to <mvc:argument-resolvers>
		argumentResolvers.add(0, new JsonParameterBinder());
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		// converters.add(0, new FastJsonHttpMessageConverter());

		super.configureMessageConverters(converters);

		FastJsonHttpMessageConverter4 fastConverter = new FastJsonHttpMessageConverter4();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.QuoteFieldNames, SerializerFeature.WriteMapNullValue,
				SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullNumberAsZero,
				SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullBooleanAsFalse,
				SerializerFeature.WriteNonStringKeyAsString, SerializerFeature.WriteDateUseDateFormat);

		fastConverter.setFastJsonConfig(fastJsonConfig);

		converters.add(fastConverter);
	}

}