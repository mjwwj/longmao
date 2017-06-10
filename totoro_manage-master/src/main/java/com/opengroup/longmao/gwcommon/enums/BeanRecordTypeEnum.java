package com.opengroup.longmao.gwcommon.enums;
/**
 * 龙猫豆流水类型
 * @author
 *
 */
public enum BeanRecordTypeEnum {
	//val, name
	COIN_IN_BEAN(0, "币换豆"),
    GUESSING(1,"竞猜"),
    PK(2,"发起PK"),
	GIFT_GIVE(3, "送礼消费"),
	GIVE(4, "赠送"),
	EXCHANGE(5, "兑换"),
	ERR_RETURN(6, "交易失败返还"),
	REGISTER(7, "注册"),
	REGISTER_GIVE(8, "注册送"),
	BEAN_IN_COIN(9, "豆换币"),
	;
	private Integer val;
	private String desc;

	private BeanRecordTypeEnum (Integer val,String desc) {
		this.val = val;
		this.desc = desc;
	}
	
	public Integer getVal() {
		return val;
	}
	public String getDesc() {
		return desc;
	}
		
	public static BeanRecordTypeEnum getEnumByNumber(Integer val){
		if (val == null)
            return null;
        for (BeanRecordTypeEnum tSORNOTEnum : BeanRecordTypeEnum.values()) {
            if (tSORNOTEnum.getVal().equals(val))
                return tSORNOTEnum;
        }
        return null;
	}
	
	public static BeanRecordTypeEnum getEnumByDesc(String desc){
		if (desc == null)
            return null;
        for (BeanRecordTypeEnum tSORNOTEnum : BeanRecordTypeEnum.values()) {
            if (tSORNOTEnum.getDesc().equals(desc))
                return tSORNOTEnum;
        }
        return null;
	}
	
}


