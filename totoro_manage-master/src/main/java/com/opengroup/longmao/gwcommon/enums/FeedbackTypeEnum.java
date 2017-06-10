package com.opengroup.longmao.gwcommon.enums;
/**
 * 开盘状态
 * @author
 *
 */
public enum FeedbackTypeEnum {
	//val, name
	ZERO(Short.parseShort("0"),"功能需求"),
	ONE(Short.parseShort("1"),"报告错误"),
	TWO(Short.parseShort("2"),"其他")
	;
	private Short val;
	private String desc;

	private FeedbackTypeEnum (Short val,String desc) {
		this.val = val;
		this.desc = desc;
	}
	
	public Short getVal() {
		return val;
	}
	public String getDesc() {
		return desc;
	}
		
	public static FeedbackTypeEnum getEnumByNumber(Short val){
		if (val == null)
            return null;
        for (FeedbackTypeEnum tSORNOTEnum : FeedbackTypeEnum.values()) {
            if (tSORNOTEnum.getVal().equals(val))
                return tSORNOTEnum;
        }
        return null;
	}
	
	public static FeedbackTypeEnum getEnumByDesc(String desc){
		if (desc == null)
            return null;
        for (FeedbackTypeEnum tSORNOTEnum : FeedbackTypeEnum.values()) {
            if (tSORNOTEnum.getDesc().equals(desc))
                return tSORNOTEnum;
        }
        return null;
	}
	
}


