package com.opengroup.longmao.gwcommon.enums;
/**
 * 商品订单发货状态枚举
 * @author
 *
 */
public enum DeliveryStatusEnum {
	//val, name
	UN_DELIVERY(Short.parseShort("1"),"待发货"),
	DELIVERY_ING(Short.parseShort("2"),"发货中"),
	IS_DELIVERY(Short.parseShort("3"),"已发货"),
	DELIVERY_FAIL(Short.parseShort("4"),"发货失败"),
	TASK_FAIL(Short.parseShort("5"), "处理失败")
	;
	private Short type;
	private String desc;

	private DeliveryStatusEnum (Short type,String desc) {
		this.type = type;
		this.desc = desc;
	}
	
	public Short getType() {
		return type;
	}
	public String getDesc() {
		return desc;
	}
		
	public static DeliveryStatusEnum getEnumByNumber(Short type){
		if (type == null)
            return null;
        for (DeliveryStatusEnum deliveryStatusTmp : DeliveryStatusEnum.values()) {
            if (deliveryStatusTmp.getType().equals(type))
                return deliveryStatusTmp;
        }
        return null;
	}
	
	public static DeliveryStatusEnum getEnumByDesc(String desc){
		if (desc == null)
            return null;
        for (DeliveryStatusEnum deliveryStatusTmp : DeliveryStatusEnum.values()) {
            if (deliveryStatusTmp.getDesc().equals(desc))
                return deliveryStatusTmp;
        }
        return null;
	}
	
}


