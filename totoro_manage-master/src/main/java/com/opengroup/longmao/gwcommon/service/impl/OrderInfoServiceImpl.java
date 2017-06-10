package com.opengroup.longmao.gwcommon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.opengroup.longmao.gwcommon.entity.po.OrderInfo;
import com.opengroup.longmao.gwcommon.enums.DeliveryStatusEnum;
import com.opengroup.longmao.gwcommon.enums.GoodsTypeEnum;
import com.opengroup.longmao.gwcommon.enums.OrderDiscountWayEnum;
import com.opengroup.longmao.gwcommon.enums.OrderStatusEnum;
import com.opengroup.longmao.gwcommon.enums.PayWayEnum;
import com.opengroup.longmao.gwcommon.enums.RefundStatusEnum;
import com.opengroup.longmao.gwcommon.repository.queryFilter.OrderInfoQueryFilter;
import com.opengroup.longmao.gwcommon.repository.slave.OrderInfoRepositorySlave;
import com.opengroup.longmao.gwcommon.service.OrderInfoService;
import com.opengroup.longmao.gwcommon.tools.date.DateUtil;

/**
 * 
 * 【订单增删改】
 *
 * @version
 * @author liunan 2016年4月18日 下午4:52:33
 *
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {

//	@Autowired
//	private OrderInfoRepositoryMaster orderInfoRepositoryMaster;

	@Autowired
	private OrderInfoRepositorySlave orderInfoRepositorySlave;

	//	@Autowired
//	private IdGlobalGenerator idGen;

	@Override
	public OrderInfo findOrderInfo(Long id) {
		OrderInfo orderInfo=orderInfoRepositorySlave.findOne(id);
		orderInfo.setTimeC(DateUtil.timestampToDates(orderInfo.getCtime(), DateUtil.TIME_PATTON_DEFAULT));
		orderInfo.setPayWayStr(orderInfo.getPayWay()==null?"":PayWayEnum.getEnumByNumber(orderInfo.getPayWay()).getDesc());
		orderInfo.setDiscountWayStr(orderInfo.getDiscountWay()==null?"":OrderDiscountWayEnum.getEnumByNumber(orderInfo.getDiscountWay()).getDesc());
		orderInfo.setDeliveryStatusStr(orderInfo.getDeliveryStatus()==null?"":DeliveryStatusEnum.getEnumByNumber(orderInfo.getDeliveryStatus()).getDesc());
		orderInfo.setOrderStatusStr(orderInfo.getOrderStatus()==null?"":OrderStatusEnum.getEnumByNumber(orderInfo.getOrderStatus()).getDesc());
		orderInfo.setRefundStatusStr(orderInfo.getRefundStatus()==null?"":RefundStatusEnum.getEnumByNumber(orderInfo.getRefundStatus()).getDesc());
		return orderInfo;
	}

	@Override
	public Page<OrderInfo> findOrderInfo(OrderInfo orderInfo, Integer pageNo, Integer pageSize, String sortField) {
		// 组合查询语句
		OrderInfoQueryFilter query = new OrderInfoQueryFilter();
		// query.setIsId(goodsInfo.getId());
		query.setGoodsTypeId(GoodsTypeEnum.WX_GTYPE_RECHARGE.getType());
		// 字段排序
		Sort sort = new Sort(Direction.DESC, sortField);
		// 分页
		Pageable page = new PageRequest(pageNo, pageSize, sort);
		// 查询分页数据
		Page<OrderInfo> pageList = orderInfoRepositorySlave.findAll(query, page);
		for (OrderInfo gi : pageList.getContent()) {
			gi.setTimeC(DateUtil.timestampToDates(gi.getCtime(), DateUtil.TIME_PATTON_DEFAULT));
			gi.setPayWayStr(gi.getPayWay()==null?"":PayWayEnum.getEnumByNumber(gi.getPayWay()).getDesc());
			gi.setDiscountWayStr(gi.getDiscountWay()==null?"":OrderDiscountWayEnum.getEnumByNumber(gi.getDiscountWay()).getDesc());
			gi.setDeliveryStatusStr(gi.getDeliveryStatus()==null?"":DeliveryStatusEnum.getEnumByNumber(gi.getDeliveryStatus()).getDesc());
			gi.setOrderStatusStr(gi.getOrderStatus()==null?"":OrderStatusEnum.getEnumByNumber(gi.getOrderStatus()).getDesc());
			orderInfo.setRefundStatusStr(orderInfo.getRefundStatus()==null?"":RefundStatusEnum.getEnumByNumber(orderInfo.getRefundStatus()).getDesc());
		}
		return pageList;
	}

}
