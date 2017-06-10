package com.opengroup.longmao.gwcommon.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opengroup.longmao.gwcommon.configuration.annotation.JsonParam;
import com.opengroup.longmao.gwcommon.configuration.log.GwsLogger;
import com.opengroup.longmao.gwcommon.entity.po.TvActivity;
import com.opengroup.longmao.gwcommon.service.TvActivityService;
import com.opengroup.longmao.gwcommon.tools.result.CommConstant;
import com.opengroup.longmao.gwcommon.tools.result.RetResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 【活动配置表】 控制类
 *
 * @version 1.0
 * @author Hermit 2017年04月26日 上午11:11:50
 */ 
@Controller
@EnableSwagger2
@Api(value= "活动配置表",tags="tvActivity")
@RequestMapping(value = {"/tvActivity"})
public class TvActivityController {

	@Autowired
	private TvActivityService tvActivityService;
	
	
	/**
	 * 
	 * 进入活动管理页面
	 * 
	 * @author zengjq 2017年4月13日
	 * @return
	 */
	@RequestMapping("/activity")
	public String banner() {
		GwsLogger.info("进入activity管理页面");
		return "activity/activity";
	}

	/**
	* 【保存活动配置表】
	* @param tvActivity
	* @param request
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年04月26日 上午11:11:50
	*/ 
	@ApiOperation(value = "保存活动配置表",notes="保存活动配置表")
	@ApiImplicitParam(name ="tvActivity",value ="详细实体tvActivity", required = true, dataType ="TvActivity")
	@RequestMapping(value = "/saveTvActivity",method =RequestMethod.POST)
	public @ResponseBody RetResult saveTvActivity(@JsonParam TvActivity tvActivity,HttpServletRequest request){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("活动配置表保存开始:code={},message={},tvActivity",code,message,ToStringBuilder.reflectionToString(tvActivity));
		if (tvActivity == null) {
			GwsLogger.error("tvActivity不合法:code={},message={},guessInfo={}", CommConstant.GWSCOD0003,
					CommConstant.GWSMSG0003, ToStringBuilder.reflectionToString(tvActivity));
			return RetResult.setRetDate(CommConstant.GWSCOD0003, CommConstant.GWSMSG0003, null);
		}
		TvActivity newTvActivity=null;
		try {
			newTvActivity = tvActivityService.saveTvActivity(tvActivity);
			if(newTvActivity!=null){
			   GwsLogger.info("活动配置表保存成功:code={},message={},",code,message);
			}else{
			   code = CommConstant.GWSCOD0001;
			   message = CommConstant.GWSMSG0001;
			   GwsLogger.info("活动配置表保存失败:code={},message={},tvActivity={}",code,message,ToStringBuilder.reflectionToString(tvActivity));
			}
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("活动配置表保存异常:tvActivity={},e={}",ToStringBuilder.reflectionToString(tvActivity),e);
		}
		GwsLogger.info("活动配置表保存结束:code={},message={}",code,message);
		return RetResult.setRetDate(code, message, newTvActivity);
	}

	/**
	* 【根据id删除活动配置表】
	* @param id
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年04月26日 上午11:11:50
	*/ 
	@ApiOperation(value = "根据id删除活动配置表",notes="根据id删除活动配置表")
	@ApiImplicitParam(name ="id",value ="主键id", required = true, dataType ="Long")
	@RequestMapping(value = "/deleteTvActivitybyId",method =RequestMethod.POST)
	public @ResponseBody RetResult deleteTvActivitybyId(Long id){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("根据id删除活动配置表开始:code={},message={},id={}",code,message,id);
		if (id == null||id<0) {
			GwsLogger.error("参数不合法:code={},message={},id={}", CommConstant.GWSCOD0003,
					CommConstant.GWSMSG0003, id);
			return RetResult.setRetDate(CommConstant.GWSCOD0003, CommConstant.GWSMSG0003, null);
		}
		//根据id删除活动配置表
		try {
			   tvActivityService.deleteTvActivity(id);
			   GwsLogger.info("根据id删除活动配置表成功:code={},message={},id={}",code,message,id);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("根据id删除活动配置表异常:code={},message={},id={},e={}",code,message,id,e);
		}
		GwsLogger.info("根据id删除活动配置表结束:code={},message={},id={}",code,message,id);
		return RetResult.setRetDate(code, message, null);
	}

