package com.opengroup.longmao.gwcommon.repository.queryFilter;

import com.opengroup.longmao.gwcommon.configuration.query.Where;
import com.opengroup.longmao.gwcommon.configuration.query.annotation.QBindAttrField;
import com.opengroup.longmao.gwcommon.configuration.query.annotation.QBindEntity;
import com.opengroup.longmao.gwcommon.configuration.query.core.BaseQuery;
import com.opengroup.longmao.gwcommon.entity.po.TvBanner;

/**
 * 【banner配置表】构造查询条件
 *
 * @version
 * @author Hermit 2017年04月26日 上午11:11:17
 */ 
@QBindEntity(entityClass = TvBanner.class)
public class TvBannerQueryFilter extends BaseQuery {
	
	@QBindAttrField(fieldName = "isDelete", where = Where.equal)
	private Short isDelete;
	
	@QBindAttrField(fieldName = "bannerName", where = Where.like)
	private String bannerName;
	
	@QBindAttrField(fieldName = "bannerStatus", where = Where.equal)
	private Short bannerStatus;

	public String getBannerName() {
		return bannerName;
	}

	public void setBannerName(String bannerName) {
		this.bannerName = bannerName;
	}

	public Short getBannerStatus() {
		return bannerStatus;
	}

	public void setBannerStatus(Short bannerStatus) {
		this.bannerStatus = bannerStatus;
	}

	public Short getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Short isDelete) {
		this.isDelete = isDelete;
	}

}

