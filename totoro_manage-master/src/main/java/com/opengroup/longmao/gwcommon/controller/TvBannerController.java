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
import com.opengroup.longmao.gwcommon.entity.po.TvBanner;
import com.opengroup.longmao.gwcommon.entity.vo.TvBannerVO;
import com.opengroup.longmao.gwcommon.service.TvBannerService;
import com.opengroup.longmao.gwcommon.tools.result.CommConstant;
import com.opengroup.longmao.gwcommon.tools.result.RetResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 【banner配置表】 控制类
 *
 * @version 1.0
 * @author Hermit 2017年04月26日 上午11:11:17
 */ 
@Controller
@EnableSwagger2
@Api(value= "banner配置表",tags="tvBanner")
public class TvBannerController {

	@Autowired
	private TvBannerService tvBannerService;
	
	/**
	 * 
	 * 进入banner管理页面
	 * 
	 * @author zengjq 2017年4月13日
	 * @return
	 */
	@RequestMapping("/banner")
	public String banner() {
		GwsLogger.info("进入banner管理页面");
		return "banner/banner";
	}
	
	/**
	* 【保存banner配置表】
	* @param tvBanner
	* @param request
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年04月26日 上午11:11:17
	*/ 
	@ApiOperation(value = "保存banner配置表",notes="保存banner配置表")
	@ApiImplicitParam(name ="tvBanner",value ="详细实体tvBanner", required = true, dataType ="TvBanner")
	@RequestMapping(value = "/saveTvBanner",method =RequestMethod.POST)
	public @ResponseBody RetResult saveTvBanner(@JsonParam TvBannerVO tvBannerVO){
				
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("banner配置表保存开始:code={},message={},tvBanner",code,message,ToStringBuilder.reflectionToString(tvBannerVO));
		if (tvBannerVO == null) {
			GwsLogger.error("tvBanner不合法:code={},message={},guessInfo={}", CommConstant.GWSCOD0003,CommConstant.GWSMSG0003, ToStringBuilder.reflectionToString(tvBannerVO));
			return RetResult.setRetDate(CommConstant.GWSCOD0003, CommConstant.GWSMSG0003, null);
		}
		TvBanner newTvBanner = null;
		try {
			newTvBanner = tvBannerService.saveTvBanner(tvBannerVO);
			if(newTvBanner!=null){
			   GwsLogger.info("banner配置表保存成功:code={},message={},",code,message);
			}else{
			   code = CommConstant.GWSCOD0001;
			   message = CommConstant.GWSMSG0001;
			   GwsLogger.info("banner配置表保存失败:code={},message={},tvBanner={}",code,message,ToStringBuilder.reflectionToString(tvBannerVO));
			}
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("banner配置表保存异常:tvBanner={},e={}",ToStringBuilder.reflectionToString(tvBannerVO),e);
		}
		GwsLogger.info("banner配置表保存结束:code={},message={}",code,message);
		return RetResult.setRetDate(code, message, newTvBanner);
	}
	
	/**
	* 【根据id删除banner配置表】
	* @param id
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年04月26日 上午11:11:17
	*/ 
	@ApiOperation(value = "根据id删除banner配置表",notes="根据id删除banner配置表")
	@ApiImplicitParam(name ="id",value ="主键id", required = true, dataType ="Long")
	@RequestMapping(value = "/deleteTvBanner",method =RequestMethod.POST)
	public @ResponseBody RetResult deleteTvBanner(Long id){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("根据id删除banner配置表开始:code={},message={},id={}",code,message,id);
		//根据id删除banner配置表
		if (id == null||id<0) {
			GwsLogger.error("参数不合法:code={},message={},id={}", CommConstant.GWSCOD0003,
					CommConstant.GWSMSG0003, id);
			return RetResult.setRetDate(CommConstant.GWSCOD0003, CommConstant.GWSMSG0003, null);
		}
		try {
			   tvBannerService.deleteTvBanner(id);
			   GwsLogger.info("根据id删除banner配置表成功:code={},message={},id={}",code,message,id);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("根据id删除banner配置表异常:code={},message={},id={},e={}",code,message,id,e);
		}
		GwsLogger.info("根据id删除banner配置表结束:code={},message={},id={}",code,message,id);
		return RetResult.setRetDate(code, message, null);
	}

