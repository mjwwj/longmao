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
import com.opengroup.longmao.gwcommon.entity.po.OrderInfo;
import com.opengroup.longmao.gwcommon.service.OrderInfoService;
import com.opengroup.longmao.gwcommon.tools.result.CommConstant;
import com.opengroup.longmao.gwcommon.tools.result.RetResult;

import io.swagger.annotations.Api;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Api(value = "微信公众号充值流水Controller", tags = "manage")
@Controller
public class PublicSignalController {
	@Autowired
	private OrderInfoService orderInfoService;
	
	/**
	 * 进入微信公众号充值流水列表
	 * @return
	 */
	@RequestMapping("/publicSignalOrder")
	public String publicSignalOrders() {
		GwsLogger.info("进入微信公众号充值流水页面");
		return "publicSignalOrder/publicSignalOrder";
	}
	
	
	/**
	* 【根据id查询公众号订单流水】
	* @param id
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年05月16日 下午17:13:21
	*/ 
	@RequestMapping(value = "/findOrderInfoById",method =RequestMethod.POST)
	public @ResponseBody RetResult findOrderInfoById(Long id){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("根据id查询公众号订单流水开始:code={},message={},id={}",code,message,id);
		if (id == null||id<0) {
			GwsLogger.error("参数不合法:code={},message={},id={}", CommConstant.GWSCOD0003,
					CommConstant.GWSMSG0003, id);
			return RetResult.setRetDate(CommConstant.GWSCOD0003, CommConstant.GWSMSG0003, null);
		}
		//根据id查询公众号订单流水
		OrderInfo orderInfo = null;
		try {
			orderInfo = orderInfoService.findOrderInfo(id);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("根据id查询公众号订单流水异常:code={},message={},id={},e={}",code,message,id,e);
		}
		GwsLogger.info("根据id查询公众号订单流水结束:code={},message={}",code,message);
		return RetResult.setRetDate(code, message, orderInfo);
	}

	/**
	* 【查询所有公众号订单流水】
	* @param goodsInfoVO
	* @param pageNo
	* @param pageSize
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年05月16日 下午17:13:21
	*/ 
	@RequestMapping(value = "/findOrderInfo",method =RequestMethod.POST)
	public @ResponseBody RetResult findOrderInfo(@JsonParam OrderInfo orderInfo,Integer pageNo,Integer pageSize){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("查询所有公众号订单流水开始:pageNo={},pageSize={},goodsInfo",pageNo,pageSize,ToStringBuilder.reflectionToString(orderInfo));
		//查询所有公众号订单流水
		Page<OrderInfo> page = null;
		try {
			page = orderInfoService.findOrderInfo(orderInfo,pageNo-1,pageSize,CommConstant.SORT_FIELD_CTIME);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("查询所有公众号订单流水异常:code={},message={},goodsInfo={},pageNo={},pageSize={},softField={},e={}",code,message,ToStringBuilder.reflectionToString(orderInfo),pageNo-1,pageSize,e);
		}
		GwsLogger.info("查询所有公众号订单流水结束:pageNo={},pageSize={},softField={}",pageNo-1,pageSize);
		return RetResult.setRetDate(code, message, page);
	}
}
