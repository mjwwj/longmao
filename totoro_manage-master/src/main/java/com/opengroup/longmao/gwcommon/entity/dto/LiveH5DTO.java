package com.opengroup.longmao.gwcommon.entity.dto;

import java.io.Serializable;

/**
 * 【直播间H5活动入口】 数据传输对象
 *
 * @version
 * @author Hermit 2017年05月12日 下午16:09:19
 */
public class LiveH5DTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String upTimeStr;
	private String downTimeStr;
	private String isEnableStr;

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

	public String getIsEnableStr() {
		return isEnableStr;
	}

	public void setIsEnableStr(String isEnableStr) {
		this.isEnableStr = isEnableStr;
	}

}
