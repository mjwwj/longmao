package com.opengroup.longmao.gwcommon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;

import com.opengroup.longmao.gwcommon.configuration.log.GwsLogger;

@SpringBootApplication
@ServletComponentScan
@ImportResource({"classpath:conf/redisConfig.xml"})
public class TotoroManageApplication{ 
    
    public static void main(String[] args) {
      	SpringApplication app = new SpringApplication(TotoroManageApplication.class);
		app.setAddCommandLineProperties(false);//屏蔽命令行访问属性的设置
		app.run(args);
    	System.out.println("totoro manage server is started!");
    	GwsLogger.info("totoro manage server is started!");
    }
}

