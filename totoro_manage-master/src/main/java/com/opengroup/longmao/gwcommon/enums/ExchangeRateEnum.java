/**
 * @Title: ExchangeRateEnum.java 
 * @Package com.opengroup.longmao.gwcommon.enums 
 * @Description:
 * @author Mr.Zhu
 * @version V1.5
 */
package com.opengroup.longmao.gwcommon.enums;

/**
 * @ClassName: ExchangeRateEnum
 * @Description: 龙猫豆、龙猫币、商品等换算比率
 * @author Mr.Zhu
 */
public enum ExchangeRateEnum {
	BEAN_RATE_GOODS("100", "龙猫豆兑换商品"),
	HUYI_RATE_BEAN("0.996", "互亿无线充值"),
	CALORIE_RATE_RMB("0.10", "卡路里兑换RMB"),
	EXTRACT_CASH_RATE("0.50", "提取现金比率");
	private String val;
	private String code;

	private ExchangeRateEnum(String val, String code) {
		this.val = val;
		this.code = code;
	}

	public String getVal() {
		return val;
	}

	public String getCode() {
		return code;
	}

	public static ExchangeRateEnum getEnumByVal(String val) {
		if (val == null)
			return null;
		for (ExchangeRateEnum exchangeRateEnum : ExchangeRateEnum.values()) {
			if (exchangeRateEnum.getVal().equals(val))
				return exchangeRateEnum;
		}
		return null;
	}

	public static ExchangeRateEnum getEnumByCode(String code) {
		if (code == null)
			return null;
		for (ExchangeRateEnum exchangeRateEnum : ExchangeRateEnum.values()) {
			if (exchangeRateEnum.getCode().equals(code))
				return exchangeRateEnum;
		}
		return null;
	}
}
