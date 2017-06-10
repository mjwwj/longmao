package com.opengroup.longmao.gwcommon.controller;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opengroup.longmao.gwcommon.configuration.annotation.JsonParam;
import com.opengroup.longmao.gwcommon.configuration.log.GwsLogger;
import com.opengroup.longmao.gwcommon.entity.po.TvIllegalReport;
import com.opengroup.longmao.gwcommon.service.TvIllegalReportService;
import com.opengroup.longmao.gwcommon.tools.result.CommConstant;
import com.opengroup.longmao.gwcommon.tools.result.RetResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 【举报情况表】 控制类
 *
 * @version 1.0
 * @author Hermit 2017年04月25日 上午11:08:03
 */ 
@Controller
@EnableSwagger2
@Api(value= "举报情况表",tags="tvIllegalReport")
public class TvIllegalReportController {

	@Autowired
	private TvIllegalReportService tvIllegalReportService;
	
//	手里有现金吗
	/**
	 * 
	 * 进入意见反馈管理页面
	 * 
	 * @author zengjq 2017年4月13日
	 * @return
	 */
	@RequestMapping("/illegalReport")
	public String banner() {
		GwsLogger.info("进入illegalReport管理页面");
		return "illegalReport/illegalReport";
	}


 /**
	* 【根据id查询举报情况表】
	* @param id
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年04月25日 上午11:08:03
	*/ 
	@ApiOperation(value = "根据id查询举报情况表",notes="根据id查询举报情况表")
	@ApiImplicitParam(name ="id",value ="主键id", required = true, dataType ="Long")
	@RequestMapping(value = "/findTvIllegalReportById",method =RequestMethod.POST)
	public @ResponseBody RetResult findTvIllegalReportById(Long id){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("根据id查询举报情况表开始:code={},message={},id={}",code,message,id);
		//根据id查询举报情况表
		TvIllegalReport tvIllegalReport = null;
		try {
			tvIllegalReport = tvIllegalReportService.findTvIllegalReport(id);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("根据id查询举报情况表异常:code={},message={},id={},e={}",code,message,id,e);
		}
		GwsLogger.info("根据id查询举报情况表结束:code={},message={}",code,message);
		return RetResult.setRetDate(code, message, tvIllegalReport);
	}

	/**
	* 【查询所有举报情况表】
	* @param tvIllegalReport
	* @param pageNo
	* @param pageSize
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年04月25日 上午11:08:03
	*/ 
	@ApiOperation(value = "查询所有举报情况表",notes="查询所有举报情况表")
	@ApiImplicitParams({
		@ApiImplicitParam(name ="tvIllegalReport",value ="详细实体tvIllegalReport", required = true, dataType ="TvIllegalReport"),
		@ApiImplicitParam(name ="pageNo",value ="第几页", required = true, dataType ="Long"),
		@ApiImplicitParam(name ="pageSize",value ="每页条数", required = true, dataType ="String")
		})
	@RequestMapping(value = "/findTvIllegalReport",method =RequestMethod.POST)
	public @ResponseBody RetResult findTvIllegalReport(@JsonParam TvIllegalReport tvIllegalReport,Integer pageNo,Integer pageSize){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("查询所有举报情况表开始:pageNo={},pageSize={},tvIllegalReport",pageNo,pageSize,ToStringBuilder.reflectionToString(tvIllegalReport));
		//查询所有举报情况表
		Page<TvIllegalReport> page = null;
		try {
			page = tvIllegalReportService.findTvIllegalReport(tvIllegalReport,pageNo-1,pageSize,CommConstant.SORT_FIELD_CTIME);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("查询所有举报情况表异常:code={},message={},tvIllegalReport={},pageNo={},pageSize={},softField={},e={}",code,message,ToStringBuilder.reflectionToString(tvIllegalReport),pageNo-1,pageSize,e);
		}
		GwsLogger.info("查询所有举报情况表结束:pageNo={},pageSize={},softField={}",pageNo-1,pageSize);
		return RetResult.setRetDate(code, message, page);
	}

	/**
	* 【修改举报情况表】
	* @param tvIllegalReport
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年04月25日 上午11:08:03
	*/ 
	@ApiOperation(value = "修改举报情况表",notes="修改举报情况表")
	@ApiImplicitParam(name ="tvIllegalReport",value ="详细实体tvIllegalReport", required = true, dataType ="TvIllegalReport")
	@RequestMapping(value = "/updateTvIllegalReport",method =RequestMethod.POST)
	public @ResponseBody RetResult updateTvIllegalReport(@JsonParam TvIllegalReport tvIllegalReport){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("修改举报情况表开始:tvIllegalReport",ToStringBuilder.reflectionToString(tvIllegalReport));
		if (tvIllegalReport == null) {
			GwsLogger.error("tvIllegalReport不合法:code={},message={},guessInfo={}", CommConstant.GWSCOD0003,
					CommConstant.GWSMSG0003, ToStringBuilder.reflectionToString(tvIllegalReport));
			return RetResult.setRetDate(CommConstant.GWSCOD0003, CommConstant.GWSMSG0003, null);
		}
		//修改举报情况表
		try {
			tvIllegalReport = tvIllegalReportService.updateTvIllegalReport(tvIllegalReport);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("修改举报情况表异常:code={},message={},tvIllegalReport={},e={}",code,message,ToStringBuilder.reflectionToString(tvIllegalReport),e);
		}
		GwsLogger.info("修改举报情况表结束");
		return RetResult.setRetDate(code, message, tvIllegalReport);
	}
}

