package com.opengroup.longmao.gwcommon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.opengroup.longmao.gwcommon.configuration.log.GwsLogger;

import io.swagger.annotations.Api;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Api(value = "main默认页面", tags = "main")
@Controller
public class MainController {
	
	/**
	 * 
	 * 进入系统默认页面
	 * 
	 * @author zengjq 2017年4月15日
	 * @return
	 */
	@RequestMapping("/main")
	public String getMonitorInfo() {
		GwsLogger.info("进入系统默认页面");
		return "main";
	}

}