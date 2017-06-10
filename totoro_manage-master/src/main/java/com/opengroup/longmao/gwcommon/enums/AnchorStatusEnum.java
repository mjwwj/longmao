package com.opengroup.longmao.gwcommon.enums;

/**
 * @ClassName: AnchorTypeEnum 
 * @Description: 主播状态枚举
 * @author Mr.Zhu
 */
public enum AnchorStatusEnum {
	IDENTITY_PASS(Short.parseShort("0"), "实名认证未通过"),
	IDENTITY_NO_PASS(Short.parseShort("1"), "实名认证已通过"),
	APPLY(Short.parseShort("2"), "申请主播认证"),
	ANCHOR_PASS(Short.parseShort("3"), "主播认证未通过"),
	ANCHOR_NO_PASS(Short.parseShort("4"), "主播认证已通过"),
	SHUT_LIVE(Short.parseShort("0"), "禁播"),
	OPEN_LIVE(Short.parseShort("1"), "开播");
	private Short type;
	private String explain;

	private AnchorStatusEnum (Short type,String explain) {
		this.type = type;
		this.explain = explain;
	}
	
	public Short getType() {
		return type;
	}
	public String getExplain() {
		return explain;
	}
		
	public static AnchorStatusEnum getEnumByType(Short type){
		if (type == null)
            return null;
        for (AnchorStatusEnum anchorStatusEnum : AnchorStatusEnum.values()) {
            if (anchorStatusEnum.getType().equals(type))
                return anchorStatusEnum;
        }
        return null;
	}
	
	public static AnchorStatusEnum getEnumByExplain(String explain){
		if (explain == null)
            return null;
        for (AnchorStatusEnum anchorStatusEnum : AnchorStatusEnum.values()) {
            if (anchorStatusEnum.getExplain().equals(explain))
                return anchorStatusEnum;
        }
        return null;
	}
	
}


