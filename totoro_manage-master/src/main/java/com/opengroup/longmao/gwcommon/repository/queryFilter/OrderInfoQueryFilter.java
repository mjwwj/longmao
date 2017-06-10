/**
 * @Title: OrderInfoQueryFilter.java 
 * @Package com.opengroup.longmao.gwcommon.repository.queryFilter 
 * @Description:
 * @author Mr.Zhu
 * @version V1.5
 */
package com.opengroup.longmao.gwcommon.repository.queryFilter;

import com.opengroup.longmao.gwcommon.configuration.query.Where;
import com.opengroup.longmao.gwcommon.configuration.query.annotation.QBindAttrField;
import com.opengroup.longmao.gwcommon.configuration.query.annotation.QBindEntity;
import com.opengroup.longmao.gwcommon.configuration.query.core.BaseQuery;
import com.opengroup.longmao.gwcommon.entity.po.OrderInfo;

/**
 * @ClassName: OrderInfoQueryFilter 
 * @Description: TODO
 * @author Mr.Zhu
 */
@QBindEntity(entityClass = OrderInfo.class)
public class OrderInfoQueryFilter extends BaseQuery {
	
	@QBindAttrField(fieldName = "buyerUid", where = Where.equal)
	private Long buyerUid;
	
	@QBindAttrField(fieldName = "sellerUid", where = Where.equal)
	private Long sellerUid;
	
	@QBindAttrField(fieldName = "chlId", where = Where.equal)
	private Long chlId;
	
	@QBindAttrField(fieldName = "orderStatus", where = Where.equal)
	private Short orderStatus;
	
	@QBindAttrField(fieldName = "deliveryStatus", where = Where.equal)
	private Short deliveryStatus;
	
	@QBindAttrField(fieldName = "createDate", where = Where.equal)
	private String createDate;
	
	@QBindAttrField(fieldName = "goods_type_id", where = Where.equal)
	private Long goodsTypeId;

	
	public Long getGoodsTypeId() {
		return goodsTypeId;
	}

	public void setGoodsTypeId(Long goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}

	public Long getBuyerUid() {
		return buyerUid;
	}

	public void setBuyerUid(Long buyerUid) {
		this.buyerUid = buyerUid;
	}

	public Long getSellerUid() {
		return sellerUid;
	}

	public void setSellerUid(Long sellerUid) {
		this.sellerUid = sellerUid;
	}

	public Long getChlId() {
		return chlId;
	}

	public void setChlId(Long chlId) {
		this.chlId = chlId;
	}

	public Short getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Short orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Short getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(Short deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
		
}
