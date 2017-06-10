package com.opengroup.longmao.gwcommon.repository.queryFilter;

import com.opengroup.longmao.gwcommon.configuration.query.Where;
import com.opengroup.longmao.gwcommon.configuration.query.annotation.QBindAttrField;
import com.opengroup.longmao.gwcommon.configuration.query.annotation.QBindEntity;
import com.opengroup.longmao.gwcommon.configuration.query.core.BaseQuery;
import com.opengroup.longmao.gwcommon.entity.po.GuessInfo;

/**
 * GuessQueryFilter 构造查询条件类
 *
 * @version
 * @author zengjq 2016年10月29日 下午5:04:24
 * 
 */
@QBindEntity(entityClass = GuessInfo.class)
public class GuessInfoQueryFilter extends BaseQuery {

	@QBindAttrField(fieldName = "guessId", where = Where.equal)
	private Long guessId;

	@QBindAttrField(fieldName = "anchorId", where = Where.equal)
	private Long anchorId;

	@QBindAttrField(fieldName = "status", where = Where.equal)
	private Short status;
	
	@QBindAttrField(fieldName = "robStatus", where = Where.equal)
	private Short robStatus;
	
	@QBindAttrField(fieldName = "finalResult", where = Where.equal)
	private Short finalResult;

	public Long getGuessId() {
		return guessId;
	}

	public void setGuessId(Long guessId) {
		this.guessId = guessId;
	}

	public Long getAnchorId() {
		return anchorId;
	}

	public void setAnchorId(Long anchorId) {
		this.anchorId = anchorId;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Short getRobStatus() {
		return robStatus;
	}

	public void setRobStatus(Short robStatus) {
		this.robStatus = robStatus;
	}

	public Short getFinalResult() {
		return finalResult;
	}

	public void setFinalResult(Short finalResult) {
		this.finalResult = finalResult;
	}

}
