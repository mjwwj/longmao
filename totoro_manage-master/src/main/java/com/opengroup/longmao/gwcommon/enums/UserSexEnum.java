package com.opengroup.longmao.gwcommon.enums;
/**
 * 用户性别状态枚举
 * @author
 *
 */
public enum UserSexEnum {
	//val, name
	SEX_MAN(Short.parseShort("1"),"男"),
	SEX_WOMEN(Short.parseShort("2"),"女"),
	;
	private Short type;
	private String desc;

	private UserSexEnum (Short type,String desc) {
		this.type = type;
		this.desc = desc;
	}
	
	public Short getType() {
		return type;
	}
	public String getDesc() {
		return desc;
	}
		
	public static UserSexEnum getEnumByNumber(Short type){
		if (type == null)
            return null;
        for (UserSexEnum userSexEnum : UserSexEnum.values()) {
            if (userSexEnum.getType().equals(type))
                return userSexEnum;
        }
        return null;
	}
	
	public static UserSexEnum getEnumByDesc(String desc){
		if (desc == null)
            return null;
        for (UserSexEnum userSexEnum : UserSexEnum.values()) {
            if (userSexEnum.getDesc().equals(desc))
                return userSexEnum;
        }
        return null;
	}
	
}