/**
 * @Title: AnchorServiceImpl.java 
 * @Package com.opengroup.longmao.gwcommon.service.impl 
 * @Description:
 * @author Mr.Zhu
 * @version V1.5
 */
package com.opengroup.longmao.gwcommon.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.opengroup.longmao.gwcommon.configuration.log.GwsLogger;
import com.opengroup.longmao.gwcommon.configuration.redis.cache.CacheClientString;
import com.opengroup.longmao.gwcommon.configuration.redis.cache.IdGlobalGenerator;
import com.opengroup.longmao.gwcommon.entity.dto.OrderInfoDTO;
import com.opengroup.longmao.gwcommon.entity.po.IdentityInfo;
import com.opengroup.longmao.gwcommon.entity.po.OrderInfo;
import com.opengroup.longmao.gwcommon.entity.po.OrderTradeFlow;
import com.opengroup.longmao.gwcommon.entity.po.User;
import com.opengroup.longmao.gwcommon.entity.vo.AnchorVO;
import com.opengroup.longmao.gwcommon.entity.vo.OrderInfoVO;
import com.opengroup.longmao.gwcommon.enums.AnchorStatusEnum;
import com.opengroup.longmao.gwcommon.enums.ChlInfoEnum;
import com.opengroup.longmao.gwcommon.enums.CreditGradeEnum;
import com.opengroup.longmao.gwcommon.enums.DeliveryStatusEnum;
import com.opengroup.longmao.gwcommon.enums.ExchangeRateEnum;
import com.opengroup.longmao.gwcommon.enums.OrderStatusEnum;
import com.opengroup.longmao.gwcommon.enums.UserTypeEnum;
import com.opengroup.longmao.gwcommon.repository.master.IdentityInfoRepositoryMaster;
import com.opengroup.longmao.gwcommon.repository.master.OrderInfoRepositoryMaster;
import com.opengroup.longmao.gwcommon.repository.master.UserRepositoryMaster;
import com.opengroup.longmao.gwcommon.repository.queryFilter.AnchorQueryFilter;
import com.opengroup.longmao.gwcommon.repository.queryFilter.OrderInfoQueryFilter;
import com.opengroup.longmao.gwcommon.repository.slave.IdentityInfoRepositorySlave;
import com.opengroup.longmao.gwcommon.repository.slave.OrderInfoRepositorySlave;
import com.opengroup.longmao.gwcommon.repository.slave.OrderTradeFlowRepositorySlave;
import com.opengroup.longmao.gwcommon.repository.slave.UserRepositorySlave;
import com.opengroup.longmao.gwcommon.service.AnchorService;
import com.opengroup.longmao.gwcommon.tools.date.DateUtil;
import com.opengroup.longmao.gwcommon.tools.result.CommConstant;
import com.opengroup.longmao.gwcommon.tools.result.ImplException;

/**
 * @ClassName: AnchorServiceImpl 
 * @Description: TODO
 * @author Mr.Zhu
 */
@Service
public class AnchorServiceImpl implements AnchorService {
	
	@Autowired
	private UserRepositoryMaster userRepositoryMaster;
	
	@Autowired
	private UserRepositorySlave userRepositorySlave;
	
	@Autowired
	private IdentityInfoRepositoryMaster identityInfoRepositoryMaster;
	
	@Autowired
	private IdentityInfoRepositorySlave identityInfoRepositorySlave;
	
	@Autowired
	private OrderInfoRepositoryMaster orderInfoRepositoryMaster;
	
	@Autowired
	private OrderInfoRepositorySlave orderInfoRepositorySlave;
	
	@Autowired
	private OrderTradeFlowRepositorySlave orderTradeFlowRepositorySlave;
	
	@Autowired
	private IdGlobalGenerator idGlobalGenerator;
	
	@Autowired
	private CacheClientString cacheString;
	
