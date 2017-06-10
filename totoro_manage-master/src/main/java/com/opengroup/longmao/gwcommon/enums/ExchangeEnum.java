package com.opengroup.longmao.gwcommon.enums;
/**
 * 是否
 * @author
 *
 */
public enum ExchangeEnum {
	//val, name english
	ALIPAY(Short.parseShort("1"),"支付宝","alipay"),
	QB(Short.parseShort("2"),"Q币","qb"),
	COUPON(Short.parseShort("3"),"优惠券","coupon"),
	OBJECT(Short.parseShort("4"),"实物","object"),
	PHONEBILL(Short.parseShort("5"),"话费","phonebill"),
	PHONEFLOW(Short.parseShort("6"),"流量","phoneflow"),
	VIRTUAL(Short.parseShort("7"),"虚拟商品","virtual"),
	TURNTABLE(Short.parseShort("8"),"大转盘","turntable"),
	SINGLELOTTERY(Short.parseShort("9"),"单品抽奖","singleLottery"),
	HDTOOLLOTTERY(Short.parseShort("10"),"活动抽奖","hdtoolLottery"),
	HTOOL(Short.parseShort("11"),"新活动抽奖","htool"),
	MANUALLOTTERY(Short.parseShort("12"),"手动开奖","manualLottery"),
	GAMELOTTERY(Short.parseShort("13"),"游戏","gameLottery"),
	NGAMELOTTERY(Short.parseShort("14"),"新游戏","ngameLottery"),
	QUESTIONLOTTERY(Short.parseShort("15"),"答题","questionLottery"),
	QUIZZLOTTERY(Short.parseShort("16"),"测试题","quizzLottery"),
	GUESSLOTTERY(Short.parseShort("17"),"竞猜","guessLottery"),
	;
	private Short type;
	private String desc;
	private String code;

	private ExchangeEnum (Short type,String desc,String code) {
		this.type = type;
		this.desc = desc;
		this.code = code;
	}
	
	public Short getType() {
		return type;
	}
	public String getDesc() {
		return desc;
	}
		
	public String getCode() {
		return code;
	}

	public static ExchangeEnum getEnumByNumber(Short type){
		if (type == null)
            return null;
        for (ExchangeEnum tSORNOTEnum : ExchangeEnum.values()) {
            if (tSORNOTEnum.getType().equals(type))
                return tSORNOTEnum;
        }
        return null;
	}
	
	public static ExchangeEnum getEnumByDesc(String desc){
		if (desc == null)
            return null;
        for (ExchangeEnum tSORNOTEnum : ExchangeEnum.values()) {
            if (tSORNOTEnum.getDesc().equals(desc))
                return tSORNOTEnum;
        }
        return null;
	}
	
	public static ExchangeEnum getEnumByCode(String code){
		if (code == null)
            return null;
        for (ExchangeEnum tSORNOTEnum : ExchangeEnum.values()) {
            if (tSORNOTEnum.getCode().equals(code))
                return tSORNOTEnum;
        }
        return null;
	}
}


