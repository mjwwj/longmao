package com.opengroup.longmao.gwcommon.repository.queryFilter;

import com.opengroup.longmao.gwcommon.configuration.query.Where;
import com.opengroup.longmao.gwcommon.configuration.query.annotation.QBindAttrField;
import com.opengroup.longmao.gwcommon.configuration.query.annotation.QBindEntity;
import com.opengroup.longmao.gwcommon.configuration.query.core.BaseQuery;
import com.opengroup.longmao.gwcommon.entity.po.TvActivity;

/**
 * 【活动配置表】构造查询条件
 *
 * @version
 * @author Hermit 2017年04月26日 上午11:11:50
 */ 
@QBindEntity(entityClass = TvActivity.class)
public class TvActivityQueryFilter extends BaseQuery {
	@QBindAttrField(fieldName = "isDelete", where = Where.equal)
	private Short isDelete;

	public Short getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Short isDelete) {
		this.isDelete = isDelete;
	}
}

