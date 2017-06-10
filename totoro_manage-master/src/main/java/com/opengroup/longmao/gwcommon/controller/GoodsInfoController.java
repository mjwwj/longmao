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
import com.opengroup.longmao.gwcommon.entity.po.GoodsInfo;
import com.opengroup.longmao.gwcommon.service.GoodsInfoService;
import com.opengroup.longmao.gwcommon.tools.result.CommConstant;
import com.opengroup.longmao.gwcommon.tools.result.RetResult;

import io.swagger.annotations.Api;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 【商品信息表】 控制类
 *
 * @version 1.0
 * @author Hermit 2017年05月16日 下午17:13:21
 */ 
@Controller
@EnableSwagger2
@Api(value= "商品信息表",tags="goodsInfo")
//@RequestMapping(value = {"/goodsInfo"})
public class GoodsInfoController {

	@Autowired
	private GoodsInfoService goodsInfoService;
	
	
	/**
	 * 进入公众号商品列表
	 * @return
	 */
	@RequestMapping("/goodsInfo")
	public String goodsInfos() {
		GwsLogger.info("进入公众号商品列表页面");
		return "goodsInfo/goodsInfo";
	}

	/**
	* 【保存商品信息表】
	* @param goodsInfoVO
	* @param request
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年05月16日 下午17:13:21
	*/ 
//	@ApiOperation(value = "保存商品信息表",notes="保存商品信息表")
//	@ApiImplicitParams({
//		@ApiImplicitParam(name ="goodsInfo",value ="详细实体goodsInfo", required = true, dataType ="GoodsInfo"),
//		@ApiImplicitParam(name ="request",value ="HttpServletRequest", required = true, dataType ="HttpServletRequest") })
//	@RequestMapping(value = "/saveGoodsInfo",method =RequestMethod.POST)
//	public @ResponseBody RetResult saveGoodsInfo(@JsonParam GoodsInfo goodsInfo,HttpServletRequest request){
//		String code = CommConstant.GWSCOD0000;
//		String message = CommConstant.GWSMSG0000;
//		GwsLogger.info("商品信息表保存开始:code={},message={},goodsInfoVO",code,message,ToStringBuilder.reflectionToString(goodsInfo));
//		GoodsInfo newgoodsInfo = null;
//		try {
//			newgoodsInfo = goodsInfoService.saveGoodsInfo(goodsInfo);
//			if(null!=newgoodsInfo){
//			   GwsLogger.info("商品信息表保存成功:code={},message={},",code,message);
//			}else{
//			   code = CommConstant.GWSCOD0001;
//			   message = CommConstant.GWSMSG0001;
//			   GwsLogger.info("商品信息表保存失败:code={},message={},goodsInfo={}",code,message,ToStringBuilder.reflectionToString(goodsInfo));
//			}
//		} catch (Exception e) {
//			code = CommConstant.GWSCOD0001;
//			message = CommConstant.GWSMSG0001;
//			GwsLogger.error("商品信息表保存异常:goodsInfoVO={},e={}",ToStringBuilder.reflectionToString(goodsInfo),e);
//		}
//		GwsLogger.info("商品信息表保存结束:code={},message={}",code,message);
//		return RetResult.setRetDate(code, message, newgoodsInfo);
//	}

	/**
	* 【根据id删除商品信息表】
	* @param id
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年05月16日 下午17:13:21
	*/ 
	@RequestMapping(value = "/deleteGoodsInfoById",method =RequestMethod.POST)
	public @ResponseBody RetResult deleteGoodsInfoById(Long id){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("根据id删除商品信息表开始:code={},message={},id={}",code,message,id);
		if (id == null||id<0) {
			GwsLogger.error("参数不合法:code={},message={},id={}", CommConstant.GWSCOD0003,
					CommConstant.GWSMSG0003, id);
			return RetResult.setRetDate(CommConstant.GWSCOD0003, CommConstant.GWSMSG0003, null);
		}
		//根据id删除商品信息表
		try {
			   goodsInfoService.deleteGoodsInfo(id);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("根据id删除商品信息表异常:code={},message={},id={},e={}",code,message,id,e);
		}
		GwsLogger.info("根据id删除商品信息表结束:code={},message={},id={}",code,message,id);
		return RetResult.setRetDate(code, message, null);
	}

