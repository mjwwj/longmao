package com.opengroup.longmao.gwcommon.repository.queryFilter;

import com.opengroup.longmao.gwcommon.configuration.query.Where;
import com.opengroup.longmao.gwcommon.configuration.query.annotation.QBindAttrField;
import com.opengroup.longmao.gwcommon.configuration.query.annotation.QBindEntity;
import com.opengroup.longmao.gwcommon.configuration.query.core.BaseQuery;
import java.math.BigDecimal;
import java.util.Date;
import com.opengroup.longmao.gwcommon.entity.po.GoodsInfo;

/**
 * 【商品信息表】构造查询条件
 *
 * @version
 * @author Hermit 2017年05月16日 下午17:13:21
 */ 
@QBindEntity(entityClass = GoodsInfo.class)
public class GoodsInfoQueryFilter extends BaseQuery {

	@QBindAttrField(fieldName = "goodsId", where = Where.equal)
	private Long goodsId;

	@QBindAttrField(fieldName = "goodsCategoryId", where = Where.equal)
	private Long goodsCategoryId;

	@QBindAttrField(fieldName = "name", where = Where.equal)
	private String name;

	@QBindAttrField(fieldName = "currentStorage", where = Where.equal)
	private Integer currentStorage;

	@QBindAttrField(fieldName = "price", where = Where.equal)
	private BigDecimal price;

	@QBindAttrField(fieldName = "priceNum", where = Where.equal)
	private Integer priceNum;

	@QBindAttrField(fieldName = "discount", where = Where.equal)
	private Integer discount;

	@QBindAttrField(fieldName = "sellerUid", where = Where.equal)
	private Long sellerUid;

	@QBindAttrField(fieldName = "auditStatus", where = Where.equal)
	private Short auditStatus;

	@QBindAttrField(fieldName = "isEnable", where = Where.equal)
	private Short isEnable;

	@QBindAttrField(fieldName = "isSellOut", where = Where.equal)
	private Short isSellOut;

	@QBindAttrField(fieldName = "chlId", where = Where.equal)
	private Long chlId;

	@QBindAttrField(fieldName = "chlName", where = Where.equal)
	private String chlName;

	@QBindAttrField(fieldName = "goodsTypeId", where = Where.equal)
	private Long goodsTypeId;

	@QBindAttrField(fieldName = "remark", where = Where.equal)
	private String remark;

	@QBindAttrField(fieldName = "ctime", where = Where.equal)
	private Integer ctime;

	@QBindAttrField(fieldName = "utime", where = Where.equal)
	private Integer utime;

	@QBindAttrField(fieldName = "isDelete", where = Where.equal)
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

