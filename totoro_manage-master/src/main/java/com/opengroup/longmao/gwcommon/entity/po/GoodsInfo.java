package com.opengroup.longmao.gwcommon.entity.po;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.opengroup.longmao.gwcommon.entity.vo.GoodsInfoVO;

/**
 * 【商品信息表】 持久化对象
 *
 * @version
 * @author Hermit 2017年05月16日 下午17:13:21
 */ 
@Entity
@Table(name = "goods_info") 
public class GoodsInfo extends GoodsInfoVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "goods_id")
	private Long goodsId;

	@Column(name = "goods_category_id")
	private Long goodsCategoryId;

	@Column(name = "name")
	private String name;

	@Column(name = "current_storage")
	private Integer currentStorage;

	@Column(name = "price")
	private BigDecimal price;

	@Column(name = "price_num")
	private Integer priceNum;

	@Column(name = "discount")
	private Integer discount;

	@Column(name = "seller_uid")
	private Long sellerUid;

	@Column(name = "audit_status")
	private Short auditStatus;

	@Column(name = "is_enable")
	private Short isEnable;

	@Column(name = "is_sell_out")
	private Short isSellOut;

	@Column(name = "chl_id")
	private Long chlId;

	@Column(name = "chl_name")
	private String chlName;

	@Column(name = "goods_type_id")
	private Long goodsTypeId;

	@Column(name = "remark")
	private String remark;

	@Column(name = "ctime")
	private Integer ctime;

	@Column(name = "utime")
	private Integer utime;

	@Column(name = "is_delete")
	private Short isDelete;


	public Long getGoodsId(){
		return goodsId;
	}

	public void setGoodsId(Long goodsId){
		this.goodsId = goodsId;
	}

	public Long getGoodsCategoryId(){
		return goodsCategoryId;
	}

	public void setGoodsCategoryId(Long goodsCategoryId){
		this.goodsCategoryId = goodsCategoryId;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public Integer getCurrentStorage(){
		return currentStorage;
	}

	public void setCurrentStorage(Integer currentStorage){
		this.currentStorage = currentStorage;
	}

	public BigDecimal getPrice(){
		return price;
	}

	public void setPrice(BigDecimal price){
		this.price = price;
	}

	public Integer getPriceNum(){
		return priceNum;
	}

	public void setPriceNum(Integer priceNum){
		this.priceNum = priceNum;
	}

	public Integer getDiscount(){
		return discount;
	}

	public void setDiscount(Integer discount){
		this.discount = discount;
	}

	public Long getSellerUid(){
		return sellerUid;
	}

	public void setSellerUid(Long sellerUid){
		this.sellerUid = sellerUid;
	}

	public Short getAuditStatus(){
		return auditStatus;
	}

	public void setAuditStatus(Short auditStatus){
		this.auditStatus = auditStatus;
	}

	public Short getIsEnable(){
		return isEnable;
	}

	public void setIsEnable(Short isEnable){
		this.isEnable = isEnable;
	}

	public Short getIsSellOut(){
		return isSellOut;
	}

	public void setIsSellOut(Short isSellOut){
		this.isSellOut = isSellOut;
	}

	public Long getChlId(){
		return chlId;
	}

	public void setChlId(Long chlId){
		this.chlId = chlId;
	}

	public String getChlName(){
		return chlName;
	}

	public void setChlName(String chlName){
		this.chlName = chlName;
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

