package com.opengroup.longmao.gwcommon.repository.queryFilter;

import com.opengroup.longmao.gwcommon.configuration.query.Where;
import com.opengroup.longmao.gwcommon.configuration.query.annotation.QBindAttrField;
import com.opengroup.longmao.gwcommon.configuration.query.annotation.QBindEntity;
import com.opengroup.longmao.gwcommon.configuration.query.core.BaseQuery;
import com.opengroup.longmao.gwcommon.entity.po.TvFeedback;

/**
 * 【反馈意见表】构造查询条件
 *
 * @version
 * @author Hermit 2017年04月25日 上午11:11:47
 */ 
@QBindEntity(entityClass = TvFeedback.class)
public class TvFeedbackQueryFilter extends BaseQuery {
	@QBindAttrField(fieldName = "isDelete", where = Where.equal)
	private Short isDelete;
	
	@QBindAttrField(fieldName = "userId", where = Where.equal)
	private Long userId;
	
	@QBindAttrField(fieldName = "systemType", where = Where.like)
	private String systemType;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getSystemType() {
		return systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}

	public Short getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Short isDelete) {
		this.isDelete = isDelete;
	}
}

