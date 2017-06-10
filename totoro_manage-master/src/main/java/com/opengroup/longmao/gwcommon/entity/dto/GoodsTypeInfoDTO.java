package com.opengroup.longmao.gwcommon.entity.dto;

import java.io.Serializable;
/**
 * 【类型】 数据传输对象
 *
 * @version
 * @author Hermit 2017年05月15日 下午16:34:49
 */ 
public class GoodsTypeInfoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
    private String isEnablestr;
	private String cTimeStr; 
	private String uTimeStr; 
    
	public String getIsEnablestr() {
		return isEnablestr;
	}
	public void setIsEnablestr(String isEnablestr) {
		this.isEnablestr = isEnablestr;
	}
	public String getcTimeStr() {
		return cTimeStr;
	}
	public void setcTimeStr(String cTimeStr) {
		this.cTimeStr = cTimeStr;
	}
	public String getuTimeStr() {
		return uTimeStr;
	}
	public void setuTimeStr(String uTimeStr) {
		this.uTimeStr = uTimeStr;
	}
	
	
    
}