	@Override
	public Page<User> findAllUser(AnchorVO vo, Integer pageNo, Integer pageSize, String sortField) {
		// 组合查询语句
		AnchorQueryFilter query = new AnchorQueryFilter();
		if(null != vo){
			if(StringUtils.isNotBlank(vo.getUserType())){
				query.setUserType(Integer.valueOf(vo.getUserType()));
			}
			if(StringUtils.isNotBlank(vo.getUserId())){
				query.setUserId(vo.getUserId());
			}
			if(StringUtils.isNotBlank(vo.getGrade())){
				query.setGrade(Integer.valueOf(vo.getGrade()));
			}
		}
		//字段排序
		Sort sort = new Sort(Direction.DESC, sortField);
		// 分页
		Pageable page = new PageRequest(pageNo, pageSize, sort);
		// 查询分页数据
		Page<User> pageList = userRepositorySlave.findAll(query, page);
		return pageList;
	}
	
	@Override
	public Page<OrderInfo> findAllOrder(OrderInfoVO vo, Integer pageNo, Integer pageSize, String sortField) {
		OrderInfoQueryFilter query = new OrderInfoQueryFilter();
		
		if(null != vo){
			if(StringUtils.isNotBlank(vo.getUserIdVO())){
				query.setBuyerUid(Long.valueOf(vo.getUserIdVO()));
			}
			if(StringUtils.isNotBlank(vo.getStatusVO())){
				String status = vo.getStatusVO();
				if ("0".equals(status)) {
					query.setOrderStatus(OrderStatusEnum.FAIL.getType());
					query.setDeliveryStatus(DeliveryStatusEnum.DELIVERY_FAIL.getType());
				} else if ("2".equals(status)) {
					query.setOrderStatus(OrderStatusEnum.SUCCESS.getType());
					query.setDeliveryStatus(DeliveryStatusEnum.IS_DELIVERY.getType());
				} else {
					query.setOrderStatus(OrderStatusEnum.IS_PAY.getType());
					query.setDeliveryStatus(DeliveryStatusEnum.UN_DELIVERY.getType());
				}
			}
			if(StringUtils.isNotBlank(vo.getTimeC())){
				query.setCreateDate(vo.getTimeC().replace("-", "").replace("/", ""));
			}
		}
		query.setSellerUid(ChlInfoEnum.CHL_TOTORO_CALORIE.getType());
		query.setChlId(ChlInfoEnum.CHL_TOTORO_CALORIE.getType());
		
		// 字段排序
		Sort sort = new Sort(Direction.DESC, sortField);
		// 分页
		Pageable page = new PageRequest(pageNo, pageSize, sort);
		// 查询分页数据
		Page<OrderInfo> pageList = orderInfoRepositorySlave.findAll(query, page);
		if (CollectionUtils.isNotEmpty(pageList.getContent())) {
			List<OrderInfo> oL = pageList.getContent();
			for (OrderInfo o : oL) {
				o.setTimeC(DateUtil.timestampToDates(o.getCtime(), DateUtil.TIME_PATTON_DEFAULT));
				o.setTimeU(DateUtil.timestampToDates(o.getUtime(), DateUtil.TIME_PATTON_DEFAULT));
			}
		}
		return pageList;
	}
	
