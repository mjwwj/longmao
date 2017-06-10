package com.opengroup.longmao.gwcommon.enums;


/**
 * 商品类别枚举
 * @author
 *
 */
public enum GoodsCategoryEnum {
	//cateGoryId , typeName , categoryName , price, typeId
	GCTGY_RECHARGE_LX_30(1L,"联通卡","30元","30",1L),
	GCTGY_RECHARGE_LX_50(2L,"联通卡","50元","50",1L),
	GCTGY_RECHARGE_LX_100(3L,"联通卡","100元","100",1L),
	GCTGY_RECHARGE_LX_300(4L,"联通卡","200元","200",1L),
	GCTGY_RECHARGE_LX_500(5L,"联通卡","500元","500",1L),
	
	GCTGY_RECHARGE_YD_30(6L,"移动卡","30元","30",2L),
	GCTGY_RECHARGE_YD_50(7L,"移动卡","50元","50",2L),
	GCTGY_RECHARGE_YD_100(8L,"移动卡","100元","100",2L),
	GCTGY_RECHARGE_YD_300(9L,"移动卡","200元","200",2L),
	GCTGY_RECHARGE_YD_500(10L,"移动卡","500元","500",2L),
	
	GCTGY_RECHARGE_DX_30(11L,"电信卡","30元","30",3L),
	GCTGY_RECHARGE_DX_50(12L,"电信卡","50元","50",3L),
	GCTGY_RECHARGE_DX_100(13L,"电信卡","100元","100",3L),
	GCTGY_RECHARGE_DX_300(14L,"电信卡","200元","200",3L),
	GCTGY_RECHARGE_DX_500(15L,"电信卡","500元","500",3L),
	
	GCTGY_RECHARGE_WX_60(16L,"微信公众号充值","60币","6",4L),
	GCTGY_RECHARGE_WX_300(17L,"微信公众号充值","300币","30",4L),
	GCTGY_RECHARGE_WX_980(18L,"微信公众号充值","980币","98",4L),
	GCTGY_RECHARGE_WX_29800(19L,"微信公众号充值","2980币","298",4L),
	GCTGY_RECHARGE_WX_5880(20L,"微信公众号充值","5880币","588",4L),
	GCTGY_RECHARGE_WX_19980(21L,"微信公众号充值","19980币","1998",4L),
	GCTGY_RECHARGE_WX_00(22L,"微信公众号充值","自定义","1",4L),
	
	GCTGY_RECHARGE_ANDROID_APP_30(23L,"安卓app充值","300","30",6L),
	GCTGY_RECHARGE_ANDROID_APP_980(24L,"安卓app充值","980","98",6L),
	GCTGY_RECHARGE_ANDROID_APP_1980(25L,"安卓app充值","1980","198",6L),
	GCTGY_RECHARGE_ANDROID_APP_3280(26L,"安卓app充值","3280","328",6L),
	GCTGY_RECHARGE_ANDROID_APP_6480(27L,"安卓app充值","6480","648",6L),
	GCTGY_RECHARGE_ANDROID_APP_00(28L,"安卓app充值","自定义","1",6L),
	GCTGY_RECHARGE_DUIBA(29L,"兑吧商品类别","自定义","1",7L),
	GCTGY_RECHARGE_HUYI(30L,"互亿无线话费充值","自定义","1",8L),
	GCTGY_RECHARGE_CALORIE(31L,"卡路里兑换RMB","自定义","1",9L),
	GCTGY_RECHARGE_ALIPAY(32L,"支付宝","自定义","1",10L),;;
	//类别id
	private Long number;
	//类型名称
	private String desc;
	//类别名称
	private String name;
	//面额
	private String price;
	//类型id
	private Long type;
	
//	public static void main(String[] args) {
//		GoodsCategoryEnum enumm = getEnumByTypeAndPrice(2L, "500");
//		GoodsCategoryEnum enumm2 = getEnumByNumber(4L);
//		System.out.println(enumm.getDesc());
//		System.out.println(enumm2.getDesc());
//	}
	
	private GoodsCategoryEnum (Long number,String desc,String name,String price,Long type) {
		this.number = number;
		this.desc = desc;
		this.name = name;
		this.price = price;
		this.type = type;
	}
	
	public Long getNumber() {
		return number;
	}
	public String getDesc() {
		return desc;
	}
	public Long getType() {
		return type;
	}
	public String getName() {
		return name;
	}
	public String getPrice() {
		return price;
	}

	public static GoodsCategoryEnum getEnumByNumber(Long number){
		if (number == null)
            return null;
        for (GoodsCategoryEnum goodsCategoryTmp : GoodsCategoryEnum.values()) {
            if (goodsCategoryTmp.getNumber().equals(number))
                return goodsCategoryTmp;
        }
        return null;
	}
	
	/*public static GoodsCategoryEnum getEnumByDesc(String desc){
		if (desc == null)
            return null;
        for (GoodsCategoryEnum goodsCategoryTmp : GoodsCategoryEnum.values()) {
            if (goodsCategoryTmp.getDesc().equals(desc))
                return goodsCategoryTmp;
        }
        return null;
	}*/
	
	public static GoodsCategoryEnum getEnumByName(String name){
		if (name == null)
            return null;
        for (GoodsCategoryEnum goodsCategoryTmp : GoodsCategoryEnum.values()) {
            if (goodsCategoryTmp.getName().equals(name))
                return goodsCategoryTmp;
        }
        return null;
	}
	
	public static GoodsCategoryEnum getEnumByTypeAndPrice(Long type,String price){
		if (type == null)
            return null;
		if (price == null)
            return null;
        for (GoodsCategoryEnum goodsCategoryTmp : GoodsCategoryEnum.values()) {
            if (goodsCategoryTmp.getType()==type&&goodsCategoryTmp.getPrice().equals(price))
                return goodsCategoryTmp;
        }
        return null;
	}
	
}


