package com.opengroup.longmao.gwcommon.configuration.swagger;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;

import com.google.common.base.Predicate;
import com.opengroup.longmao.gwcommon.configuration.properties.EnvironmentConfig;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Autowired
	private EnvironmentConfig environmentConfig;

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfoBuilder()
        .title("Totoro Pay Rest API")//大标题
        .description("龙猫运动支付服务 V1.8 REST API.")//详细描述
        .version("1.8")//版本
        .termsOfServiceUrl("NO terms of service")//服务条款
        .contact("Hermit")//作者
//      .contact(new Contact("Hermit", "http://blog.csdn.net/catoop", "85583755@qq.com"))//作者
        .license("The Apache License, Version 2.0")//链接显示文字
        .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")//网站链接
        .build();

        return apiInfo;
    }

    private String initContextInfo() {
        StringBuffer sb = new StringBuffer();
        sb.append("REST API 设计在细节上有很多自己独特的需要注意的技巧，并且对开发人员在构架设计能力上比传统 API 有着更高的要求。")
                .append("<br/>")
                .append("本文通过翔实的叙述和一系列的范例，从整体结构，到局部细节，分析和解读了为了提高易用性和高效性，REST API 设计应该注意哪些问题以及如何解决这些问题。");
        return sb.toString();
    }

    @Bean
    public Docket restfulApi() {
      return new Docket(DocumentationType.SWAGGER_2).enable(Boolean.parseBoolean(environmentConfig.getSwagger_enable()))
//	        .groupName("test")
	        .genericModelSubstitutes(DeferredResult.class)
	        .useDefaultResponseMessages(false)
	        .forCodeGeneration(true)
    		.pathMapping("/")// base，最终调用接口后会和paths拼接在一起
//	        .pathMapping(pathMapping) // base，最终调用接口后会和paths拼接在一起
	        .select()
	        .apis(RequestHandlerSelectors.basePackage("com.opengroup.longmao.gwcommon.controller"))
	        .paths(PathSelectors.regex("/test.*"))//过滤的接口
//    		.paths(or(regex("/.*")))//过滤的接口
	        .build()
	        .apiInfo(apiInfo());
    }

    /**
     * 设置过滤规则
     * 这里的过滤规则支持正则匹配
     * @return
     */
    private Predicate<String> doFilteringRules() {
        return or(
                regex("/hello.*"),
                regex("/vehicles.*")
        );
    }

}