	/**
	 * 
	 * 【提现报表导出】
	 * 
	 * (non-Javadoc)
	 * @see com.opengroup.longmao.gwcommon.service.AnchorService#cashExprot(com.opengroup.longmao.gwcommon.entity.vo.OrderInfoVO, java.lang.String)
	 */
	@Override
	public List<OrderInfoDTO> cashExprot(OrderInfoVO vo, String sortField)throws IOException {
		
        OrderInfoQueryFilter query = new OrderInfoQueryFilter();
		//初始化用于储存最终有效数据
		List<OrderInfoDTO> exprotList = new ArrayList<OrderInfoDTO>();
		
		if(null != vo){
			if(StringUtils.isNotBlank(vo.getUserIdVO())){
				query.setBuyerUid(Long.valueOf(vo.getUserIdVO()));
			}
			if(StringUtils.isNotBlank(vo.getStatusVO())){
				String status = vo.getStatusVO();
				if ("0".equals(status)) {
					query.setOrderStatus(OrderStatusEnum.FAIL.getType());
					query.setDeliveryStatus(DeliveryStatusEnum.DELIVERY_FAIL.getType());
				} else if ("2".equals(status)) {
					query.setOrderStatus(OrderStatusEnum.SUCCESS.getType());
					query.setDeliveryStatus(DeliveryStatusEnum.IS_DELIVERY.getType());
				} else {
					query.setOrderStatus(OrderStatusEnum.IS_PAY.getType());
					query.setDeliveryStatus(DeliveryStatusEnum.UN_DELIVERY.getType());
				}
			}
			if(StringUtils.isNotBlank(vo.getTimeC())){
				query.setCreateDate(vo.getTimeC().replace("-", "").replace("/", ""));
			}
		}
		query.setSellerUid(ChlInfoEnum.CHL_TOTORO_CALORIE.getType());
		query.setChlId(ChlInfoEnum.CHL_TOTORO_CALORIE.getType());
		
		// 字段排序
		Sort sort = new Sort(Direction.DESC, sortField);
		
		// 查询数据
		List<OrderInfo> list = orderInfoRepositorySlave.findAll(query,sort);
		if (CollectionUtils.isNotEmpty(list)) {
			for (OrderInfo o : list) {
				OrderInfoDTO r =new OrderInfoDTO();
				r.setOrderId(o.getOrderId());
				r.setBuyerUid(o.getBuyerUid());
				r.setOrderPrice(o.getOrderPrice());
				r.setDividedPrice(o.getPayPrice().subtract(o.getOrderPrice()));
				r.setTimeStr(DateUtil.timestampToDates(o.getCtime(), DateUtil.TIME_PATTON_DEFAULT));
				r.setRemark(o.getRemark());
				r.setOrderStatusStr(OrderStatusEnum.getEnumByNumber(o.getOrderStatus()).getDesc());
				exprotList.add(r);
			}
		}
		return exprotList;
	}
	
	@Override
	public List<OrderInfo> findAllOrder(OrderInfoVO vo, String sortField) {
		OrderInfoQueryFilter query = new OrderInfoQueryFilter();
		
		if(null != vo){
			if(StringUtils.isNotBlank(vo.getUserIdVO())){
				query.setBuyerUid(Long.valueOf(vo.getUserIdVO()));
			}
			if(StringUtils.isNotBlank(vo.getStatusVO())){
				String status = vo.getStatusVO();
				if ("0".equals(status)) {
					query.setOrderStatus(OrderStatusEnum.FAIL.getType());
					query.setDeliveryStatus(DeliveryStatusEnum.DELIVERY_FAIL.getType());
				} else if ("2".equals(status)) {
					query.setOrderStatus(OrderStatusEnum.SUCCESS.getType());
					query.setDeliveryStatus(DeliveryStatusEnum.IS_DELIVERY.getType());
				} else {
					query.setOrderStatus(OrderStatusEnum.IS_PAY.getType());
					query.setDeliveryStatus(DeliveryStatusEnum.UN_DELIVERY.getType());
				}
			}
			if(StringUtils.isNotBlank(vo.getTimeC())){
				query.setCreateDate(vo.getTimeC().replace("-", "").replace("/", ""));
			}
		}
		query.setSellerUid(ChlInfoEnum.CHL_TOTORO_CALORIE.getType());
		query.setChlId(ChlInfoEnum.CHL_TOTORO_CALORIE.getType());
		
		// 字段排序
		Sort sort = new Sort(Direction.DESC, sortField);
		
		// 查询分页数据
		List<OrderInfo> list = orderInfoRepositorySlave.findAll(query, sort);
		if (CollectionUtils.isNotEmpty(list)) {
			for (OrderInfo o : list) {
				o.setTimeC(DateUtil.timestampToDates(o.getCtime(), DateUtil.TIME_PATTON_DEFAULT));
				o.setTimeU(DateUtil.timestampToDates(o.getUtime(), DateUtil.TIME_PATTON_DEFAULT));
			}
		}
		return list;
	}
	
