package com.opengroup.longmao.gwcommon.entity.vo;

import java.io.Serializable;
/**
 * 【banner】 表现层对象
 *
 * @version
 * @author Hermit 2017年05月10日 下午16:51:15
 */ 
public class TvBannerVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String bannerName;

	private String bannerPicUrl;

	private String bannerUrl;

	private String upTimeStr;

	private String downTimeStr;

	private Short sortNum;
	
	private Short bannerStatus;

	public String getBannerName() {
		return bannerName;
	}

	public void setBannerName(String bannerName) {
		this.bannerName = bannerName;
	}

	public String getBannerPicUrl() {
		return bannerPicUrl;
	}

	public void setBannerPicUrl(String bannerPicUrl) {
		this.bannerPicUrl = bannerPicUrl;
	}

	public String getBannerUrl() {
		return bannerUrl;
	}

	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}

	public String getUpTimeStr() {
		return upTimeStr;
	}

	public void setUpTimeStr(String upTimeStr) {
		this.upTimeStr = upTimeStr;
	}

	public String getDownTimeStr() {
		return downTimeStr;
	}

	public void setDownTimeStr(String downTimeStr) {
		this.downTimeStr = downTimeStr;
	}

	public Short getSortNum() {
		return sortNum;
	}

	public void setSortNum(Short sortNum) {
		this.sortNum = sortNum;
	}

	public Short getBannerStatus() {
		return bannerStatus;
	}

	public void setBannerStatus(Short bannerStatus) {
		this.bannerStatus = bannerStatus;
	}

}

