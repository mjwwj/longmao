package com.opengroup.longmao.gwcommon.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opengroup.longmao.gwcommon.configuration.annotation.JsonParam;
import com.opengroup.longmao.gwcommon.configuration.log.GwsLogger;
import com.opengroup.longmao.gwcommon.entity.po.GoodsCategoryInfo;
import com.opengroup.longmao.gwcommon.service.GoodsCategoryInfoService;
import com.opengroup.longmao.gwcommon.tools.result.CommConstant;
import com.opengroup.longmao.gwcommon.tools.result.RetResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 【类别】 控制类
 *
 * @version 1.0
 * @author Hermit 2017年05月15日 下午16:35:03
 */ 
@Controller
@EnableSwagger2
@Api(value= "类别",tags="goodsCategoryInfo")
public class GoodsCategoryInfoController {

	@Autowired
	private GoodsCategoryInfoService goodsCategoryInfoService;

	/**
	 * 
	 * 类别管理页面
	 * 
	 * @author zengjq 2017年4月13日
	 * @return
	 */
	@RequestMapping("/categoryInfo")
	public String categoryInfo() {
		GwsLogger.info("类别管理页面");
		return "categoryInfo/categoryInfo";
	}
	
	/**
	* 【保存类别】
	* @param goodsCategoryInfo
	* @param request
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年05月15日 下午16:35:03
	*/ 
	@ApiOperation(value = "保存类别",notes="保存类别")
	@ApiImplicitParams({
		@ApiImplicitParam(name ="goodsCategoryInfo",value ="详细实体goodsCategoryInfo", required = true, dataType ="GoodsCategoryInfo"),
		@ApiImplicitParam(name ="request",value ="HttpServletRequest", required = true, dataType ="HttpServletRequest") })
	@RequestMapping(value = "/saveGoodsCategoryInfo",method =RequestMethod.POST)
	@ResponseBody
	public RetResult saveGoodsCategoryInfo(@JsonParam GoodsCategoryInfo goodsCategoryInfo,HttpServletRequest request){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("类别保存开始:code={},message={},goodsCategoryInfo",code,message,ToStringBuilder.reflectionToString(goodsCategoryInfo));
		GoodsCategoryInfo gci = null;
		try {
			if(goodsCategoryInfo!=null){
			   goodsCategoryInfo = goodsCategoryInfoService.saveGoodsCategoryInfo(goodsCategoryInfo);
			   GwsLogger.info("类别保存成功:code={},message={},",code,message);
			}else{
			   code = CommConstant.GWSCOD0001;
			   message = CommConstant.GWSMSG0001;
			   GwsLogger.info("类别保存失败:code={},message={},goodsCategoryInfo={}",code,message,ToStringBuilder.reflectionToString(goodsCategoryInfo));
			}
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("类别保存异常:goodsCategoryInfo={},e={}",ToStringBuilder.reflectionToString(goodsCategoryInfo),e);
		}
		GwsLogger.info("类别保存结束:code={},message={}",code,message);
		return RetResult.setRetDate(code, message, gci);
	}

	/**
	* 【根据id删除类别】
	* @param categoryId
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年05月15日 下午16:35:03
	*/ 
	@ApiOperation(value = "根据id删除类别",notes="根据id删除类别")
	@ApiImplicitParam(name ="categoryId",value ="主键categoryId", required = true, dataType ="Long")
	@RequestMapping(value = "/deleteGoodsCategoryInfo",method =RequestMethod.POST)
	@ResponseBody
	public RetResult deleteGoodsCategoryInfo(Long categoryId){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("根据id删除类别开始:code={},message={},categoryId={}",code,message,categoryId);
		//根据id删除类别
		try {
			if(StringUtils.isNotBlank(categoryId.toString())){
			   goodsCategoryInfoService.deleteGoodsCategoryInfo(categoryId);
			   GwsLogger.info("根据id删除类别成功:code={},message={},categoryId={}",code,message,categoryId);
			}else{
			   code = CommConstant.GWSCOD0001;
			   message = CommConstant.GWSMSG0001;
			   GwsLogger.info("根据id删除类别失败:code={},message={},categoryId={}",code,message,categoryId);
			}
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("根据id删除类别异常:code={},message={},categoryId={},e={}",code,message,categoryId,e);
		}
		GwsLogger.info("根据id删除类别结束:code={},message={},categoryId={}",code,message,categoryId);
		return RetResult.setRetDate(code, message, null);
	}