	/**
	  * @Title: extractCash 
	  * @Description: 主播提现卡路里 
	  * @param map
	  * @return boolean
	  */
	@Override
	public boolean extractCash(Map<String, Object> param) {
		if (!param.containsKey("userId")) {
			throw new ImplException(CommConstant.GWSCOD0003, "用户ID为空");
		}
		String userId = param.get("userId").toString();
		String orderId = param.get("orderId").toString();
		String remark = param.get("remark").toString();
		
		OrderInfo info = getOrderInfoByOrderId(userId, orderId);
		if (null == info) {
			throw new ImplException(CommConstant.GWSCOD0003, "没有提现记录");
		}
		
		String status = param.get("status").toString();
		
		if ("success".equals(status)) {
			OrderInfoVO orderVO = new OrderInfoVO(info.getOrderId(), OrderStatusEnum.SUCCESS.getType(),
					DeliveryStatusEnum.IS_DELIVERY.getType(), new Date(), "", remark);
			OrderInfo o = updateOrderState(info, orderVO);
			return null != o ? true : false;
		} else {
			OrderInfoVO orderVO = new OrderInfoVO(info.getOrderId(), OrderStatusEnum.FAIL.getType(),
					DeliveryStatusEnum.DELIVERY_FAIL.getType(), new Date(), "", remark);
			OrderInfo o = updateOrderState(info, orderVO);
			User u = null; 
			if (null != o) {
				u = updateCalorie(userId, info.getPayPrice());
			}
			return null != u ? true : false;
		}
	}
	
	/**
	 * @Title: updateOrderState 
	 * @Description: 更新交易订单状态  
	 * @param orderInfo
	 * @param orderVO
	 * @return OrderInfo
	 */
	private OrderInfo updateOrderState(OrderInfo orderInfo, OrderInfoVO orderVO) {
		if (orderInfo == null) {
			orderInfo = getOrderInfoByOrderId(orderVO.getOrderIdVO().toString());
		}
		orderInfo.setOrderStatus(orderVO.getOrderStatusVO());// 订单状态
		orderInfo.setDeliveryStatus(orderVO.getDeliveryStatusVO());// 发货状态
		orderInfo.setUtime(DateUtil.currentSecond());
		if (StringUtils.isNotEmpty(orderVO.getRemarkVO())) {
			orderInfo.setRemark(orderVO.getRemarkVO());
		}
		if (orderVO.getPayTimeVO() != null) {
			String timestamp = orderVO.getPayTimeVO().getTime() / 1000 + "";
			orderInfo.setPayTime(Integer.valueOf(timestamp));
		}
		if (StringUtils.isNotEmpty(orderVO.getPayFlowNoVO())) {
			orderInfo.setPayFlowNo(orderVO.getPayFlowNoVO());
		}
		return orderInfoRepositoryMaster.save(orderInfo);
	}
	
	public User updateCalorie(String userId, BigDecimal balance) {
		User u = queryUserByUserId(userId);
		if (null != u) {
			BigDecimal calorie = new BigDecimal(u.getCalorie());
			BigDecimal deductCalorie = balance.divide(new BigDecimal(ExchangeRateEnum.CALORIE_RATE_RMB.getVal()));
			
			BigDecimal deduct = calorie.add(deductCalorie);
			u.setCalorie(deduct.longValue());
			u.setGmtModified(new Date());
			u.setGmtModifiedUser(userId);
			User user = userRepositoryMaster.save(u);
			if (null != user && 0 == deduct.compareTo(new BigDecimal(user.getCalorie()))) {
				return user;
			}
		}
		return null;
	}

