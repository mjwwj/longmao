package com.opengroup.longmao.gwcommon.enums;
/**
 * 商品订单状态枚举
 * @author
 *
 */
public enum OrderStatusEnum {
	//val, name	根据实际情况可省略步骤
	UN_PAY(Short.parseShort("1"),"待付款"),
	IS_PAY(Short.parseShort("2"),"已付款，处理中"),
	HADNl_ING(Short.parseShort("3"),"处理中，待发货"),
	SUCCESS(Short.parseShort("4"),"交易成功"),
	FAIL(Short.parseShort("5"),"交易失败"),
	REFUND(Short.parseShort("6"),"退款"),
	;
	private Short type;
	private String desc;

	private OrderStatusEnum (Short type,String desc) {
		this.type = type;
		this.desc = desc;
	}
	
	public Short getType() {
		return type;
	}
	public String getDesc() {
		return desc;
	}
		
	public static OrderStatusEnum getEnumByNumber(Short type){
		if (type == null)
            return null;
        for (OrderStatusEnum orderStatusTmp : OrderStatusEnum.values()) {
            if (orderStatusTmp.getType().equals(type))
                return orderStatusTmp;
        }
        return null;
	}
	
	public static OrderStatusEnum getEnumByDesc(String desc){
		if (desc == null)
            return null;
        for (OrderStatusEnum orderStatusTmp : OrderStatusEnum.values()) {
            if (orderStatusTmp.getDesc().equals(desc))
                return orderStatusTmp;
        }
        return null;
	}
	
}


