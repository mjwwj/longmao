package com.opengroup.longmao.gwcommon.entity.dto;

import java.io.Serializable;

/**
 * GuessInfoDTO，GuessInfo继承类
 * 
 * @author Administrator
 *
 */
public class GuessInfoDTO implements Serializable {

	/**
	 * Comments for <code>serialVersionUID</code> 【请在此输入描述文字】
	 */
	private static final long serialVersionUID = -512986873217971092L;
	private String finalResultStr;
	private String statusNameStr;
	private String gmtCreateStr;
	private String gmtModifiedStr;
	private String stopBetTimeStr;
	private String stopRobTimeStr;
	private String isCustomStr;
	private String isRobStr;
	private String robStatusStr;

	public String getFinalResultStr() {
		return finalResultStr;
	}

	public void setFinalResultStr(String finalResultStr) {
		this.finalResultStr = finalResultStr;
	}

	public String getStatusNameStr() {
		return statusNameStr;
	}

	public void setStatusNameStr(String statusNameStr) {
		this.statusNameStr = statusNameStr;
	}

	public String getGmtCreateStr() {
		return gmtCreateStr;
	}

	public void setGmtCreateStr(String gmtCreateStr) {
		this.gmtCreateStr = gmtCreateStr;
	}

	public String getGmtModifiedStr() {
		return gmtModifiedStr;
	}

	public void setGmtModifiedStr(String gmtModifiedStr) {
		this.gmtModifiedStr = gmtModifiedStr;
	}

	public String getStopBetTimeStr() {
		return stopBetTimeStr;
	}

	public void setStopBetTimeStr(String stopBetTimeStr) {
		this.stopBetTimeStr = stopBetTimeStr;
	}

	public String getIsCustomStr() {
		return isCustomStr;
	}

	public void setIsCustomStr(String isCustomStr) {
		this.isCustomStr = isCustomStr;
	}

	public String getIsRobStr() {
		return isRobStr;
	}

	public void setIsRobStr(String isRobStr) {
		this.isRobStr = isRobStr;
	}

	public String getRobStatusStr() {
		return robStatusStr;
	}

	public void setRobStatusStr(String robStatusStr) {
		this.robStatusStr = robStatusStr;
	}

	public String getStopRobTimeStr() {
		return stopRobTimeStr;
	}

	public void setStopRobTimeStr(String stopRobTimeStr) {
		this.stopRobTimeStr = stopRobTimeStr;
	}

}
