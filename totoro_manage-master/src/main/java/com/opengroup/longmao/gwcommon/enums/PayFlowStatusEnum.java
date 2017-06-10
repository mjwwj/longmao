package com.opengroup.longmao.gwcommon.enums;
/**
 * 支付流水状态枚举
 * @author
 *
 */
public enum PayFlowStatusEnum {
	//val, name
	UN_PAY(Short.parseShort("1"),"未支付"),
	ING_PAY(Short.parseShort("2"),"支付中"),
	IS_PAY(Short.parseShort("3"),"已支付"),
	FAIL_PAY(Short.parseShort("4"),"支付失败"),
	;
	private Short type;
	private String desc;

	private PayFlowStatusEnum (Short type,String desc) {
		this.type = type;
		this.desc = desc;
	}
	
	public Short getType() {
		return type;
	}
	public String getDesc() {
		return desc;
	}
		
	public static PayFlowStatusEnum getEnumByNumber(Short type){
		if (type == null)
            return null;
        for (PayFlowStatusEnum payFlowStatusTmp : PayFlowStatusEnum.values()) {
            if (payFlowStatusTmp.getType().equals(type))
                return payFlowStatusTmp;
        }
        return null;
	}
	
	public static PayFlowStatusEnum getEnumByDesc(String desc){
		if (desc == null)
            return null;
        for (PayFlowStatusEnum payFlowStatusTmp : PayFlowStatusEnum.values()) {
            if (payFlowStatusTmp.getDesc().equals(desc))
                return payFlowStatusTmp;
        }
        return null;
	}
	
}


