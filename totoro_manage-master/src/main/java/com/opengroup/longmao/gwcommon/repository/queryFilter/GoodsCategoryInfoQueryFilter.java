package com.opengroup.longmao.gwcommon.repository.queryFilter;

import com.opengroup.longmao.gwcommon.configuration.query.Where;
import com.opengroup.longmao.gwcommon.configuration.query.annotation.QBindAttrField;
import com.opengroup.longmao.gwcommon.configuration.query.annotation.QBindEntity;
import com.opengroup.longmao.gwcommon.configuration.query.core.BaseQuery;
import com.opengroup.longmao.gwcommon.entity.po.GoodsCategoryInfo;

/**
 * 【类别】构造查询条件
 *
 * @version
 * @author Hermit 2017年05月15日 下午16:35:03
 */ 
@QBindEntity(entityClass = GoodsCategoryInfo.class)
public class GoodsCategoryInfoQueryFilter extends BaseQuery {

	@QBindAttrField(fieldName = "goodsCategoryId", where = Where.equal)
	private Long goodsCategoryId;

	@QBindAttrField(fieldName = "goodsTypeId", where = Where.equal)
	private Long goodsTypeId;

	@QBindAttrField(fieldName = "name", where = Where.equal)
	private String name;

	@QBindAttrField(fieldName = "isEnable", where = Where.equal)
	private Short isEnable;
	
	@QBindAttrField(fieldName = "isDelete", where = Where.equal)
	private Short isDelete;

	public Long getGoodsCategoryId() {
		return goodsCategoryId;
	}

	public void setGoodsCategoryId(Long goodsCategoryId) {
		this.goodsCategoryId = goodsCategoryId;
	}

	public Long getGoodsTypeId() {
		return goodsTypeId;
	}

	public void setGoodsTypeId(Long goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Short getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Short isDelete) {
		this.isDelete = isDelete;
	}

	public Short getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Short isEnable) {
		this.isEnable = isEnable;
	}

}