/**
 * @Title: OrderInfoVO.java 
 * @Package com.opengroup.longmao.gwcommon.entity.vo 
 * @Description:
 * @author Mr.Zhu
 * @version V1.5
 */
package com.opengroup.longmao.gwcommon.entity.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: OrderInfoVO
 * @Description: TODO
 * @author Mr.Zhu
 */
public class OrderInfoVO implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 4544850066591402612L;
	private String userIdVO;
	private String statusVO;
	private Long orderIdVO;
	private Long buyerUidVO;
	private Short orderStatusVO;
	private Short deliveryStatusVO;
	private Date payTimeVO;
	private String payFlowNoVO;
	private String remarkVO;
	private String timeC;
	private String timeU;
	
	//选择支付方式(1-支付宝,2-微信,3-银联,4-ios内支付,5-喵点,6-微信公众号支付)
	private String payWayStr;
	//订单优惠方式
	private String discountWayStr;
	//订单状态(1-待付款,2-已付款,待处理,3-处理中,待发货,4-交易成功,5-交易失败,6-退款)
	private String orderStatusStr;
	//发货状态(1-待发货,2-发货中,3-已发货,4-发货失败)
	private String deliveryStatusStr;
	//退款状态
	private String refundStatusStr;

	public OrderInfoVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderInfoVO(Long orderIdVO, Short orderStatusVO, Short deliveryStatusVO, String remarkVO) {
		super();
		this.orderIdVO = orderIdVO;
		this.orderStatusVO = orderStatusVO;
		this.deliveryStatusVO = deliveryStatusVO;
		this.remarkVO = remarkVO;
	}

	public OrderInfoVO(Long orderIdVO, Short orderStatusVO, Short deliveryStatusVO, Date payTimeVO, String payFlowNoVO,
			String remarkVO) {
		super();
		this.orderIdVO = orderIdVO;
		this.orderStatusVO = orderStatusVO;
		this.deliveryStatusVO = deliveryStatusVO;
		this.payTimeVO = payTimeVO;
		this.payFlowNoVO = payFlowNoVO;
		this.remarkVO = remarkVO;
	}

	public String getUserIdVO() {
		return userIdVO;
	}

	public void setUserIdVO(String userIdVO) {
		this.userIdVO = userIdVO;
	}

	public String getStatusVO() {
		return statusVO;
	}

	public void setStatusVO(String statusVO) {
		this.statusVO = statusVO;
	}

	public Long getOrderIdVO() {
		return orderIdVO;
	}

	public void setOrderIdVO(Long orderIdVO) {
		this.orderIdVO = orderIdVO;
	}

	public Long getBuyerUidVO() {
		return buyerUidVO;
	}

	public void setBuyerUidVO(Long buyerUidVO) {
		this.buyerUidVO = buyerUidVO;
	}

	public Short getOrderStatusVO() {
		return orderStatusVO;
	}

	public void setOrderStatusVO(Short orderStatusVO) {
		this.orderStatusVO = orderStatusVO;
	}

	public Short getDeliveryStatusVO() {
		return deliveryStatusVO;
	}

	public void setDeliveryStatusVO(Short deliveryStatusVO) {
		this.deliveryStatusVO = deliveryStatusVO;
	}

	public Date getPayTimeVO() {
		return payTimeVO;
	}

	public void setPayTimeVO(Date payTimeVO) {
		this.payTimeVO = payTimeVO;
	}

	public String getPayFlowNoVO() {
		return payFlowNoVO;
	}

	public void setPayFlowNoVO(String payFlowNoVO) {
		this.payFlowNoVO = payFlowNoVO;
	}

	public String getRemarkVO() {
		return remarkVO;
	}

	public void setRemarkVO(String remarkVO) {
		this.remarkVO = remarkVO;
	}

	public String getTimeC() {
		return timeC;
	}

	public void setTimeC(String timeC) {
		this.timeC = timeC;
	}

	public String getTimeU() {
		return timeU;
	}

	public void setTimeU(String timeU) {
		this.timeU = timeU;
	}

	public String getPayWayStr() {
		return payWayStr;
	}

	public void setPayWayStr(String payWayStr) {
		this.payWayStr = payWayStr;
	}

	public String getDiscountWayStr() {
		return discountWayStr;
	}

	public void setDiscountWayStr(String discountWayStr) {
		this.discountWayStr = discountWayStr;
	}

	public String getOrderStatusStr() {
		return orderStatusStr;
	}

	public void setOrderStatusStr(String orderStatusStr) {
		this.orderStatusStr = orderStatusStr;
	}

	public String getDeliveryStatusStr() {
		return deliveryStatusStr;
	}

	public void setDeliveryStatusStr(String deliveryStatusStr) {
		this.deliveryStatusStr = deliveryStatusStr;
	}

	public String getRefundStatusStr() {
		return refundStatusStr;
	}

	public void setRefundStatusStr(String refundStatusStr) {
		this.refundStatusStr = refundStatusStr;
	}
	
	

}
