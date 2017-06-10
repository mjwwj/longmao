package com.opengroup.longmao.gwcommon.enums;
/**
 * 退款状态枚举
 * @author
 *
 */
public enum RefundStatusEnum {
	//val, name
	NO_REFUND(Short.parseShort("0"),"无需退款"),
	UN_REFUND(Short.parseShort("1"),"待退款"),
	ING_REFUND(Short.parseShort("2"),"退款中"),
	IS_REFUND(Short.parseShort("3"),"退款成功"),
	FAIL_REFUND(Short.parseShort("4"),"退款失败"),
	;
	private Short type;
	private String desc;

	private RefundStatusEnum (Short type,String desc) {
		this.type = type;
		this.desc = desc;
	}
	
	public Short getType() {
		return type;
	}
	public String getDesc() {
		return desc;
	}
		
	public static RefundStatusEnum getEnumByNumber(Short type){
		if (type == null)
            return null;
        for (RefundStatusEnum refundStatusTmp : RefundStatusEnum.values()) {
            if (refundStatusTmp.getType().equals(type))
                return refundStatusTmp;
        }
        return null;
	}
	
	public static RefundStatusEnum getEnumByDesc(String desc){
		if (desc == null)
            return null;
        for (RefundStatusEnum refundStatusTmp : RefundStatusEnum.values()) {
            if (refundStatusTmp.getDesc().equals(desc))
                return refundStatusTmp;
        }
        return null;
	}
	
}