 /**
	* 【根据id查询banner配置表】
	* @param id
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年04月26日 上午11:11:17
	*/ 
	@ApiOperation(value = "根据id查询banner配置表",notes="根据id查询banner配置表")
	@ApiImplicitParam(name ="id",value ="主键id", required = true, dataType ="Long")
	@RequestMapping(value = "/findTvBannerById",method =RequestMethod.POST)
	public @ResponseBody RetResult findTvBannerById(Long id){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("根据id查询banner配置表开始:code={},message={},id={}",code,message,id);
		if (id == null||id<0) {
			GwsLogger.error("参数不合法:code={},message={},id={}", CommConstant.GWSCOD0003,
					CommConstant.GWSMSG0003, id);
			return RetResult.setRetDate(CommConstant.GWSCOD0003, CommConstant.GWSMSG0003, null);
		}
		//根据id查询banner配置表
		TvBanner tvBanner = null;
		try {
			tvBanner = tvBannerService.findTvBanner(id);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("根据id查询banner配置表异常:code={},message={},id={},e={}",code,message,id,e);
		}
		GwsLogger.info("根据id查询banner配置表结束:code={},message={}",code,message);
		return RetResult.setRetDate(code, message, tvBanner);
	}

	/**
	* 【查询所有banner配置表】
	* @param tvBanner
	* @param pageNo
	* @param pageSize
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年04月26日 上午11:11:17
	*/ 
	@ApiOperation(value = "查询所有banner配置表",notes="查询所有banner配置表")
	@ApiImplicitParams({
		@ApiImplicitParam(name ="tvBanner",value ="详细实体tvBanner", required = true, dataType ="TvBanner"),
		@ApiImplicitParam(name ="pageNo",value ="第几页", required = true, dataType ="Long"),
		@ApiImplicitParam(name ="pageSize",value ="每页条数", required = true, dataType ="String")
		})
	@RequestMapping(value = "/findTvBanner",method =RequestMethod.POST)
	public @ResponseBody RetResult findTvBanner(@JsonParam TvBanner tvBanner,Integer pageNo,Integer pageSize){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("查询所有banner配置表开始:pageNo={},pageSize={},tvBanner",pageNo,pageSize,ToStringBuilder.reflectionToString(tvBanner));
		//查询所有banner配置表
		Page<TvBanner> page = null;
		try {
			page = tvBannerService.findTvBanner(tvBanner,pageNo-1,pageSize,CommConstant.SORT_FIELD_CTIME);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("查询所有banner配置表异常:code={},message={},tvBanner={},pageNo={},pageSize={},softField={},e={}",code,message,ToStringBuilder.reflectionToString(tvBanner),pageNo-1,pageSize,e);
		}
		GwsLogger.info("查询所有banner配置表结束:pageNo={},pageSize={},softField={}",pageNo-1,pageSize);
		return RetResult.setRetDate(code, message, page);
	}

	/**
	* 【修改banner配置表】
	* @param tvBanner
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年04月26日 上午11:11:17
	*/ 
	@ApiOperation(value = "修改banner配置表",notes="修改banner配置表")
	@ApiImplicitParam(name ="tvBanner",value ="详细实体tvBanner", required = true, dataType ="TvBanner")
	@RequestMapping(value = "/updateTvBanner",method =RequestMethod.POST)
	public @ResponseBody RetResult updateTvBanner(@JsonParam TvBanner tvBanner){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("修改banner配置表开始:tvBanner",ToStringBuilder.reflectionToString(tvBanner));
		//修改banner配置表
		try {
			tvBanner = tvBannerService.updateTvBanner(tvBanner);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("修改banner配置表异常:code={},message={},tvBanner={},e={}",code,message,ToStringBuilder.reflectionToString(tvBanner),e);
		}
		GwsLogger.info("修改banner配置表结束");
		return RetResult.setRetDate(code, message, tvBanner);
	}
}

