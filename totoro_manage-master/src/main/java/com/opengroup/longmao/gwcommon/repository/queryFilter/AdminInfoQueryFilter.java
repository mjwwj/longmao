package com.opengroup.longmao.gwcommon.repository.queryFilter;

import com.opengroup.longmao.gwcommon.configuration.query.Where;
import com.opengroup.longmao.gwcommon.configuration.query.annotation.QBindAttrField;
import com.opengroup.longmao.gwcommon.configuration.query.annotation.QBindEntity;
import com.opengroup.longmao.gwcommon.configuration.query.core.BaseQuery;
import com.opengroup.longmao.gwcommon.entity.po.AdminInfo;

/**
 * AdminInfoQueryFilter 构造查询条件类
 *
 * @version
 * @author zengjq 2017年4月14日 下午5:04:24
 * 
 */
@QBindEntity(entityClass = AdminInfo.class)
public class AdminInfoQueryFilter extends BaseQuery {
	@QBindAttrField(fieldName = "userId", where = Where.equal)
	private Long userId;
	@QBindAttrField(fieldName = "userName", where = Where.equal)
	private String userName;
	@QBindAttrField(fieldName = "isDelete", where = Where.equal)
	private Short isDelete;

	public Long getUserId() {
 		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Short getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Short isDelete) {
		this.isDelete = isDelete;
	}

}
