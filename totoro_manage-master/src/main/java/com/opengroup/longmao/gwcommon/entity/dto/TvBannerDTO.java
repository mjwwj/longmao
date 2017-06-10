package com.opengroup.longmao.gwcommon.entity.dto;

import java.io.Serializable;

/**
 * GuessInfoDTO，GuessInfo继承类
 * 
 * @author Administrator
 *
 */
public class TvBannerDTO implements Serializable {

	/**
	 * Comments for <code>serialVersionUID</code> 【请在此输入描述文字】
	 */
	private static final long serialVersionUID = -512986873217971092L;
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
