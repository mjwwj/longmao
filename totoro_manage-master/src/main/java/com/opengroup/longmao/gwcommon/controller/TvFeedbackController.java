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
import com.opengroup.longmao.gwcommon.entity.po.TvFeedback;
import com.opengroup.longmao.gwcommon.service.TvFeedbackService;
import com.opengroup.longmao.gwcommon.tools.result.CommConstant;
import com.opengroup.longmao.gwcommon.tools.result.RetResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 【反馈意见表】 控制类
 *
 * @version 1.0
 * @author Hermit 2017年04月25日 上午11:11:47
 */ 
@Controller
@EnableSwagger2
@Api(value= "反馈意见表",tags="tvFeedback")
public class TvFeedbackController {

	@Autowired
	private TvFeedbackService tvFeedbackService;
	
	/**
	 * 
	 * 进入意见反馈管理页面
	 * 
	 * @author zengjq 2017年4月13日
	 * @return
	 */
	@RequestMapping("/feedback")
	public String banner() {
		GwsLogger.info("进入feedback管理页面");
		return "feedback/feedback";
	}


 /**
	* 【根据id查询反馈意见表】
	* @param id
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年04月25日 上午11:11:47
	*/ 
	@ApiOperation(value = "根据id查询反馈意见表",notes="根据id查询反馈意见表")
	@ApiImplicitParam(name ="id",value ="主键id", required = true, dataType ="Long")
	@RequestMapping(value = "/findTvFeedbackById",method =RequestMethod.POST)
	public @ResponseBody RetResult findTvFeedbackById(Long id){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("根据id查询反馈意见表开始:code={},message={},id={}",code,message,id);
		//根据id查询反馈意见表
		TvFeedback tvFeedback = null;
		try {
			tvFeedback = tvFeedbackService.findTvFeedback(id);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("根据id查询反馈意见表异常:code={},message={},id={},e={}",code,message,id,e);
		}
		GwsLogger.info("根据id查询反馈意见表结束:code={},message={}",code,message);
		return RetResult.setRetDate(code, message, tvFeedback);
	}

	/**
	* 【查询所有反馈意见表】
	* @param tvFeedback
	* @param pageNo
	* @param pageSize
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年04月25日 上午11:11:47
	*/ 
	@ApiOperation(value = "查询所有反馈意见表",notes="查询所有反馈意见表")
	@ApiImplicitParams({
		@ApiImplicitParam(name ="tvFeedback",value ="详细实体tvFeedback", required = true, dataType ="TvFeedback"),
		@ApiImplicitParam(name ="pageNo",value ="第几页", required = true, dataType ="Integer"),
		@ApiImplicitParam(name ="pageSize",value ="每页条数", required = true, dataType ="Integer")
		})
	@RequestMapping(value = "/findTvFeedback",method =RequestMethod.POST)
	public @ResponseBody RetResult findTvFeedback(@JsonParam TvFeedback tvFeedback,Integer pageNo,Integer pageSize){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("查询所有反馈意见表开始:pageNo={},pageSize={},tvFeedback",pageNo,pageSize,ToStringBuilder.reflectionToString(tvFeedback));
		//查询所有反馈意见表
		Page<TvFeedback> page = null;
		try {
			page = tvFeedbackService.findTvFeedback(tvFeedback,pageNo-1,pageSize,CommConstant.SORT_FIELD_CTIME);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("查询所有反馈意见表异常:code={},message={},tvFeedback={},pageNo={},pageSize={},softField={},e={}",code,message,ToStringBuilder.reflectionToString(tvFeedback),pageNo-1,pageSize,e);
		}
		GwsLogger.info("查询所有反馈意见表结束:pageNo={},pageSize={},softField={}",pageNo-1,pageSize);
		return RetResult.setRetDate(code, message, page);
	}

	/**
	* 【修改反馈意见表】
	* @param tvFeedback
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年04月25日 上午11:11:47
	*/ 
	@ApiOperation(value = "修改反馈意见表",notes="修改反馈意见表")
	@ApiImplicitParam(name ="tvFeedback",value ="详细实体tvFeedback", required = true, dataType ="TvFeedback")
	@RequestMapping(value = "/updateTvFeedback",method =RequestMethod.POST)
	public @ResponseBody RetResult updateTvFeedback(@JsonParam TvFeedback tvFeedback){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("修改反馈意见表开始:tvFeedback",ToStringBuilder.reflectionToString(tvFeedback));
		if (tvFeedback == null) {
			GwsLogger.error("tvFeedback不合法:code={},message={},guessInfo={}", CommConstant.GWSCOD0003,
					CommConstant.GWSMSG0003, ToStringBuilder.reflectionToString(tvFeedback));
			return RetResult.setRetDate(CommConstant.GWSCOD0003, CommConstant.GWSMSG0003, null);
		}
		//修改反馈意见表
		try {
			tvFeedback = tvFeedbackService.updateTvFeedback(tvFeedback);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("修改反馈意见表异常:code={},message={},tvFeedback={},e={}",code,message,ToStringBuilder.reflectionToString(tvFeedback),e);
		}
		GwsLogger.info("修改反馈意见表结束");
		return RetResult.setRetDate(code, message, tvFeedback);
	}
}

