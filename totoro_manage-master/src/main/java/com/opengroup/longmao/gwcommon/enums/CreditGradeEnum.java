package com.opengroup.longmao.gwcommon.enums;

/**
 * @ClassName: AnchorTypeEnum
 * @Description: 主播状态枚举
 * @author Mr.Zhu
 */
public enum CreditGradeEnum {
	DEFAULT_GRADE((short) 75, "良好", "0.50");
	private Short val;
	private String explain;
	private String ratio;

	private CreditGradeEnum(Short val, String explain, String ratio) {
		this.val = val;
		this.explain = explain;
		this.ratio = ratio;
	}

	public Short getVal() {
		return val;
	}

	public String getExplain() {
		return explain;
	}

	public String getRatio() {
		return ratio;
	}

	public void setRatio(String ratio) {
		this.ratio = ratio;
	}

	public static CreditGradeEnum getEnumByVal(Short val) {
		if (val == null)
			return null;
		for (CreditGradeEnum creditGradeEnum : CreditGradeEnum.values()) {
			if (creditGradeEnum.getVal().equals(val))
				return creditGradeEnum;
		}
		return null;
	}

	public static CreditGradeEnum getEnumByExplain(String explain) {
		if (explain == null)
			return null;
		for (CreditGradeEnum creditGradeEnum : CreditGradeEnum.values()) {
			if (creditGradeEnum.getExplain().equals(explain))
				return creditGradeEnum;
		}
		return null;
	}
	
	public static CreditGradeEnum getEnumByRatio(String ratio) {
		if (ratio == null)
			return null;
		for (CreditGradeEnum creditGradeEnum : CreditGradeEnum.values()) {
			if (creditGradeEnum.getRatio().equals(ratio))
				return creditGradeEnum;
		}
		return null;
	}

}
