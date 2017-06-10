package com.opengroup.longmao.gwcommon.enums;
/**
 * 是否删除
 * @author
 *
 */
public enum FeedBackDealEnum {
	//val, name
	UN_DEAL(Short.parseShort("1"),"待处理"),
	DEAL(Short.parseShort("2"),"已处理"),
	;
	private Short val;
	private String desc;

	private FeedBackDealEnum (Short val,String desc) {
		this.val = val;
		this.desc = desc;
	}
	
	public Short getVal() {
		return val;
	}
	public String getDesc() {
		return desc;
	}
		
	public static FeedBackDealEnum getEnumByNumber(Short val){
		if (val == null)
            return null;
        for (FeedBackDealEnum tSORNOTEnum : FeedBackDealEnum.values()) {
            if (tSORNOTEnum.getVal().equals(val))
                return tSORNOTEnum;
        }
        return null;
	}
	
	public static FeedBackDealEnum getEnumByDesc(String desc){
		if (desc == null)
            return null;
        for (FeedBackDealEnum tSORNOTEnum : FeedBackDealEnum.values()) {
            if (tSORNOTEnum.getDesc().equals(desc))
                return tSORNOTEnum;
        }
        return null;
	}
	
}


