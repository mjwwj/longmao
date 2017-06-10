package com.opengroup.longmao.gwcommon.repository.queryFilter;

import com.opengroup.longmao.gwcommon.configuration.query.Where;
import com.opengroup.longmao.gwcommon.configuration.query.annotation.QBindAttrField;
import com.opengroup.longmao.gwcommon.configuration.query.annotation.QBindEntity;
import com.opengroup.longmao.gwcommon.configuration.query.core.BaseQuery;
import com.opengroup.longmao.gwcommon.entity.po.User;

/**
 * 【用户】构造查询条件
 *
 * @version
 * @author Hermit 2017年03月23日 上午09:08:34
 */
@QBindEntity(entityClass = User.class)
public class AnchorQueryFilter extends BaseQuery {

	@QBindAttrField(fieldName = "userId", where = Where.equal)
	private String userId;

	@QBindAttrField(fieldName = "grade", where = Where.equal)
	private Integer grade;

	@QBindAttrField(fieldName = "userType", where = Where.equal)
	private Integer userType;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

}
