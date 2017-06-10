package com.opengroup.longmao.gwcommon.enums;
/**
 * 用户类型：0：普通用户；1：主播；2：企业
 * @author
 *
 */
public enum UserTypeEnum {
	//用户类型：0：普通用户；1：主播；2：企业
	ORDINARY(0,"普通用户"),
	ANCHOR(1,"主播"),
	ENTERPRISE(2,"企业"),
	;
	private Integer val;
	private String desc;

	private UserTypeEnum (Integer val,String desc) {
		this.val = val;
		this.desc = desc;
	}
	
	public Integer getVal() {
		return val;
	}
	public String getDesc() {
		return desc;
	}
		
	public static UserTypeEnum getEnumByNumber(Short val){
		if (val == null)
            return null;
        for (UserTypeEnum tSORNOTEnum : UserTypeEnum.values()) {
            if (tSORNOTEnum.getVal().equals(val))
                return tSORNOTEnum;
        }
        return null;
	}
	
	public static UserTypeEnum getEnumByDesc(String desc){
		if (desc == null)
            return null;
        for (UserTypeEnum tSORNOTEnum : UserTypeEnum.values()) {
            if (tSORNOTEnum.getDesc().equals(desc))
                return tSORNOTEnum;
        }
        return null;
	}
	
}


