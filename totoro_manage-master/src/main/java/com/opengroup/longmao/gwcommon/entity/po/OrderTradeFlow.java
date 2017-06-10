package com.opengroup.longmao.gwcommon.entity.po;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 【交易订单流水表】 持久化对象
 *
 * @version
 * @author Hermit 2017年03月10日 上午10:43:35
 */ 
@Entity
@Table(name = "order_trade_flow") 
public class OrderTradeFlow implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "flow_id")
	private Long flowId;

	@Column(name = "order_id")
	private Long orderId;

	@Column(name = "pay_status")
	private Short payStatus;

	@Column(name = "pay_price")
	private BigDecimal payPrice;

	@Column(name = "pay_flow_no")
	private String payFlowNo;

	@Column(name = "create_date")
	private String createDate;

	@Column(name = "ctime")
	private Integer ctime;

	@Column(name = "utime")
	private Integer utime;

	@Column(name = "is_delete")
	private Short isDelete;

	public Long getFlowId(){
		return flowId;
	}

	public void setFlowId(Long flowId){
		this.flowId = flowId;
	}

	public Long getOrderId(){
		return orderId;
	}

	public void setOrderId(Long orderId){
		this.orderId = orderId;
	}

	public Short getPayStatus(){
		return payStatus;
	}

	public void setPayStatus(Short payStatus){
		this.payStatus = payStatus;
	}

	public BigDecimal getPayPrice(){
		return payPrice;
	}

	public void setPayPrice(BigDecimal payPrice){
		this.payPrice = payPrice;
	}

	public String getPayFlowNo(){
		return payFlowNo;
	}

	public void setPayFlowNo(String payFlowNo){
		this.payFlowNo = payFlowNo;
	}

	public String getCreateDate(){
		return createDate;
	}

	public void setCreateDate(String createDate){
		this.createDate = createDate;
	}

	public Integer getCtime(){
		return ctime;
	}

	public void setCtime(Integer ctime){
		this.ctime = ctime;
	}

	public Integer getUtime(){
		return utime;
	}

	public void setUtime(Integer utime){
		this.utime = utime;
	}

	public Short getIsDelete(){
		return isDelete;
	}

	public void setIsDelete(Short isDelete){
		this.isDelete = isDelete;
	}

}

