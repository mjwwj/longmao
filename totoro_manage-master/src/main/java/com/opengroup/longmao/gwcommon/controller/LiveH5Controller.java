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
import com.opengroup.longmao.gwcommon.entity.po.LiveH5;
import com.opengroup.longmao.gwcommon.entity.vo.LiveH5VO;
import com.opengroup.longmao.gwcommon.service.LiveH5Service;
import com.opengroup.longmao.gwcommon.tools.result.CommConstant;
import com.opengroup.longmao.gwcommon.tools.result.RetResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 【直播间H5活动入口】 控制类
 *
 * @version 1.0
 * @author Hermit 2017年05月12日 下午16:09:19
 */ 
@Controller
@EnableSwagger2
@Api(value= "直播间H5活动入口",tags="liveH5")
public class LiveH5Controller {

	@Autowired
	private LiveH5Service liveH5Service;

	
	/**
	 * 
	 * 直播间H5活动入口管理页面
	 * 
	 * @author zengjq 2017年4月13日
	 * @return
	 */
	@RequestMapping("/liveH5")
	public String banner() {
		GwsLogger.info("直播间H5活动入口管理页面");
		return "liveH5/liveH5";
	}
	
	/**
	* 【保存直播间H5活动入口】
	* @param liveH5VO
	* @param request
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年05月12日 下午16:09:19
	*/ 
	@ApiOperation(value = "保存直播间H5活动入口",notes="保存直播间H5活动入口")
	@ApiImplicitParam(name ="liveH5VO",value ="详细实体liveH5", required = true, dataType ="LiveH5")
	@RequestMapping(value = "/saveLiveH5",method =RequestMethod.POST)
	@ResponseBody
	public RetResult saveLiveH5(@JsonParam LiveH5VO liveH5VO){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("直播间H5活动入口保存开始:code={},message={},liveH5VO",code,message,ToStringBuilder.reflectionToString(liveH5VO));
		LiveH5 liveH5 = null;
		try {
			if(liveH5VO!=null){
			   liveH5 = liveH5Service.saveLiveH5(liveH5VO);
			   GwsLogger.info("直播间H5活动入口保存成功:code={},message={},",code,message);
			}else{
			   code = CommConstant.GWSCOD0001;
			   message = CommConstant.GWSMSG0001;
			   GwsLogger.info("直播间H5活动入口保存失败:code={},message={},liveH5={}",code,message,ToStringBuilder.reflectionToString(liveH5VO));
			}
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("直播间H5活动入口保存异常:liveH5VO={},e={}",ToStringBuilder.reflectionToString(liveH5VO),e);
		}
		GwsLogger.info("直播间H5活动入口保存结束:code={},message={}",code,message);
		return RetResult.setRetDate(code, message, liveH5);
	}

	/**
	* 【根据id删除直播间H5活动入口】
	* @param id
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年05月12日 下午16:09:19
	*/ 
	@ApiOperation(value = "根据id删除直播间H5活动入口",notes="根据id删除直播间H5活动入口")
	@ApiImplicitParam(name ="id",value ="主键id", required = true, dataType ="Long")
	@RequestMapping(value = "/deleteLiveH5",method =RequestMethod.POST)
	@ResponseBody
	public RetResult deleteLiveH5(Long id){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("根据id删除直播间H5活动入口开始:code={},message={},id={}",code,message,id);
		//根据id删除直播间H5活动入口
		try {
			if(id!=null && id>0){
			   liveH5Service.deleteLiveH5(id);
			   GwsLogger.info("根据id删除直播间H5活动入口成功:code={},message={},id={}",code,message,id);
			}else{
			   code = CommConstant.GWSCOD0001;
			   message = CommConstant.GWSMSG0001;
			   GwsLogger.info("根据id删除直播间H5活动入口失败:code={},message={},id={}",code,message,id);
			}
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("根据id删除直播间H5活动入口异常:code={},message={},id={},e={}",code,message,id,e);
		}
		GwsLogger.info("根据id删除直播间H5活动入口结束:code={},message={},id={}",code,message,id);
		return RetResult.setRetDate(code, message, null);
	}

