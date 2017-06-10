package com.opengroup.longmao.gwcommon.enums;
/**
 * 开盘状态
 * @author
 *
 */
public enum GuessStatusEnum {
	//val, name
	ZERO(Short.parseShort("0"),"未开盘"),
	ONE(Short.parseShort("1"),"已开盘"),
	TWO(Short.parseShort("2"),"已封盘"),
	THREE(Short.parseShort("3"),"已结算"),
	FOUR(Short.parseShort("4"),"流局"),
	FIVE(Short.parseShort("5"),"PK审核未通过")
	;
	private Short val;
	private String desc;

	private GuessStatusEnum (Short val,String desc) {
		this.val = val;
		this.desc = desc;
	}
	
	public Short getVal() {
		return val;
	}
	public String getDesc() {
		return desc;
	}
		
	public static GuessStatusEnum getEnumByNumber(Short val){
		if (val == null)
            return null;
        for (GuessStatusEnum tSORNOTEnum : GuessStatusEnum.values()) {
            if (tSORNOTEnum.getVal().equals(val))
                return tSORNOTEnum;
        }
        return null;
	}
	
	public static GuessStatusEnum getEnumByDesc(String desc){
		if (desc == null)
            return null;
        for (GuessStatusEnum tSORNOTEnum : GuessStatusEnum.values()) {
            if (tSORNOTEnum.getDesc().equals(desc))
                return tSORNOTEnum;
        }
        return null;
	}
	
}