 /**
	* 【根据id查询活动配置表】
	* @param id
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年04月26日 上午11:11:50
	*/ 
	@ApiOperation(value = "根据id查询活动配置表",notes="根据id查询活动配置表")
	@ApiImplicitParam(name ="id",value ="主键id", required = true, dataType ="Long")
	@RequestMapping(value = "/findTvActivityById",method =RequestMethod.POST)
	public @ResponseBody RetResult findTvActivityById(Long id){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("根据id查询活动配置表开始:code={},message={},id={}",code,message,id);
		if (id == null||id<0) {
			GwsLogger.error("参数不合法:code={},message={},id={}", CommConstant.GWSCOD0003,
					CommConstant.GWSMSG0003, id);
			return RetResult.setRetDate(CommConstant.GWSCOD0003, CommConstant.GWSMSG0003, null);
		}
		//根据id查询活动配置表
		TvActivity tvActivity = null;
		try {
			tvActivity = tvActivityService.findTvActivity(id);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("根据id查询活动配置表异常:code={},message={},id={},e={}",code,message,id,e);
		}
		GwsLogger.info("根据id查询活动配置表结束:code={},message={}",code,message);
		return RetResult.setRetDate(code, message, tvActivity);
	}

	/**
	* 【查询所有活动配置表】
	* @param tvActivity
	* @param pageNo
	* @param pageSize
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年04月26日 上午11:11:50
	*/ 
	@ApiOperation(value = "查询所有活动配置表",notes="查询所有活动配置表")
	@ApiImplicitParams({
		@ApiImplicitParam(name ="tvActivity",value ="详细实体tvActivity", required = true, dataType ="TvActivity"),
		@ApiImplicitParam(name ="pageNo",value ="第几页", required = true, dataType ="Long"),
		@ApiImplicitParam(name ="pageSize",value ="每页条数", required = true, dataType ="String")
		})
	@RequestMapping(value = "/findTvActivity",method =RequestMethod.POST)
	public @ResponseBody RetResult findTvActivity(@JsonParam TvActivity tvActivity,Integer pageNo,Integer pageSize){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("查询所有活动配置表开始:pageNo={},pageSize={},tvActivity",pageNo,pageSize,ToStringBuilder.reflectionToString(tvActivity));
		//查询所有活动配置表
		Page<TvActivity> page = null;
		try {
			page = tvActivityService.findTvActivity(tvActivity,pageNo-1,pageSize,CommConstant.SORT_FIELD_CTIME);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("查询所有活动配置表异常:code={},message={},tvActivity={},pageNo={},pageSize={},softField={},e={}",code,message,ToStringBuilder.reflectionToString(tvActivity),pageNo-1,pageSize,e);
		}
		GwsLogger.info("查询所有活动配置表结束:pageNo={},pageSize={},softField={}",pageNo-1,pageSize);
		return RetResult.setRetDate(code, message, page);
	}

	/**
	* 【根据Id修改活动状态配置表】
	* @param tvActivity
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年04月26日 上午11:11:50
	*/ 
	@ApiOperation(value = "根据Id修改活动状态配置表",notes="根据Id修改活动状态配置表")
	@ApiImplicitParams({
		@ApiImplicitParam(name ="id",value ="活动activityId", required = true, dataType ="Long"),
		@ApiImplicitParam(name ="activityStatus",value ="状态 是否启用", required = true, dataType ="Short")})
	@RequestMapping(value = "/updateTvActivityById",method =RequestMethod.POST)
	public @ResponseBody RetResult updateTvActivityById(Long id,Short activityStatus){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("根据Id修改活动状态配置表开始:activityId={}",id);
		//根据Id修改活动状态配置表
		TvActivity tvActivity=null;
		try {
			tvActivity = tvActivityService.updateTvActivityById(id,activityStatus);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("根据Id修改活动状态配置表异常:code={},message={},activityId={},e={}",code,message,id,e);
		}
		GwsLogger.info("根据Id修改活动状态配置表结束");
		return RetResult.setRetDate(code, message, tvActivity);
	}
	
	
	/**
	* 【修改活动配置表】
	* @param tvActivity
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年04月26日 上午11:11:50
	*/ 
	@ApiOperation(value = "修改活动配置表",notes="修改活动配置表")
	@ApiImplicitParam(name ="tvActivity",value ="详细实体tvActivity", required = true, dataType ="TvActivity")
	@RequestMapping(value = "/updateTvActivity",method =RequestMethod.POST)
	public @ResponseBody RetResult updateTvActivity(@JsonParam TvActivity tvActivity){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("修改活动配置表开始:tvActivity",ToStringBuilder.reflectionToString(tvActivity));
		//修改活动配置表
		try {
			tvActivity = tvActivityService.updateTvActivity(tvActivity);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("修改活动配置表异常:code={},message={},tvActivity={},e={}",code,message,ToStringBuilder.reflectionToString(tvActivity),e);
		}
		GwsLogger.info("修改活动配置表结束");
		return RetResult.setRetDate(code, message, tvActivity);
	}
}

