package com.opengroup.longmao.gwcommon.enums;
/**
 * 是否正常
 * @author
 *
 */
public enum IsNormalEnum {
	//val, name
    YES(Short.parseShort("1"),"正常"),
	NO(Short.parseShort("-1"),"不正常"),
	;
	private Short val;
	private String desc;

	private IsNormalEnum (Short val,String desc) {
		this.val = val;
		this.desc = desc;
	}
	
	public Short getVal() {
		return val;
	}
	public String getDesc() {
		return desc;
	}
		
	public static IsNormalEnum getEnumByNumber(Short val){
		if (val == null)
            return null;
        for (IsNormalEnum tSORNOTEnum : IsNormalEnum.values()) {
            if (tSORNOTEnum.getVal().equals(val))
                return tSORNOTEnum;
        }
        return null;
	}
	
	public static IsNormalEnum getEnumByDesc(String desc){
		if (desc == null)
            return null;
        for (IsNormalEnum tSORNOTEnum : IsNormalEnum.values()) {
            if (tSORNOTEnum.getDesc().equals(desc))
                return tSORNOTEnum;
        }
        return null;
	}
	
}