	/**
	  * @Title: editIdentity 
	  * @Description: 新增、更新认证
	  * @param map
	  * @return boolean
	  */
	@Override
	public boolean editIdentity(AnchorVO vo) {
		IdentityInfo flag = null;
		String userId = vo.getUserId();
		Short anchorType = vo.getAnchorType();
		String alipayId = vo.getAlipayId();
		String realName = vo.getRealName();
		String idCard = vo.getIdCard();
		String mobile = vo.getMobile();
		String ratio = vo.getRatio();
		String remark = vo.getRemark();

		IdentityInfo info = queryIdentityByUserId(userId);
		if (null != info) {
			info.setUserId(userId);
			info.setAnchorType(anchorType);
			info.setAlipayId(alipayId);
			info.setRealName(realName);
			info.setIdCard(idCard);
			info.setMobile(mobile);
			info.setStatus(AnchorStatusEnum.ANCHOR_NO_PASS.getType());
			info.setRatio(ratio);
			info.setIsLive(AnchorStatusEnum.OPEN_LIVE.getType());
			info.setRemark(remark);
			info.setGmtModified(DateUtil.currentSecond());
			info.setGmtModifiedUser(userId);
			flag = identityInfoRepositoryMaster.save(info);
		} else {
			Long id = idGlobalGenerator.getSeqId(IdentityInfo.class);
			
			IdentityInfo identity = new IdentityInfo();
			identity.setId(id);
			identity.setUserId(userId);
			identity.setAnchorType(anchorType);
			identity.setAlipayId(alipayId);
			identity.setRealName(realName);
			identity.setIdCard(idCard);
			identity.setMobile(mobile);
			identity.setStatus(AnchorStatusEnum.ANCHOR_NO_PASS.getType());
			identity.setRatio("".equals(ratio) ? CreditGradeEnum.DEFAULT_GRADE.getRatio() : ratio);
			identity.setIsLive(AnchorStatusEnum.OPEN_LIVE.getType());
			identity.setRemark(remark);
			identity.setCreditGrade(CreditGradeEnum.DEFAULT_GRADE.getVal());
			identity.setCreditGradeExplain(CreditGradeEnum.DEFAULT_GRADE.getExplain());
			identity.setGmtCreate(DateUtil.currentSecond());
			identity.setGmtCreateUser(userId);
			flag = identityInfoRepositoryMaster.save(identity);
		}
		if (null != flag && AnchorStatusEnum.ANCHOR_NO_PASS.getType() == flag.getStatus()) {
			return editUser(userId, UserTypeEnum.ANCHOR.getVal());
		}
		return false;
	}
	
	/**
	 * @Title: cancelAnchor 
	 * @Description: 取消主播身份 
	 * @param userId
	 * @return boolean
	 */
	@Override
	public boolean cancelAnchor(String userId) {
		IdentityInfo info = queryIdentityByUserId(userId);
		if (null != info) {
			info.setStatus(AnchorStatusEnum.ANCHOR_PASS.getType());
			info.setIsLive(AnchorStatusEnum.SHUT_LIVE.getType());
			info.setGmtModified(DateUtil.currentSecond());
			info.setGmtModifiedUser(userId);
			info = identityInfoRepositoryMaster.save(info);
		}
		if (null != info && AnchorStatusEnum.ANCHOR_PASS.getType() == info.getStatus()) {
			return editUser(userId, UserTypeEnum.ORDINARY.getVal());
		}
		return false;
	}
	
	@Override
	public boolean editUser(String userId, Integer type) {
		User u = null;
		u = queryUserByUserId(userId);
		if (null != u) {
			u.setUserType(type);
			u.setGmtModified(new Date());
			u.setGmtModifiedUser(userId);
			u = userRepositoryMaster.save(u);
		}
		return null == u ? false : true;
	}
	
	@Override
	public boolean editIdentity(String userId, String ratio) {
		IdentityInfo flag = null;
		IdentityInfo info = queryIdentityByUserId(userId);
		if (null != info) {
			info.setRatio(ratio);
			info.setGmtModified(DateUtil.currentSecond());
			info.setGmtModifiedUser(userId);
			flag = identityInfoRepositoryMaster.save(info);
		}
		return null == flag ? false : true;
	}
	
