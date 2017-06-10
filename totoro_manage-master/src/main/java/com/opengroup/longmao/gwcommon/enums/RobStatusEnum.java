package com.opengroup.longmao.gwcommon.enums;
/**
 * 开盘状态
 * @author
 *
 */
public enum RobStatusEnum {
	//val, name0：未开启；1：抢庄中；2：抢庄结束
	ZERO(Short.parseShort("0"),"未开启"),
	ONE(Short.parseShort("1"),"抢庄中"),
	TWO(Short.parseShort("2"),"抢庄结束");
	private Short val;
	private String desc;

	private RobStatusEnum (Short val,String desc) {
		this.val = val;
		this.desc = desc;
	}
	
	public Short getVal() {
		return val;
	}
	public String getDesc() {
		return desc;
	}
		
	public static RobStatusEnum getEnumByNumber(Short val){
		if (val == null)
            return null;
        for (RobStatusEnum tSORNOTEnum : RobStatusEnum.values()) {
            if (tSORNOTEnum.getVal().equals(val))
                return tSORNOTEnum;
        }
        return null;
	}
	
	public static RobStatusEnum getEnumByDesc(String desc){
		if (desc == null)
            return null;
        for (RobStatusEnum tSORNOTEnum : RobStatusEnum.values()) {
            if (tSORNOTEnum.getDesc().equals(desc))
                return tSORNOTEnum;
        }
        return null;
	}
	
}


