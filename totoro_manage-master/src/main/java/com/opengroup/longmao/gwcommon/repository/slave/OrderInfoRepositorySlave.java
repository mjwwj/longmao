/**
 * @Title: OrderInfoRepositorySlave.java 
 * @Package com.opengroup.longmao.gwcommon.repository.slave 
 * @Description:
 * @author Mr.Zhu
 * @version V1.5
 */
package com.opengroup.longmao.gwcommon.repository.slave;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.opengroup.longmao.gwcommon.configuration.query.core.BaseRepository;
import com.opengroup.longmao.gwcommon.entity.po.OrderInfo;

/**
 * @ClassName: OrderInfoRepositorySlave 
 * @Description: 订单信息
 * @author Mr.Zhu
 */
public interface OrderInfoRepositorySlave extends BaseRepository<OrderInfo, Long> {
	
	/**
	 * @Title: getOrderInfoBychlOrderId 
	 * @Description: 渠道订单ID获取订单 
	 * @param chlOrderId
	 * @return OrderInfo
	 */
	@Query("SELECT o FROM OrderInfo o WHERE o.chlOrderId = ?1")
	OrderInfo getOrderInfoBychlOrderId(String chlOrderId);
	
	/**
	 * @Title: getOrderInfoByOrderId 
	 * @Description: 订单ID、买家ID获取订单 
	 * @param orderId
	 * @param buyerUid
	 * @return OrderInfo
	 */
	@Query("SELECT o FROM OrderInfo o WHERE o.orderId = ?1 AND o.buyerUid = ?2")
	OrderInfo getOrderInfoByOrderId(Long orderId, Long buyerUid);

	/**
	 * @Title: getOrderInfoListByBuyerUid 
	 * @Description: 用户ID、订单状态、发货状态获取订单 
	 * @param buyerUid
	 * @param orderStatus
	 * @param deliveryStatus
	 * @return List<OrderInfo>
	 */
	@Query("SELECT o FROM OrderInfo o WHERE o.buyerUid = ?1 AND o.orderStatus = ?2 AND o.deliveryStatus = ?3")
	List<OrderInfo> getOrderInfoListByBuyerUid(Long buyerUid, Short orderStatus, Short deliveryStatus);
	
	@Query("SELECT o FROM OrderInfo o WHERE o.sellerUid = ?1 AND o.chlId = ?2")
	List<OrderInfo> getOrderInfoList(Long sellerUid, Long chlId);
	
	@Query("SELECT SUM(o.orderPrice) AS orderPrice FROM OrderInfo o WHERE o.buyerUid = ?1 AND o.sellerUid = ?2 AND o.chlId = ?3 AND o.orderStatus = ?4 AND o.deliveryStatus = ?5")
	BigDecimal getSumOrderPrice(Long buyerUid, Long sellerUid, Long chlId, Short orderStatus, Short deliveryStatus);
	
}
