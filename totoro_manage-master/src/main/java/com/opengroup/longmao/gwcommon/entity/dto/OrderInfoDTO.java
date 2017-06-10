package com.opengroup.longmao.gwcommon.entity.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderInfoDTO implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 4544850066591402612L;
	
	private Long orderId;
	private Long buyerUid;
	private BigDecimal orderPrice;
	private BigDecimal dividedPrice;
	private String timeStr;
	private String remark;
	private String orderStatusStr;
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getBuyerUid() {
		return buyerUid;
	}
	public void setBuyerUid(Long buyerUid) {
		this.buyerUid = buyerUid;
	}
	public BigDecimal getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}
	public BigDecimal getDividedPrice() {
		return dividedPrice;
	}
	public void setDividedPrice(BigDecimal dividedPrice) {
		this.dividedPrice = dividedPrice;
	}
	public String getTimeStr() {
		return timeStr;
	}
	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOrderStatusStr() {
		return orderStatusStr;
	}
	public void setOrderStatusStr(String orderStatusStr) {
		this.orderStatusStr = orderStatusStr;
	}

}
