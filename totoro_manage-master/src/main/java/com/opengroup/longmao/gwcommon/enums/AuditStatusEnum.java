package com.opengroup.longmao.gwcommon.enums;
/**
 * 支付方式枚举
 * @author
 *
 */
public enum AuditStatusEnum {
	//val, name
	UN_APPROVE(Short.parseShort("1"),"待审核"),
	APPROVE_ING(Short.parseShort("2"),"审核中"),
	APPROVE_SUCCESS(Short.parseShort("3"),"审核通过"),
	APPROVE_FAIL(Short.parseShort("4"),"审核失败");
	private Short type;
	private String desc;

	private AuditStatusEnum (Short type,String desc) {
		this.type = type;
		this.desc = desc;
	}
	
	public Short getType() {
		return type;
	}
	public String getDesc() {
		return desc;
	}
		
	public static AuditStatusEnum getEnumByNumber(Short type){
		if (type == null)
            return null;
        for (AuditStatusEnum payWayTmp : AuditStatusEnum.values()) {
            if (payWayTmp.getType().equals(type))
                return payWayTmp;
        }
        return null;
	}
	
	public static AuditStatusEnum getEnumByDesc(String desc){
		if (desc == null)
            return null;
        for (AuditStatusEnum payWayTmp : AuditStatusEnum.values()) {
            if (payWayTmp.getDesc().equals(desc))
                return payWayTmp;
        }
        return null;
	}
	
}


