package com.opengroup.longmao.gwcommon.repository.queryFilter;

import com.opengroup.longmao.gwcommon.configuration.query.Where;
import com.opengroup.longmao.gwcommon.configuration.query.annotation.QBindAttrField;
import com.opengroup.longmao.gwcommon.configuration.query.annotation.QBindEntity;
import com.opengroup.longmao.gwcommon.configuration.query.core.BaseQuery;
import com.opengroup.longmao.gwcommon.entity.po.UserGradeRule;

/**
 * 【用户】构造查询条件
 *
 * @version
 * @author Hermit 2017年03月23日 上午09:08:34
 */
@QBindEntity(entityClass = UserGradeRule.class)
public class GradeRuleQueryFilter extends BaseQuery {

	@QBindAttrField(fieldName = "grade", where = Where.equal)
	private Short grade;

	@QBindAttrField(fieldName = "minVal", where = Where.lessThanOrEqualTo)
	private Long minVal;

	@QBindAttrField(fieldName = "maxVal", where = Where.greaterThan)
	private Long maxVal;
	
	@QBindAttrField(fieldName = "alias", where = Where.equal)
	private String alias;
	
	@QBindAttrField(fieldName = "isDelete", where = Where.equal)
	private Short isDelete;

	public Short getGrade() {
		return grade;
	}

	public void setGrade(Short grade) {
		this.grade = grade;
	}

	public Long getMinVal() {
		return minVal;
	}

	public void setMinVal(Long minVal) {
		this.minVal = minVal;
	}

	public Long getMaxVal() {
		return maxVal;
	}

	public void setMaxVal(Long maxVal) {
		this.maxVal = maxVal;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Short getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Short isDelete) {
		this.isDelete = isDelete;
	}
	
}
