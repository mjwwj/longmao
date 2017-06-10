package com.opengroup.longmao.gwcommon.entity.vo;

import java.io.Serializable;
/**
 * 【直播间H5活动入口】 表现层对象
 *
 * @version
 * @author Hermit 2017年05月12日 下午16:09:19
 */ 
public class LiveH5VO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Short sortNum;

	private String activeName;

	private String activeLink;

	private String activeIco;

	private String directions;
	
	private String upTimeStr;

	private String downTimeStr;
	
	private Short isEnable;

	public Short getSortNum() {
		return sortNum;
	}

	public void setSortNum(Short sortNum) {
		this.sortNum = sortNum;
	}

	public String getActiveName() {
		return activeName;
	}

	public void setActiveName(String activeName) {
		this.activeName = activeName;
	}

	public String getActiveLink() {
		return activeLink;
	}

	public void setActiveLink(String activeLink) {
		this.activeLink = activeLink;
	}

	public String getActiveIco() {
		return activeIco;
	}

	public void setActiveIco(String activeIco) {
		this.activeIco = activeIco;
	}

	public String getDirections() {
		return directions;
	}

	public void setDirections(String directions) {
		this.directions = directions;
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

	public Short getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Short isEnable) {
		this.isEnable = isEnable;
	}
	
}