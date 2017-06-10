package com.opengroup.longmao.gwcommon.enums;
/**
 * 加减
 * @author
 *
 */
public enum AddOrSubtractEnum {
	//val, name
    ADD(Short.parseShort("1"),"增加"),
    SUBTRACT(Short.parseShort("-1"),"减少常"),
	;
	private Short val;
	private String desc;

	private AddOrSubtractEnum (Short val,String desc) {
		this.val = val;
		this.desc = desc;
	}
	
	public Short getVal() {
		return val;
	}
	public String getDesc() {
		return desc;
	}
		
	public static AddOrSubtractEnum getEnumByNumber(Short val){
		if (val == null)
            return null;
        for (AddOrSubtractEnum tSORNOTEnum : AddOrSubtractEnum.values()) {
            if (tSORNOTEnum.getVal().equals(val))
                return tSORNOTEnum;
        }
        return null;
	}
	
	public static AddOrSubtractEnum getEnumByDesc(String desc){
		if (desc == null)
            return null;
        for (AddOrSubtractEnum tSORNOTEnum : AddOrSubtractEnum.values()) {
            if (tSORNOTEnum.getDesc().equals(desc))
                return tSORNOTEnum;
        }
        return null;
	}
	
}


