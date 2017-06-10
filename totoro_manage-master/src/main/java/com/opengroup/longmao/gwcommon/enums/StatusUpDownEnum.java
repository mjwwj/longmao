package com.opengroup.longmao.gwcommon.enums;
/**
 * 是否删除
 * @author
 *
 */
public enum StatusUpDownEnum {
	//val, name
	UP(Short.parseShort("1"),"上架"),
	DOWN(Short.parseShort("2"),"下架"),
	;
	private Short val;
	private String desc;

	private StatusUpDownEnum (Short val,String desc) {
		this.val = val;
		this.desc = desc;
	}
	
	public Short getVal() {
		return val;
	}
	public String getDesc() {
		return desc;
	}
		
	public static StatusUpDownEnum getEnumByNumber(Short val){
		if (val == null)
            return null;
        for (StatusUpDownEnum tSORNOTEnum : StatusUpDownEnum.values()) {
            if (tSORNOTEnum.getVal().equals(val))
                return tSORNOTEnum;
        }
        return null;
	}
	
	public static StatusUpDownEnum getEnumByDesc(String desc){
		if (desc == null)
            return null;
        for (StatusUpDownEnum tSORNOTEnum : StatusUpDownEnum.values()) {
            if (tSORNOTEnum.getDesc().equals(desc))
                return tSORNOTEnum;
        }
        return null;
	}
	
}


