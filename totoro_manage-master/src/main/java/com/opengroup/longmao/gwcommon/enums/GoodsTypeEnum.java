package com.opengroup.longmao.gwcommon.enums;
/**
 * 商品类型枚举
 * @author
 *
 */
public enum GoodsTypeEnum {
	//typeId , name
	LT_GTYPE_RECHARGE(1L,"联通卡"),
	YD_GTYPE_RECHARGE(2L,"移动卡"),
	DX_GTYPE_RECHARGE(3L,"电信卡"),
	WX_GTYPE_RECHARGE(4L,"微信公众号充值"),
	ANDROID_APP_RECHARGE(6L,"安卓app充值"),
	DB_GTYPE_RECHARGE(7L,"龙猫豆兑换"),
	HY_GTYPE_RECHARGE(8L,"话费充值"),
	CALORIE_GTYPE_RECHARGE(9L,"卡路里兑换RMB"),
	ALIPAY_GTYPE_RECHARGE(10L,"支付宝"),;
	private Long type;
	private String desc;

	private GoodsTypeEnum (Long type,String desc) {
		this.type = type;
		this.desc = desc;
	}
	
	public Long getType() {
		return type;
	}
	public String getDesc() {
		return desc;
	}
		
	public static GoodsTypeEnum getEnumByNumber(Long type){
		if (type == null)
            return null;
        for (GoodsTypeEnum goodsTypeTmp : GoodsTypeEnum.values()) {
            if (goodsTypeTmp.getType().equals(type))
                return goodsTypeTmp;
        }
        return null;
	}
	
	public static GoodsTypeEnum getEnumByDesc(String desc){
		if (desc == null)
            return null;
        for (GoodsTypeEnum goodsTypeTmp : GoodsTypeEnum.values()) {
            if (goodsTypeTmp.getDesc().equals(desc))
                return goodsTypeTmp;
        }
        return null;
	}
	
}