	/**
	 * @Title: editIdentity 
	 * @Description: 主播是否开播/禁播 
	 * @param userId
	 * @param isLive
	 * @return boolean
	 */
	@Override
	public boolean editIdentity(String userId, Short isLive) {
		IdentityInfo identiy = null;
		IdentityInfo info = queryIdentityByUserId(userId);
		Boolean flag = false;
		
		if (null != info) {
			info.setIsLive(isLive);
			info.setGmtModified(DateUtil.currentSecond());
			info.setGmtModifiedUser(userId);
			identiy = identityInfoRepositoryMaster.save(info);
		}
		if (null != identiy) {
			flag = true;
			if (AnchorStatusEnum.SHUT_LIVE.getType() == identiy.getIsLive()) {
				String liveId = cacheString.get(CommConstant.LIVE_HEART_USER, identiy.getUserId());
				if (null != liveId) {
					cacheString.set(CommConstant.LIVE_HEART_ID, liveId.replace("\"", ""), "1", 120L);
				}
			}
		}
		return flag;
	}
	
	@Override
	public List<OrderInfo> queryExtractCashLogByUserId(String userId) {
		OrderInfoQueryFilter query = new OrderInfoQueryFilter();
		
		query.setBuyerUid(Long.valueOf(userId));
		query.setSellerUid(ChlInfoEnum.CHL_TOTORO_CALORIE.getType());
		query.setChlId(ChlInfoEnum.CHL_TOTORO_CALORIE.getType());
		
		//字段排序
		Sort sort = new Sort(Direction.DESC, "utime");
		
		List<OrderInfo> infoL = orderInfoRepositorySlave.findAll(query, sort);
		return infoL;
	}
	
	/**
	  * @Title: queryIdentityByUserId 
	  * @Description: 查询主播直播认证、转账提现信息 
	  * @param userId
	  * @return IdentityInfo
	  */
	@Override
	public IdentityInfo queryIdentityByUserId(String userId) {
		return identityInfoRepositorySlave.queryIdentityByUserId(userId);
	}
	
	/**
	 * @Title: queryUserByUserId 
	 * @Description: 根据userId查询用户信息表 
	 * @param userId
	 * @return User
	 */
	@Override
	public User queryUserByUserId(String userId) {
		return userRepositorySlave.queryUserByUserId(userId);
	}
	
	/**
	 * @Title: getOrderTradeFlowByOrderId 
	 * @Description: 订单流水ID获取订单流水 
	 * @param flowId
	 * @return OrderTradeFlow
	 */
	public OrderTradeFlow getOrderTradeFlowByOrderId(String flowId) {
		OrderTradeFlow orderFlow = null;
		if (StringUtils.isNotBlank(flowId)) {
			orderFlow = orderTradeFlowRepositorySlave.findOne(Long.valueOf(flowId));
		} else {
			GwsLogger.info("flowId不存在");
		}
		return orderFlow;
	}
	
	/**
	 * @Title: getOrderInfoByOrderId 
	 * @Description: 订单ID、买家ID获取订单 
	 * @param OrderId
	 * @return OrderInfo
	 */
	public OrderInfo getOrderInfoByOrderId(String userId, String OrderId) {
		OrderInfo order = null;
		if (StringUtils.isNotBlank(OrderId) && StringUtils.isNotBlank(userId)) {
			order = orderInfoRepositorySlave.getOrderInfoByOrderId(Long.valueOf(OrderId), Long.valueOf(userId));
		} else {
			GwsLogger.info("ID不存在");
		}
		return order;
	}
	
	/**
	 * @Title: getOrderInfoByOrderId 
	 * @Description: 订单ID获取订单 
	 * @param OrderId
	 * @return OrderInfo
	 */
	public OrderInfo getOrderInfoByOrderId(String OrderId) {
		OrderInfo order = null;
		if (StringUtils.isNotBlank(OrderId)) {
			order = orderInfoRepositorySlave.findOne(Long.valueOf(OrderId));
		} else {
			GwsLogger.info("chlOrderId不存在");
		}
		return order;
	}
	
}
