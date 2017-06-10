package com.opengroup.longmao.gwcommon.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opengroup.longmao.gwcommon.configuration.annotation.JsonParam;
import com.opengroup.longmao.gwcommon.configuration.log.GwsLogger;
import com.opengroup.longmao.gwcommon.entity.po.SysConfig;
import com.opengroup.longmao.gwcommon.service.SysConfigService;
import com.opengroup.longmao.gwcommon.tools.result.CommConstant;
import com.opengroup.longmao.gwcommon.tools.result.RetResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 【系统配置】 控制类
 *
 * @version 1.0
 * @author Hermit 2017年05月09日 下午12:16:09
 */ 
@Controller
@EnableSwagger2
@Api(value= "系统配置",tags="sysConfig")
public class SysConfigController {

	@Autowired
	private SysConfigService sysConfigService;

	/**
	 * 
	 * 进入系统配置页面
	 * 
	 * @author zengjq 2017年4月13日
	 * @return
	 */
	@RequestMapping("/sysconfig")
	public String sysconfig() {
		GwsLogger.info("进入系统配置页面");
		return "config/sysconfig";
	}
	
	/**
	* 【查询系统配置】
	* @param sysConfig
	* @param pageNo
	* @param pageSize
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年05月09日 下午12:16:09
	*/ 
	@ApiOperation(value = "查询系统配置", notes = "查询系统配置")
	@RequestMapping(value = "/findSysConfig", method = RequestMethod.POST)
	public @ResponseBody RetResult findSysConfig() {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("查询系统配置开始");
		// 查询所有系统配置
		List<SysConfig> sysConfig = new ArrayList<SysConfig>();
		try {
			sysConfig = sysConfigService.findSysConfig();
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("查询系统配置异常");
		}
		GwsLogger.info("查询系统配置结束");
		return RetResult.setRetDate(code, message, sysConfig);
	}

	/**
	* 【修改系统配置】
	* @param sysConfig
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年05月09日 下午12:16:09
	*/ 
	@ApiOperation(value = "修改系统配置",notes="修改系统配置")
	@ApiImplicitParams({ @ApiImplicitParam(name="sysId", value="系统配置ID", required=true, dataType="Long"),
			@ApiImplicitParam(name="sysKey", value="配置key", required=false, dataType="String"),
			@ApiImplicitParam(name="sysVal", value="配置val", required=false, dataType="String")
	})
	@RequestMapping(value = "/updateSysConfig",method =RequestMethod.POST)
	public @ResponseBody RetResult updateSysConfig(@RequestParam("sysId") Long sysId, String sysKey, String sysVal){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("修改系统配置开始:code={},message={},sysId={},sysKey={},sysVal={}", code, message, sysId, sysKey,
				sysVal);
		//修改系统配置
		boolean flag = false;
		try {
			flag = sysConfigService.updateSysConfig(sysId, sysKey, sysVal);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("修改系统配置异常:code={},message={},sysId={},sysKey={},sysVal={},e={}", code, message, sysId,
					sysKey, sysVal, e);
		}
		GwsLogger.info("修改系统配置结束");
		return RetResult.setRetDate(code, message, flag);
	}
}

