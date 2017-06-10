package com.opengroup.longmao.gwcommon.entity.dto;

import java.io.Serializable;

/**
 * GuessInfoDTO，GuessInfo继承类
 * 
 * @author Administrator
 *
 */
public class TvIllegalReportDTO implements Serializable {

	/**
	 * Comments for <code>serialVersionUID</code> 【请在此输入描述文字】
	 */
	private static final long serialVersionUID = -512986873217971092L;
	private String ctimeStr;

	private String utimeStr;
	
	private String dealTimeStr;
	
	private String dealUserStr;
	
	private String reportTypeStr;
	
	private String dealStatusStr;
	
	public String getReportTypeStr() {
		return reportTypeStr;
	}

	public void setReportTypeStr(String reportTypeStr) {
		this.reportTypeStr = reportTypeStr;
	}

	public String getDealStatusStr() {
		return dealStatusStr;
	}

	public void setDealStatusStr(String dealStatusStr) {
		this.dealStatusStr = dealStatusStr;
	}

	public String getDealUserStr() {
		return dealUserStr;
	}

	public void setDealUserStr(String dealUserStr) {
		this.dealUserStr = dealUserStr;
	}

	public String getDealTimeStr() {
		return dealTimeStr;
	}

	public void setDealTimeStr(String dealTimeStr) {
		this.dealTimeStr = dealTimeStr;
	}

	public String getCtimeStr() {
		return ctimeStr;
	}

	public void setCtimeStr(String ctimeStr) {
		this.ctimeStr = ctimeStr;
	}

	public String getUtimeStr() {
		return utimeStr;
	}

	public void setUtimeStr(String utimeStr) {
		this.utimeStr = utimeStr;
	}
	
}
