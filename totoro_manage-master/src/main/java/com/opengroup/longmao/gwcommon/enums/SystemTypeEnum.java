package com.opengroup.longmao.gwcommon.enums;

/**
 * 系统类型
 * @authors liunan
 * @time 
 */
public enum SystemTypeEnum {
	IOS(Short.parseShort("1"),"IOS"),
	ANDROID(Short.parseShort("2"),"Android"),
	;
	private Short type;
	private String desc;
	
	private SystemTypeEnum (Short type,String desc) {
		this.type = type;
		this.desc = desc;
	}
	public Short getType() {
		return type;
	}
	public String getDesc() {
		return desc;
	}
		
	public static SystemTypeEnum getEnumByType(Short type){
		if (type == null)
            return null;
        for (SystemTypeEnum systemTypeEnum : SystemTypeEnum.values()) {
            if (systemTypeEnum.getType().equals(type))
                return systemTypeEnum;
        }
        return null;
	}
}
