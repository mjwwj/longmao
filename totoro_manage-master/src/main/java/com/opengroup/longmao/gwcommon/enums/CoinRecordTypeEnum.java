/**
 * @Title: UseTypeEnum.java 
 * @Package com.opengroup.longmao.gwcommon.enums 
 * @Description:
 * @author Administrator
 * @version V1.5
 */
package com.opengroup.longmao.gwcommon.enums;

/**
 * @ClassName: BizTypeEnum
 * @Description: 龙猫豆、龙猫币使用类型
 * @author Administrator
 */
public enum CoinRecordTypeEnum {
	
	COIN_IN_BEAN(0, "币换豆"),
	RECHARGE(1, "充值"), 
	RECHARGE_GIVE(2, "充值送"),
	GIFT_GIVE(3, "送礼消费"),
	REGISTER(4, "注册"),
	REGISTER_GIVE(5, "注册送"),
	BEAN_IN_COIN(9, "豆换币"),
	;
	private Integer val;
	private String code;

	private CoinRecordTypeEnum(Integer val, String code) {
		this.val = val;
		this.code = code;
	}

	public Integer getVal() {
		return val;
	}

	public String getCode() {
		return code;
	}

	public static CoinRecordTypeEnum getEnumByVal(Integer val) {
		if (val == null)
			return null;
		for (CoinRecordTypeEnum useTypeEnum : CoinRecordTypeEnum.values()) {
			if (useTypeEnum.getVal().equals(val))
				return useTypeEnum;
		}
		return null;
	}

	public static CoinRecordTypeEnum getEnumByCode(String code) {
		if (code == null)
			return null;
		for (CoinRecordTypeEnum useTypeEnum : CoinRecordTypeEnum.values()) {
			if (useTypeEnum.getCode().equals(code))
				return useTypeEnum;
		}
		return null;
	}
}
