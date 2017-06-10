package com.opengroup.longmao.gwcommon.enums;

/**
 * @ClassName: AnchorTypeEnum 
 * @Description: 主播类型枚举
 * @author Mr.Zhu
 */
public enum AnchorTypeEnum {
	PEOPLE_ANCHOR(Short.parseShort("0"), "全民主播"),
	FAMILY_ANCHOR(Short.parseShort("1"), "家族主播"),
	;
	private Short type;
	private String explain;

	private AnchorTypeEnum (Short type,String explain) {
		this.type = type;
		this.explain = explain;
	}
	
	public Short getType() {
		return type;
	}
	public String getExplain() {
		return explain;
	}
		
	public static AnchorTypeEnum getEnumByType(Short type){
		if (type == null)
            return null;
        for (AnchorTypeEnum anchorTypeEnum : AnchorTypeEnum.values()) {
            if (anchorTypeEnum.getType().equals(type))
                return anchorTypeEnum;
        }
        return null;
	}
	
	public static AnchorTypeEnum getEnumByExplain(String explain){
		if (explain == null)
            return null;
        for (AnchorTypeEnum anchorTypeEnum : AnchorTypeEnum.values()) {
            if (anchorTypeEnum.getExplain().equals(explain))
                return anchorTypeEnum;
        }
        return null;
	}
	
}


