package com.opengroup.longmao.gwcommon.entity.vo;

import java.io.Serializable;

public class GoodsInfoVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String ctimeStr;
	
	private String utimeStr;
	
	private String goodsTypeStr;
	
	private String goodsCategoryStr;
	
	private String auditStatusStr;
	
	private String isEnableStr;
	
	private String isSellOutStr;

	public String getCtimeStr() {
		return ctimeStr;
	}

	public void setCtimeStr(String ctimeStr) {
		this.ctimeStr = ctimeStr;
	}

	public String getGoodsTypeStr() {
		return goodsTypeStr;
	}

	public void setGoodsTypeStr(String goodsTypeStr) {
		this.goodsTypeStr = goodsTypeStr;
	}

	public String getGoodsCategoryStr() {
		return goodsCategoryStr;
	}

	public void setGoodsCategoryStr(String goodsCategoryStr) {
		this.goodsCategoryStr = goodsCategoryStr;
	}

	public String getAuditStatusStr() {
		return auditStatusStr;
	}

	public void setAuditStatusStr(String auditStatusStr) {
		this.auditStatusStr = auditStatusStr;
	}

	public String getIsEnableStr() {
		return isEnableStr;
	}

	public void setIsEnableStr(String isEnableStr) {
		this.isEnableStr = isEnableStr;
	}

	public String getIsSellOutStr() {
		return isSellOutStr;
	}

	public void setIsSellOutStr(String isSellOutStr) {
		this.isSellOutStr = isSellOutStr;
	}

	public String getUtimeStr() {
		return utimeStr;
	}

	public void setUtimeStr(String utimeStr) {
		this.utimeStr = utimeStr;
	}
	
	

}
