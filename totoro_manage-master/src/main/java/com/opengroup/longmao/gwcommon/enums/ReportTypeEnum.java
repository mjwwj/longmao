package com.opengroup.longmao.gwcommon.enums;
/**
 * 开盘状态
 * @author
 *
 */
public enum ReportTypeEnum {
	//val, name
	ZERO(Short.parseShort("0"),"广告欺骗"),
	ONE(Short.parseShort("1"),"淫秽色情"),
	TWO(Short.parseShort("2"),"骚扰谩骂"),
	THREE(Short.parseShort("3"),"反动政治"),
	FOUR(Short.parseShort("4"),"虚假竞猜"),
	FIVE(Short.parseShort("5"),"其他内容")
	;
	private Short val;
	private String desc;

	private ReportTypeEnum (Short val,String desc) {
		this.val = val;
		this.desc = desc;
	}
	
	public Short getVal() {
		return val;
	}
	public String getDesc() {
		return desc;
	}
		
	public static ReportTypeEnum getEnumByNumber(Short val){
		if (val == null)
            return null;
        for (ReportTypeEnum tSORNOTEnum : ReportTypeEnum.values()) {
            if (tSORNOTEnum.getVal().equals(val))
                return tSORNOTEnum;
        }
        return null;
	}
	
	public static ReportTypeEnum getEnumByDesc(String desc){
		if (desc == null)
            return null;
        for (ReportTypeEnum tSORNOTEnum : ReportTypeEnum.values()) {
            if (tSORNOTEnum.getDesc().equals(desc))
                return tSORNOTEnum;
        }
        return null;
	}
	
}


