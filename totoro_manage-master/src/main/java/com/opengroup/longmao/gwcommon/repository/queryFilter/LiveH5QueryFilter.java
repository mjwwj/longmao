package com.opengroup.longmao.gwcommon.repository.queryFilter;

import com.opengroup.longmao.gwcommon.configuration.query.Where;
import com.opengroup.longmao.gwcommon.configuration.query.annotation.QBindAttrField;
import com.opengroup.longmao.gwcommon.configuration.query.annotation.QBindEntity;
import com.opengroup.longmao.gwcommon.configuration.query.core.BaseQuery;
import com.opengroup.longmao.gwcommon.entity.po.LiveH5;

/**
 * 【直播间H5活动入口】构造查询条件
 *
 * @version
 * @author Hermit 2017年05月12日 下午16:09:19
 */ 
@QBindEntity(entityClass = LiveH5.class)
public class LiveH5QueryFilter extends BaseQuery {

	@QBindAttrField(fieldName = "id", where = Where.equal)
	private Long id;

	@QBindAttrField(fieldName = "activeName", where = Where.equal)
	private String activeName;
	
	@QBindAttrField(fieldName = "isDelete", where = Where.equal)
	private Short isDelete;
	
	@QBindAttrField(fieldName = "isEnable", where = Where.equal)
	private Short isEnable;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActiveName() {
		return activeName;
	}

	public void setActiveName(String activeName) {
		this.activeName = activeName;
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

