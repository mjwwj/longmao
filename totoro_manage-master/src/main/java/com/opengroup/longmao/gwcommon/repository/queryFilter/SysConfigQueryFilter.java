package com.opengroup.longmao.gwcommon.repository.queryFilter;

import com.opengroup.longmao.gwcommon.configuration.query.Where;
import com.opengroup.longmao.gwcommon.configuration.query.annotation.QBindAttrField;
import com.opengroup.longmao.gwcommon.configuration.query.annotation.QBindEntity;
import com.opengroup.longmao.gwcommon.configuration.query.core.BaseQuery;
import com.opengroup.longmao.gwcommon.entity.po.SysConfig;

/**
 * 【系统配置信息】构造查询条件
 *
 * @version
 * @author Yangst 2017年04月28日 上午10:59:13
 */ 
@QBindEntity(entityClass = SysConfig.class)
public class SysConfigQueryFilter extends BaseQuery {

	@QBindAttrField(fieldName = "isDelete", where = Where.equal)
	private Short isDelete;

	public Short getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Short isDelete) {
		this.isDelete = isDelete;
	}


}