 /**
	* 【根据id查询直播间H5活动入口】
	* @param id
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年05月12日 下午16:09:19
	*/ 
	@ApiOperation(value = "根据id查询直播间H5活动入口",notes="根据id查询直播间H5活动入口")
	@ApiImplicitParam(name ="id",value ="主键id", required = true, dataType ="Long")
	@RequestMapping(value = "/findLiveH5",method =RequestMethod.POST)
	@ResponseBody
	public RetResult findLiveH5(Long id){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("根据id查询直播间H5活动入口开始:code={},message={},id={}",code,message,id);
		//根据id查询直播间H5活动入口
		LiveH5 liveH5 = null;
		try {
			liveH5 = liveH5Service.findLiveH5(id);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("根据id查询直播间H5活动入口异常:code={},message={},id={},e={}",code,message,id,e);
		}
		GwsLogger.info("根据id查询直播间H5活动入口结束:code={},message={}",code,message);
		return RetResult.setRetDate(code, message, liveH5);
	}

	/**
	* 【查询所有直播间H5活动入口】
	* @param liveH5VO
	* @param pageNo
	* @param pageSize
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年05月12日 下午16:09:19
	*/ 
	@ApiOperation(value = "查询所有直播间H5活动入口",notes="查询所有直播间H5活动入口")
	@ApiImplicitParams({
		@ApiImplicitParam(name ="liveH5VO",value ="详细实体liveH5", required = true, dataType ="LiveH5"),
		@ApiImplicitParam(name ="pageNo",value ="第几页", required = true, dataType ="Long"),
		@ApiImplicitParam(name ="pageSize",value ="每页条数", required = true, dataType ="String")
		})
	@RequestMapping(value = "/findAllLiveH5",method =RequestMethod.POST)
	@ResponseBody
	public RetResult findAllLiveH5(@JsonParam LiveH5 liveH5,Integer pageNo,Integer pageSize){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("查询所有直播间H5活动入口开始:pageNo={},pageSize={},liveH5",pageNo,pageSize,ToStringBuilder.reflectionToString(liveH5));
		//查询所有直播间H5活动入口
		Page<LiveH5> page = null;
		try {
			page = liveH5Service.findLiveH5(liveH5,pageNo-1,pageSize,CommConstant.SORT_FIELD_CTIME);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("查询所有直播间H5活动入口异常:code={},message={},liveH5={},pageNo={},pageSize={},softField={},e={}",code,message,ToStringBuilder.reflectionToString(liveH5),pageNo-1,pageSize,e);
		}
		GwsLogger.info("查询所有直播间H5活动入口结束:pageNo={},pageSize={},softField={}",pageNo-1,pageSize);
		return RetResult.setRetDate(code, message, page);
	}

	/**
	* 【修改直播间H5活动入口】
	* @param liveH5VO
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年05月12日 下午16:09:19
	*/ 
	@ApiOperation(value = "修改直播间H5活动入口",notes="修改直播间H5活动入口")
	@ApiImplicitParam(name ="liveH5VO",value ="详细实体liveH5", required = true, dataType ="LiveH5")
	@RequestMapping(value = "/updateLiveH5",method =RequestMethod.POST)
	@ResponseBody
	public RetResult updateLiveH5(@JsonParam LiveH5 liveH5){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("修改直播间H5活动入口开始:liveH5",ToStringBuilder.reflectionToString(liveH5));
		//修改直播间H5活动入口
		LiveH5 newLiveH5 = null;
		try {
			newLiveH5 = liveH5Service.updateLiveH5(liveH5);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("修改直播间H5活动入口异常:code={},message={},liveH5={},e={}",code,message,ToStringBuilder.reflectionToString(liveH5),e);
		}
		GwsLogger.info("修改直播间H5活动入口结束");
		return RetResult.setRetDate(code, message, newLiveH5);
	}
}

