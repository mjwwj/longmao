package com.opengroup.longmao.gwcommon.entity.dto;

import java.io.Serializable;

/**
 * 【类别】 数据传输对象
 *
 * @version
 * @author Hermit 2017年05月15日 下午16:35:03
 */
public class GoodsCategoryInfoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String isEnablestr;
	private String goodsTypeStr;
	private String cTimeStr;
	private String uTimeStr;

	public String getIsEnablestr() {
		return isEnablestr;
	}

	public void setIsEnablestr(String isEnablestr) {
		this.isEnablestr = isEnablestr;
	}

	public String getGoodsTypeStr() {
		return goodsTypeStr;
	}

	public void setGoodsTypeStr(String goodsTypeStr) {
		this.goodsTypeStr = goodsTypeStr;
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
