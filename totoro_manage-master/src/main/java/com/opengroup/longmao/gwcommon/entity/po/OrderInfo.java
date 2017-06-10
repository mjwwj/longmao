package com.opengroup.longmao.gwcommon.entity.po;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.opengroup.longmao.gwcommon.entity.vo.OrderInfoVO;

/**
 * 【订单】 持久化对象
 *
 * @version
 * @author Hermit 2017年03月09日 上午11:02:33
 */ 
@Entity
@Table(name = "order_info") 
public class OrderInfo extends OrderInfoVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "order_id")
	private Long orderId;

	@Column(name = "buyer_uid")
	private Long buyerUid;

	@Column(name = "seller_uid")
	private Long sellerUid;

	@Column(name = "order_price")
	private BigDecimal orderPrice;

	@Column(name = "pay_price")
	private BigDecimal payPrice;

	@Column(name = "pay_way")
	private Short payWay;
	
	@Column(name = "discount_way")
	private Short discountWay;
	
	@Column(name = "integral")
	private Long integral;

	@Column(name = "order_status")
	private Short orderStatus;

	@Column(name = "delivery_status")
	private Short deliveryStatus;

	@Column(name = "create_date")
	private String createDate;

	@Column(name = "chl_id")
	private Long chlId;

	@Column(name = "chl_order_id")
	private String chlOrderId;

	@Column(name = "is_dummy")
	private Short isDummy;

	@Column(name = "pay_time")
	private Integer payTime;
	
	@Column(name = "pay_flow_no")
	private String payFlowNo;

	@Column(name = "goods_category_id")
	private Long goodsCategoryId;

	@Column(name = "goods_type_id")
	private Long goodsTypeId;

	@Column(name = "remark")
	private String remark;

	@Column(name = "order_end_time")
	private Integer orderEndTime;

	@Column(name = "ctime")
	private Integer ctime;

	@Column(name = "utime")
	private Integer utime;

	@Column(name = "refund_status")
	private Short refundStatus;

	@Column(name = "param")
	private String param;

	@Column(name = "is_delete")
	private Short isDelete;

	public Long getOrderId(){
		return orderId;
	}

	public void setOrderId(Long orderId){
		this.orderId = orderId;
	}

	public Long getBuyerUid(){
		return buyerUid;
	}

	public void setBuyerUid(Long buyerUid){
		this.buyerUid = buyerUid;
	}

	public Long getSellerUid(){
		return sellerUid;
	}

	public void setSellerUid(Long sellerUid){
		this.sellerUid = sellerUid;
	}

	public BigDecimal getOrderPrice(){
		return orderPrice;
	}

	public void setOrderPrice(BigDecimal orderPrice){
		this.orderPrice = orderPrice;
	}

	public BigDecimal getPayPrice(){
		return payPrice;
	}

	public void setPayPrice(BigDecimal payPrice){
		this.payPrice = payPrice;
	}

	public Long getIntegral(){
		return integral;
	}

	public void setIntegral(Long integral){
		this.integral = integral;
	}

	public Short getOrderStatus(){
		return orderStatus;
	}

	public void setOrderStatus(Short orderStatus){
		this.orderStatus = orderStatus;
	}

	public Short getDeliveryStatus(){
		return deliveryStatus;
	}

	public void setDeliveryStatus(Short deliveryStatus){
		this.deliveryStatus = deliveryStatus;
	}

	public String getCreateDate(){
		return createDate;
	}

	public void setCreateDate(String createDate){
		this.createDate = createDate;
	}

	public Long getChlId(){
		return chlId;
	}

	public void setChlId(Long chlId){
		this.chlId = chlId;
	}

	public String getChlOrderId(){
		return chlOrderId;
	}

	public void setChlOrderId(String chlOrderId){
		this.chlOrderId = chlOrderId;
	}

	public Short getIsDummy(){
		return isDummy;
	}

	public void setIsDummy(Short isDummy){
		this.isDummy = isDummy;
	}

	public Integer getPayTime(){
		return payTime;
	}

	public void setPayTime(Integer payTime){
		this.payTime = payTime;
	}

	public Long getGoodsCategoryId(){
		return goodsCategoryId;
	}

	public void setGoodsCategoryId(Long goodsCategoryId){
		this.goodsCategoryId = goodsCategoryId;
	}

	public Long getGoodsTypeId(){
		return goodsTypeId;
	}

	public void setGoodsTypeId(Long goodsTypeId){
		this.goodsTypeId = goodsTypeId;
	}

	public String getRemark(){
		return remark;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public Integer getOrderEndTime(){
		return orderEndTime;
	}

	public void setOrderEndTime(Integer orderEndTime){
		this.orderEndTime = orderEndTime;
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

	public Short getRefundStatus(){
		return refundStatus;
	}

	public void setRefundStatus(Short refundStatus){
		this.refundStatus = refundStatus;
	}

	public String getParam(){
		return param;
	}

	public void setParam(String param){
		this.param = param;
	}

	public Short getIsDelete(){
		return isDelete;
	}

	public void setIsDelete(Short isDelete){
		this.isDelete = isDelete;
	}

	public Short getPayWay() {
		return payWay;
	}

	public void setPayWay(Short payWay) {
		this.payWay = payWay;
	}

	public Short getDiscountWay() {
		return discountWay;
	}

	public void setDiscountWay(Short discountWay) {
		this.discountWay = discountWay;
	}

	public String getPayFlowNo() {
		return payFlowNo;
	}

	public void setPayFlowNo(String payFlowNo) {
		this.payFlowNo = payFlowNo;
	}
		
}

