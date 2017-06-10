package com.opengroup.longmao.gwcommon.enums;
/**
 * 竞猜结果
 * @author
 *
 */
public enum GuessFinalResultEnum {
	//val, name
	GUESS_FINAL_RESULT_FLOW(Short.parseShort("0"),"流局"),
	GUESS_FINAL_RESULT_A(Short.parseShort("1"),"A"),
	GUESS_FINAL_RESULT_B(Short.parseShort("2"),"B"),
	GUESS_FINAL_RESULT_C(Short.parseShort("3"),"C"),
	GUESS_FINAL_RESULT_D(Short.parseShort("4"),"D")
	;
	private Short val;
	private String desc;


	private GuessFinalResultEnum(Short val, String desc) {
		this.val = val;
		this.desc = desc;
	}
	public Short getVal() {
		return val;
	}
	public String getDesc() {
		return desc;
	}
	public static GuessFinalResultEnum getEnumByNumber(Short val){
		if (val == null)
            return null;
        for (GuessFinalResultEnum tSORNOTEnum : GuessFinalResultEnum.values()) {
            if (tSORNOTEnum.getVal().equals(val))
                return tSORNOTEnum;
        }
        return null;
	}
	
	public static GuessFinalResultEnum getEnumByDesc(String desc){
		if (desc == null)
            return null;
        for (GuessFinalResultEnum tSORNOTEnum : GuessFinalResultEnum.values()) {
            if (tSORNOTEnum.getDesc().equals(desc))
                return tSORNOTEnum;
        }
        return null;
	}
	
}