 /**
	* 【根据id查询商品信息表】
	* @param id
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年05月16日 下午17:13:21
	*/ 
	@RequestMapping(value = "/findGoodsInfoById",method =RequestMethod.POST)
	public @ResponseBody RetResult findGoodsInfoById(Long id){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("根据id查询商品信息表开始:code={},message={},id={}",code,message,id);
		if (id == null||id<0) {
			GwsLogger.error("参数不合法:code={},message={},id={}", CommConstant.GWSCOD0003,
					CommConstant.GWSMSG0003, id);
			return RetResult.setRetDate(CommConstant.GWSCOD0003, CommConstant.GWSMSG0003, null);
		}
		//根据id查询商品信息表
		GoodsInfo goodsInfo = null;
		try {
			goodsInfo = goodsInfoService.findGoodsInfo(id);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("根据id查询商品信息表异常:code={},message={},id={},e={}",code,message,id,e);
		}
		GwsLogger.info("根据id查询商品信息表结束:code={},message={}",code,message);
		return RetResult.setRetDate(code, message, goodsInfo);
	}

	/**
	* 【查询所有商品信息表】
	* @param goodsInfoVO
	* @param pageNo
	* @param pageSize
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年05月16日 下午17:13:21
	*/ 
	@RequestMapping(value = "/findGoodsInfo",method =RequestMethod.POST)
	public @ResponseBody RetResult findGoodsInfo(@JsonParam GoodsInfo goodsInfo,Integer pageNo,Integer pageSize){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("查询所有商品信息表开始:pageNo={},pageSize={},goodsInfo",pageNo,pageSize,ToStringBuilder.reflectionToString(goodsInfo));
		//查询所有商品信息表
		Page<GoodsInfo> page = null;
		try {
			page = goodsInfoService.findGoodsInfo(goodsInfo,pageNo-1,pageSize,CommConstant.SORT_FIELD_CTIME);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("查询所有商品信息表异常:code={},message={},goodsInfo={},pageNo={},pageSize={},softField={},e={}",code,message,ToStringBuilder.reflectionToString(goodsInfo),pageNo-1,pageSize,e);
		}
		GwsLogger.info("查询所有商品信息表结束:pageNo={},pageSize={},softField={}",pageNo-1,pageSize);
		return RetResult.setRetDate(code, message, page);
	}

	/**
	* 【修改商品信息表】
	* @param goodsInfoVO
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年05月16日 下午17:13:21
	*/ 
	@RequestMapping(value = "/updateGoodsInfo",method =RequestMethod.POST)
	public @ResponseBody RetResult updateGoodsInfo(@JsonParam GoodsInfo goodsInfo){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("修改商品信息表开始:goodsInfo",ToStringBuilder.reflectionToString(goodsInfo));
		if (goodsInfo.getGoodsId() == null||goodsInfo.getGoodsId()<0) {
			GwsLogger.error("参数不合法:code={},message={},id={}", CommConstant.GWSCOD0003,
					CommConstant.GWSMSG0003, goodsInfo.getGoodsId());
			return RetResult.setRetDate(CommConstant.GWSCOD0003, CommConstant.GWSMSG0003, null);
		}
		//修改商品信息表
		GoodsInfo newgoodsInfo = null;
		try {
			newgoodsInfo = goodsInfoService.updateGoodsInfo(goodsInfo);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("修改商品信息表异常:code={},message={},goodsInfo={},e={}",code,message,ToStringBuilder.reflectionToString(goodsInfo),e);
		}
		GwsLogger.info("修改商品信息表结束");
		return RetResult.setRetDate(code, message, newgoodsInfo);
	}
}

