package com.opengroup.longmao.gwcommon.enums;
/**
 * 订单优惠枚举
 * @author
 *
 */
public enum OrderDiscountWayEnum {
	//val, name
	UN(Short.parseShort("1"),"无优惠"),
	DISCOUNT(Short.parseShort("2"),"打折"),
	FULL_CUT(Short.parseShort("3"),"满减"),
	COUPON(Short.parseShort("4"),"优惠券抵扣"),
	INTEGRAL(Short.parseShort("5"),"送积分"),
	;
	private Short type;
	private String desc;

	private OrderDiscountWayEnum (Short type,String desc) {
		this.type = type;
		this.desc = desc;
	}
	
	public Short getType() {
		return type;
	}
	public String getDesc() {
		return desc;
	}
		
	public static OrderDiscountWayEnum getEnumByNumber(Short type){
		if (type == null)
            return null;
        for (OrderDiscountWayEnum tSORNOTEnum : OrderDiscountWayEnum.values()) {
            if (tSORNOTEnum.getType().equals(type))
                return tSORNOTEnum;
        }
        return null;
	}
	
	public static OrderDiscountWayEnum getEnumByDesc(String desc){
		if (desc == null)
            return null;
        for (OrderDiscountWayEnum tSORNOTEnum : OrderDiscountWayEnum.values()) {
            if (tSORNOTEnum.getDesc().equals(desc))
                return tSORNOTEnum;
        }
        return null;
	}
	
}


