package com.opengroup.longmao.gwcommon.repository.queryFilter;

import javax.persistence.Column;

import com.opengroup.longmao.gwcommon.configuration.query.Where;
import com.opengroup.longmao.gwcommon.configuration.query.annotation.QBindAttrField;
import com.opengroup.longmao.gwcommon.configuration.query.annotation.QBindEntity;
import com.opengroup.longmao.gwcommon.configuration.query.core.BaseQuery;
import com.opengroup.longmao.gwcommon.entity.po.TvIllegalReport;

/**
 * 【举报情况表】构造查询条件
 *
 * @version
 * @author Hermit 2017年04月25日 上午11:08:03
 */ 
@QBindEntity(entityClass = TvIllegalReport.class)
public class TvIllegalReportQueryFilter extends BaseQuery {
	@QBindAttrField(fieldName = "isDelete", where = Where.equal)
	private Short isDelete;
	
	@QBindAttrField(fieldName = "dealStatus", where = Where.equal)
	private Short dealStatus;
	
	@QBindAttrField(fieldName = "userId", where = Where.equal)
	private Long userId;
	
	@QBindAttrField(fieldName = "reportType", where = Where.equal)
	private Short reportType;
	
	@QBindAttrField(fieldName = "reportId", where = Where.equal)
	private Long reportId;
	
	@QBindAttrField(fieldName = "anchorId", where = Where.equal)
	private Long anchorId;

	public Short getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Short isDelete) {
		this.isDelete = isDelete;
	}

	public Short getDealStatus() {
		return dealStatus;
	}

	public void setDealStatus(Short dealStatus) {
		this.dealStatus = dealStatus;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Short getReportType() {
		return reportType;
	}

	public void setReportType(Short reportType) {
		this.reportType = reportType;
	}

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public Long getAnchorId() {
		return anchorId;
	}

	public void setAnchorId(Long anchorId) {
		this.anchorId = anchorId;
	}
	
	
}