    /**
	* 【根据id查询类别】
	* @param categoryId
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年05月15日 下午16:35:03
	*/ 
	@ApiOperation(value = "根据categoryId查询类别",notes="根据categoryId查询类别")
	@ApiImplicitParam(name ="categoryId",value ="主键CategoryId", required = true, dataType ="Long")
	@RequestMapping(value = "/findGoodsCategoryInfo",method =RequestMethod.POST)
	@ResponseBody
	public RetResult findGoodsCategoryInfo(Long categoryId){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("根据CategoryId查询类别开始:code={},message={},categoryId={}",code,message,categoryId);
		//根据id查询类别
		GoodsCategoryInfo gci = null;
		try {
			 gci = goodsCategoryInfoService.findGoodsCategoryInfo(categoryId);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("根据CategoryId查询类别异常:code={},message={},categoryId={},e={}",code,message,categoryId,e);
		}
		GwsLogger.info("根据CategoryId查询类别结束:code={},message={}",code,message);
		return RetResult.setRetDate(code, message, gci);
	}

	/**
	* 【查询所有类别】
	* @param goodsCategoryInfo
	* @param pageNo
	* @param pageSize
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年05月15日 下午16:35:03
	*/ 
	@ApiOperation(value = "查询所有类别",notes="查询所有类别")
	@ApiImplicitParams({
		@ApiImplicitParam(name ="goodsCategoryInfo",value ="详细实体goodsCategoryInfo", required = true, dataType ="GoodsCategoryInfo"),
		@ApiImplicitParam(name ="pageNo",value ="第几页", required = true, dataType ="Long"),
		@ApiImplicitParam(name ="pageSize",value ="每页条数", required = true, dataType ="String")
		})
	@RequestMapping(value = "/findAllGoodsCategoryInfo",method =RequestMethod.POST)
	@ResponseBody
	public RetResult findAllGoodsCategoryInfo(@JsonParam GoodsCategoryInfo goodsCategoryInfo,Integer pageNo,Integer pageSize){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("查询所有类别开始:pageNo={},pageSize={},goodsCategoryInfo",pageNo,pageSize,ToStringBuilder.reflectionToString(goodsCategoryInfo));
		//查询所有类别
		Page<GoodsCategoryInfo> page = null;
		try {
			page = goodsCategoryInfoService.findGoodsCategoryInfo(goodsCategoryInfo,pageNo-1,pageSize,CommConstant.SORT_FIELD_CTIME);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("查询所有类别异常:code={},message={},goodsCategoryInfo={},pageNo={},pageSize={},softField={},e={}",code,message,ToStringBuilder.reflectionToString(goodsCategoryInfo),pageNo-1,pageSize,e);
		}
		GwsLogger.info("查询所有类别结束:pageNo={},pageSize={},softField={}",pageNo-1,pageSize);
		return RetResult.setRetDate(code, message, page);
	}

	/**
	* 【修改类别】
	* @param goodsCategoryInfo
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年05月15日 下午16:35:03
	*/ 
	@ApiOperation(value = "修改类别",notes="修改类别")
	@ApiImplicitParam(name ="goodsCategoryInfo",value ="详细实体goodsCategoryInfo", required = true, dataType ="GoodsCategoryInfo")
	@RequestMapping(value = "/updateGoodsCategoryInfo",method =RequestMethod.POST)
	@ResponseBody
	public RetResult updateGoodsCategoryInfo(@JsonParam GoodsCategoryInfo goodsCategoryInfo){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("修改类别开始:goodsCategoryInfo",ToStringBuilder.reflectionToString(goodsCategoryInfo));
		//修改类别
		GoodsCategoryInfo gci = null;
		try {
			gci = goodsCategoryInfoService.updateGoodsCategoryInfo(goodsCategoryInfo);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("修改类别异常:code={},message={},goodsCategoryInfo={},e={}",code,message,ToStringBuilder.reflectionToString(goodsCategoryInfo),e);
		}
		GwsLogger.info("修改类别结束");
		return RetResult.setRetDate(code, message, gci);
	}
}

