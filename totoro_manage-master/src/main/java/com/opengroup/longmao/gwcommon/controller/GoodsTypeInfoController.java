package com.opengroup.longmao.gwcommon.controller;

import java.util.List;

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
import com.opengroup.longmao.gwcommon.entity.po.GoodsTypeInfo;
import com.opengroup.longmao.gwcommon.service.GoodsTypeInfoService;
import com.opengroup.longmao.gwcommon.tools.result.CommConstant;
import com.opengroup.longmao.gwcommon.tools.result.RetResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 【类型】 控制类
 *
 * @version 1.0
 * @author Hermit 2017年05月15日 下午16:34:49
 */
@Controller
@EnableSwagger2
@Api(value = "类型", tags = "goodsTypeInfo")
public class GoodsTypeInfoController {

	@Autowired
	private GoodsTypeInfoService goodsTypeInfoService;

	/**
	 * 
	 * 类型管理页面
	 * 
	 * @author zengjq 2017年4月13日
	 * @return
	 */
	@RequestMapping("/typeInfo")
	public String typeInfo() {
		GwsLogger.info("类型管理页面");
		return "typeInfo/typeInfo";
	}

	/**
	 * 【保存类型】
	 * 
	 * @param goodsTypeInfo
	 * @param request
	 * @return RetResult
	 * @version 1.0
	 * @author Hermit 2017年05月15日 下午16:34:49
	 */
	@ApiOperation(value = "保存类型", notes = "保存类型")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "goodsTypeInfo", value = "详细实体goodsTypeInfo", required = true, dataType = "GoodsTypeInfo"),
			@ApiImplicitParam(name = "request", value = "HttpServletRequest", required = true, dataType = "HttpServletRequest") })
	@RequestMapping(value = "/saveGoodsTypeInfo", method = RequestMethod.POST)
	@ResponseBody
	public RetResult saveGoodsTypeInfo(@JsonParam GoodsTypeInfo goodsTypeInfo, HttpServletRequest request) {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("类型保存开始:code={},message={},goodsTypeInfo", code, message,
				ToStringBuilder.reflectionToString(goodsTypeInfo));
		GoodsTypeInfo gci = null;
		try {
			if (goodsTypeInfo != null) {
				gci = goodsTypeInfoService.saveGoodsTypeInfo(goodsTypeInfo);
				GwsLogger.info("类型保存成功:code={},message={},", code, message);
			} else {
				code = CommConstant.GWSCOD0001;
				message = CommConstant.GWSMSG0001;
				GwsLogger.info("类型保存失败:code={},message={},goodsTypeInfo={}", code, message,
						ToStringBuilder.reflectionToString(goodsTypeInfo));
			}
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("类型保存异常:goodsTypeInfo={},e={}", ToStringBuilder.reflectionToString(goodsTypeInfo), e);
		}
		GwsLogger.info("类型保存结束:code={},message={}", code, message);
		return RetResult.setRetDate(code, message, gci);
	}

	/**
	 * 【根据id删除类型】
	 * 
	 * @param goodsTypeId
	 * @return RetResult
	 * @version 1.0
	 * @author Hermit 2017年05月15日 下午16:34:49
	 */
	@ApiOperation(value = "根据goodsTypeId删除类型", notes = "根据id删除类型")
	@ApiImplicitParam(name = "goodsTypeId", value = "主键goodsTypeId", required = true, dataType = "Long")
	@RequestMapping(value = "/deleteGoodsTypeInfo", method = RequestMethod.POST)
	@ResponseBody
	public RetResult deleteGoodsTypeInfo(Long goodsTypeId) {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("根据goodsTypeId删除类型开始:code={},message={},goodsTypeId={}", code, message, goodsTypeId);
		// 根据id删除类型
		try {
			if (goodsTypeId != null) {
				goodsTypeInfoService.deleteGoodsTypeInfo(goodsTypeId);
				GwsLogger.info("根据goodsTypeId删除类型成功:code={},message={},goodsTypeId={}", code, message, goodsTypeId);
			} else {
				code = CommConstant.GWSCOD0001;
				message = CommConstant.GWSMSG0001;
				GwsLogger.info("根据goodsTypeId删除类型失败:code={},message={},goodsTypeId={}", code, message, goodsTypeId);
			}
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("根据goodsTypeId删除类型异常:code={},message={},goodsTypeId={},e={}", code, message, goodsTypeId,
					e);
		}
		GwsLogger.info("根据goodsTypeId删除类型结束:code={},message={},goodsTypeId={}", code, message, goodsTypeId);
		return RetResult.setRetDate(code, message, null);
	}

	/**
	 * 【根据goodsTypeId查询类型】
	 * 
	 * @param goodsTypeId
	 * @return RetResult
	 * @version 1.0
	 * @author Hermit 2017年05月15日 下午16:34:49
	 */
	@ApiOperation(value = "根据goodsTypeId查询类型", notes = "根据id查询类型")
	@ApiImplicitParam(name = "goodsTypeId", value = "goodsTypeId", required = true, dataType = "Long")
	@RequestMapping(value = "/findGoodsTypeInfo", method = RequestMethod.POST)
	@ResponseBody
	public RetResult findGoodsTypeInfo(Long goodsTypeId) {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("根据id查询类型开始:code={},message={},goodsTypeId={}", code, message, goodsTypeId);
		// 根据id查询类型
		GoodsTypeInfo goodsTypeInfo = null;
		try {
			goodsTypeInfo = goodsTypeInfoService.findGoodsTypeInfo(goodsTypeId);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("根据id查询类型异常:code={},message={},goodsTypeId={},e={}", code, message, goodsTypeId, e);
		}
		GwsLogger.info("根据id查询类型结束:code={},message={}", code, message);
		return RetResult.setRetDate(code, message, goodsTypeInfo);
	}

	/**
	 * 【查询所有类型】
	 * 
	 * @param goodsTypeInfo
	 * @param pageNo
	 * @param pageSize
	 * @return RetResult
	 * @version 1.0
	 * @author Hermit 2017年05月15日 下午16:34:49
	 */
	@ApiOperation(value = "查询所有类型", notes = "查询所有类型")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "goodsTypeInfo", value = "详细实体goodsTypeInfo", required = true, dataType = "GoodsTypeInfo"),
			@ApiImplicitParam(name = "pageNo", value = "第几页", required = true, dataType = "Long"),
			@ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, dataType = "String") })
	@RequestMapping(value = "/findAllGoodsTypeInfo", method = RequestMethod.POST)
	@ResponseBody
	public RetResult findAllGoodsTypeInfo(@JsonParam GoodsTypeInfo goodsTypeInfo, Integer pageNo, Integer pageSize) {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("查询所有类型开始:pageNo={},pageSize={},goodsTypeInfo", pageNo, pageSize,
				ToStringBuilder.reflectionToString(goodsTypeInfo));
		// 查询所有类型
		Page<GoodsTypeInfo> page = null;
		try {
			page = goodsTypeInfoService.findGoodsTypeInfo(goodsTypeInfo, pageNo - 1, pageSize,
					CommConstant.SORT_FIELD_CTIME);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("查询所有类型异常:code={},message={},goodsTypeInfo={},pageNo={},pageSize={},e={}",
					code, message, ToStringBuilder.reflectionToString(goodsTypeInfo), pageNo - 1, pageSize, e);
		}
		GwsLogger.info("查询所有类型结束:pageNo={},pageSize={},softField={}", pageNo - 1, pageSize);
		return RetResult.setRetDate(code, message, page);
	}

	/**
	 * 【修改类型】
	 * 
	 * @param goodsTypeInfoVO
	 * @return RetResult
	 * @version 1.0
	 * @author Hermit 2017年05月15日 下午16:34:49
	 */
	@ApiOperation(value = "修改类型", notes = "修改类型")
	@ApiImplicitParam(name = "goodsTypeInfoVO", value = "详细实体goodsTypeInfo", required = true, dataType = "GoodsTypeInfo")
	@RequestMapping(value = "/updateGoodsTypeInfo", method = RequestMethod.POST)
	@ResponseBody
	public RetResult updateGoodsTypeInfo(@JsonParam GoodsTypeInfo goodsTypeInfo) {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("修改类型开始:goodsTypeInfo", ToStringBuilder.reflectionToString(goodsTypeInfo));
		// 修改类型
		GoodsTypeInfo gti = null;
		try {
			gti = goodsTypeInfoService.updateGoodsTypeInfo(goodsTypeInfo);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("修改类型异常:code={},message={},goodsTypeInfo={},e={}", code, message,
					ToStringBuilder.reflectionToString(goodsTypeInfo), e);
		}
		GwsLogger.info("修改类型结束");
		return RetResult.setRetDate(code, message, gti);
	}
	
	/**
	 * 【查询所有类型】
	 * 
	 * @return RetResult
	 * @version 1.0
	 * @author Hermit 2017年05月15日 下午16:34:49
	 */
	@ApiOperation(value = "查询所有类型", notes = "查询所有类型")
	@RequestMapping(value = "/findAllGoodsTypeInfo2", method = RequestMethod.POST)
	@ResponseBody
	public RetResult findAllGoodsTypeInfo2() {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("查询所有类型开始");
		// 查询所有类型
		List<GoodsTypeInfo> list = null;
		try {
			list = goodsTypeInfoService.findGoodsTypeInfo(CommConstant.SORT_FIELD_CTIME);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("查询所有类型异常:code={},message={},e={}",code, message,  e);
		}
		GwsLogger.info("查询所有类型结束");
		return RetResult.setRetDate(code, message, list);
	}
}
