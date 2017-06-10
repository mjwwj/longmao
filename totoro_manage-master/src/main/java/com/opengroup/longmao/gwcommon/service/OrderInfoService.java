package com.opengroup.longmao.gwcommon.service;

import org.springframework.data.domain.Page;

import com.opengroup.longmao.gwcommon.entity.po.GoodsInfo;
import com.opengroup.longmao.gwcommon.entity.po.OrderInfo;

/**
 * 
 * 【订单处理】
 *
 * @version 
 * @author liunan  2016年4月11日 上午11:47:00
 *
 */
public interface OrderInfoService {
	
	/**
	 *【查询微信公众号订单流水表】 
	 * @param id
	 * @return
	 */
	OrderInfo findOrderInfo(Long id);
	
	/**
	 * 【查询微信公众号订单流水表】
	 * @param orderInfo
	 * @param pageNo
	 * @param pageSize
	 * @param sortField
	 * @return
	 */
	Page<OrderInfo> findOrderInfo(OrderInfo orderInfo,Integer pageNo,Integer pageSize,String sortField);
}
