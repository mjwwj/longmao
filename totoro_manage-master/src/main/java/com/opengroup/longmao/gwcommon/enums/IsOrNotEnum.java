package com.opengroup.longmao.gwcommon.enums;
/**
 * 是否
 * @author
 *
 */
public enum IsOrNotEnum {
	//val, name
	YES(Short.parseShort("1"),"是"),
	NO(Short.parseShort("2"),"否"),
	;
	private Short type;
	private String desc;

	private IsOrNotEnum (Short type,String desc) {
		this.type = type;
		this.desc = desc;
	}
	
	public Short getType() {
		return type;
	}
	public String getDesc() {
		return desc;
	}
		
	public static IsOrNotEnum getEnumByNumber(Short type){
		if (type == null)
            return null;
        for (IsOrNotEnum tSORNOTEnum : IsOrNotEnum.values()) {
            if (tSORNOTEnum.getType().equals(type))
                return tSORNOTEnum;
        }
        return null;
	}
	
	public static IsOrNotEnum getEnumByDesc(String desc){
		if (desc == null)
            return null;
        for (IsOrNotEnum tSORNOTEnum : IsOrNotEnum.values()) {
            if (tSORNOTEnum.getDesc().equals(desc))
                return tSORNOTEnum;
        }
        return null;
	}
	
}


