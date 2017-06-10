package com.opengroup.longmao.gwcommon.entity.po;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OrderBuyDetail 实体类
*
*【请在此写上此类功能描述文字】
*
 * @version
 * @author zengjq 2016年04月11日 上午11:39:08
 */ 
@Entity
@Table(name = "order_buy_detail") 
public class OrderBuyDetail implements Serializable {

	private static final long serialVersionUID = 7919069471334722780L;

	@Id
	@Column(name = "buy_detail_id")
	private Long buyDetailId;

	@Column(name = "order_id")
	private Long orderId;

	@Column(name = "goods_id")
	private Long goodsId;

	@Column(name = "goods_name")
	private String goodsName;

	@Column(name = "goods_price")
	private BigDecimal goodsPrice;

	@Column(name = "goods_num")
	private Integer goodsNum;

	@Column(name = "goods_discount")
	private Integer goodsDiscount;

	@Column(name = "ctime")
	private Integer ctime;

	@Column(name = "utime")
	private Integer utime;

	@Column(name = "is_delete")
	private Short isDelete;


	public Long getBuyDetailId(){
		return buyDetailId;
	}

	public void setBuyDetailId(Long buyDetailId){
		this.buyDetailId=buyDetailId;
	}

	public Long getOrderId(){
		return orderId;
	}

	public void setOrderId(Long orderId){
		this.orderId=orderId;
	}

	public Long getGoodsId(){
		return goodsId;
	}

	public void setGoodsId(Long goodsId){
		this.goodsId=goodsId;
	}

	public String getGoodsName(){
		return goodsName;
	}

	public void setGoodsName(String goodsName){
		this.goodsName=goodsName;
	}

	public BigDecimal getGoodsPrice(){
		return goodsPrice;
	}

	public void setGoodsPrice(BigDecimal goodsPrice){
		this.goodsPrice=goodsPrice;
	}

	public Integer getGoodsNum(){
		return goodsNum;
	}

	public void setGoodsNum(Integer goodsNum){
		this.goodsNum=goodsNum;
	}

	public Integer getGoodsDiscount(){
		return goodsDiscount;
	}

	public void setGoodsDiscount(Integer goodsDiscount){
		this.goodsDiscount=goodsDiscount;
	}

	public Integer getCtime(){
		return ctime;
	}

	public void setCtime(Integer ctime){
		this.ctime=ctime;
	}

	public Integer getUtime(){
		return utime;
	}

	public void setUtime(Integer utime){
		this.utime=utime;
	}

	public Short getIsDelete(){
		return isDelete;
	}

	public void setIsDelete(Short isDelete){
		this.isDelete=isDelete;
	}

}

