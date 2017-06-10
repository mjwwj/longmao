package com.opengroup.longmao.gwcommon.enums;
/**
 * 渠道枚举
 * @author
 *
 */
public enum ChlInfoEnum {
	//chlId, name
	CHL_DUIBA_SHOP(1L,"兑吧商城"),
	CHL_WEIXIN_RECHARGE(2L,"微信公众号充值"),
	CHL_SDK_RECHARGE(3L,"融合sdk"),
	CHL_PLATFORM_RECHARGE(4L,"平台币充值"),
	CHL_ANDROID_APP_RECHARGE(6L,"安卓app充值"),
	CHL_HUYI(7L,"互亿无线"),
	CHL_TOTORO_CALORIE(8L,"龙猫直播卡路里"),
	CHL_ALIPAY(9L,"支付宝"),;
	private Long type;
	private String desc;

	private ChlInfoEnum (Long type,String desc) {
		this.type = type;
		this.desc = desc;
	}
	
	public Long getType() {
		return type;
	}
	public String getDesc() {
		return desc;
	}
		
	public static ChlInfoEnum getEnumByNumber(Long type){
		if (type == null)
            return null;
        for (ChlInfoEnum chlInfoTmp : ChlInfoEnum.values()) {
            if (chlInfoTmp.getType().equals(type))
                return chlInfoTmp;
        }
        return null;
	}
	
	public static ChlInfoEnum getEnumByDesc(String desc){
		if (desc == null)
            return null;
        for (ChlInfoEnum chlInfoTmp : ChlInfoEnum.values()) {
            if (chlInfoTmp.getDesc().equals(desc))
                return chlInfoTmp;
        }
        return null;
	}
	
}


