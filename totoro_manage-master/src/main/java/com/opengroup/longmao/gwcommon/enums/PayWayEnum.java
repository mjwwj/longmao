package com.opengroup.longmao.gwcommon.enums;
/**
 * 支付方式枚举
 * @author
 *
 */
public enum PayWayEnum {
	//val, name
	ALIPAY(Short.parseShort("1"),"支付宝"),
	WE_CHART(Short.parseShort("2"),"微信"),
	EBANK(Short.parseShort("3"),"银联"),
	IOS_INNER(Short.parseShort("4"),"ios内支付"),
	TOTORO_BEAN(Short.parseShort("5"),"龙猫豆"),
	PLATFORM_DEDUCTION(Short.parseShort("6"),"平台扣费"),
	TOTORO_CALORIE(Short.parseShort("7"),"卡路里");
	private Short type;
	private String desc;

	private PayWayEnum (Short type,String desc) {
		this.type = type;
		this.desc = desc;
	}
	
	public Short getType() {
		return type;
	}
	public String getDesc() {
		return desc;
	}
		
	public static PayWayEnum getEnumByNumber(Short type){
		if (type == null)
            return null;
        for (PayWayEnum payWayTmp : PayWayEnum.values()) {
            if (payWayTmp.getType().equals(type))
                return payWayTmp;
        }
        return null;
	}
	
	public static PayWayEnum getEnumByDesc(String desc){
		if (desc == null)
            return null;
        for (PayWayEnum payWayTmp : PayWayEnum.values()) {
            if (payWayTmp.getDesc().equals(desc))
                return payWayTmp;
        }
        return null;
	}
	
}


