package com.opengroup.longmao.gwcommon.enums;
/**
 * 支付状态枚举
 * @author
 *
 */
public enum PaymentStatusEnum {
	//val, name
	UN_PAY(Short.parseShort("1"),"待付款"),
	ING_PAY(Short.parseShort("2"),"付款中"),
	IS_PAY(Short.parseShort("3"),"已付款"),
	FAIL_PAY(Short.parseShort("4"),"付款失败"),
	;
	private Short type;
	private String desc;

	private PaymentStatusEnum (Short type,String desc) {
		this.type = type;
		this.desc = desc;
	}
	
	public Short getType() {
		return type;
	}
	public String getDesc() {
		return desc;
	}
		
	public static PaymentStatusEnum getEnumByNumber(Short type){
		if (type == null)
            return null;
        for (PaymentStatusEnum paymentStatusTmp : PaymentStatusEnum.values()) {
            if (paymentStatusTmp.getType().equals(type))
                return paymentStatusTmp;
        }
        return null;
	}
	
	public static PaymentStatusEnum getEnumByDesc(String desc){
		if (desc == null)
            return null;
        for (PaymentStatusEnum paymentStatusTmp : PaymentStatusEnum.values()) {
            if (paymentStatusTmp.getDesc().equals(desc))
                return paymentStatusTmp;
        }
        return null;
	}
	
}


