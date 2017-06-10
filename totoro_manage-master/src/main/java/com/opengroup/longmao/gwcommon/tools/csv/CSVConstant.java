package com.opengroup.longmao.gwcommon.tools.csv;

public class CSVConstant {
	// 文件的格式
	public static final String FILE_EXT = ".csv";
	// 文件最大为10M
	public static final Integer MAX_MB = 10485760;
	// 文件最大的行数
	public static final Integer MAX_LINE= 5000;
	//检查重复数据的最大数量，重复卡号太多，直接返回
	public static final Integer MAX_REPEAT_COUNT = 1;
	//文件必须是3列
	public static final Integer BINDS_COLUMNS = 3;
	//定义面额类型
	public static final String[] CARD_PRICE = {"30","50","100","200","500"};
	//文件保存路径(LINUX服务器专用)
	public static final String CSV_SAVE_PATH =System.getProperty("user.dir")+"/csvfile/" ;
	
	//联通联通充值卡序列号15位，密码19位
	public static final Integer LT_CARD_NO_LENGTH = 15;
	public static final Integer LT_CARD_PWD_LENGTH = 19;
	//移动充值卡序列号17位，密码18位
	public static final Integer YD_CARD_NO_LENGTH = 17;
	public static final Integer YD_CARD_PWD_LENGTH = 18;
	//电信充值卡序列号19位，密码18位
	public static final Integer DX_CARD_NO_LENGTH = 19;
	public static final Integer DX_CARD_PWD_LENGTH = 18;
	
	public static final String CSV_COD_0000 = "000";
	public static final String CSV_MSG_0000 = "数据处理成功";
	
	public static final String CSV_COD_0001 = "001";
	public static final String CSV_MSG_0001= "文件为空";
	public static final String CSV_COD_0002 = "002";
	public static final String CSV_MSG_0002= "数据格式不正确";
	public static final String CSV_COD_0003 = "003";
	public static final String CSV_MSG_0003= "文件过大";
	public static final String CSV_COD_0004 = "004";
	public static final String CSV_MSG_0004= "文件不存在";
	public static final String CSV_COD_0005 = "005";
	public static final String CSV_MSG_0005= "数据为空";
	public static final String CSV_COD_0006 = "006";
	public static final String CSV_MSG_0006= "行数太多";
	public static final String CSV_COD_0008= "008";
	public static final String CSV_MSG_0008= "csv文件头不存在";
	public static final String CSV_COD_0009= "009";
	public static final String CSV_MSG_0009= "csv文件头列数不正确";
	public static final String CSV_COD_0010 = "010";
	public static final String CSV_MSG_0010= "文件头中单元格有空格";
	public static final String CSV_COD_0011 = "011";
	public static final String CSV_MSG_0011 = "文件头第一列应该是卡号";
	public static final String CSV_COD_0012 = "012";
	public static final String CSV_MSG_0012 = "文件头第二列应该是密码";
	public static final String CSV_COD_0013 = "013";
	public static final String CSV_MSG_0013 = "文件头第三列应该是面额";
	public static final String CSV_COD_0014 = "014";
	public static final String CSV_MSG_0014 = "数据行中存在空数据";
	public static final String CSV_COD_0015 = "015";
	public static final String CSV_MSG_0015 = "数据行中数据列数不正确";
	public static final String CSV_COD_0016 = "016";
	public static final String CSV_MSG_0016 = "数据行中单元格存在空数据";
	public static final String CSV_COD_0017 = "017";
	public static final String CSV_MSG_0017 = "第一列数据应该是正整数";
	public static final String CSV_COD_0018 = "018";
	public static final String CSV_MSG_0018 = "第二列数据应该是正整数";
	public static final String CSV_COD_0019 = "019";
	public static final String CSV_MSG_0019 = "第三列数据应该是非负整数（正整数 > 0）";
	public static final String CSV_COD_0020 = "020";
	public static final String CSV_MSG_0020 = "联通充值卡的充值卡号是15位，密码19位";
	public static final String CSV_COD_0021 = "021";
	public static final String CSV_MSG_0021 = "移动充值卡的充值卡号是17位，密码18位";
	public static final String CSV_COD_0022 = "022";
	public static final String CSV_MSG_0022 = "电信充值卡的充值卡号是19位，密码18位";
	public static final String CSV_COD_0023= "023";
	public static final String CSV_MSG_0023 = "csv数据读取失败";
	public static final String CSV_COD_0024= "024";
	public static final String CSV_MSG_0024 = "csv数据绑定对象失败";
	public static final String CSV_COD_0025= "025";
	public static final String CSV_MSG_0025 = "上传的文件自身存在重复卡号";
	public static final String CSV_COD_0026= "026";
	public static final String CSV_MSG_0026= "数据库商品属性值表中存在相同卡号";
	public static final String CSV_COD_0028= "028";
	public static final String CSV_MSG_0028= "插入商品失败";
	public static final String CSV_COD_0029= "029";
	public static final String CSV_MSG_0029= "卡号不存在";
	public static final String CSV_COD_0030= "030";
	public static final String CSV_MSG_0030= "密码不存在";
	public static final String CSV_COD_0031= "031";
	public static final String CSV_MSG_0031= "插入卡号失败";
	public static final String CSV_COD_0032= "032";
	public static final String CSV_MSG_0032= "插入密码失败";
	public static final String CSV_COD_0033= "033";
	public static final String CSV_MSG_0033= "卡号插入成功，密码插入失败";
	public static final String CSV_COD_0035= "035";
	public static final String CSV_MSG_0035= "商品插入成功，商品卖家属性值表插入失败";
	public static final String CSV_COD_0036= "036";
	public static final String CSV_MSG_0036= "goodsTypeId为空";
	public static final String CSV_COD_0037= "037";
	public static final String CSV_MSG_0037= "获取游戏id失败";
	public static final String CSV_COD_0038= "038";
	public static final String CSV_MSG_0038= "获取渠道id失败";
	public static final String CSV_COD_0039= "039";
	public static final String CSV_MSG_0039= "获取充值卡卖家属性id失败";
	public static final String CSV_COD_0040= "040";
	public static final String CSV_MSG_0040= "获取面额失败";
	public static final String CSV_COD_0041= "041";
	public static final String CSV_MSG_0041= "商品类别获取失败";
	public static final String CSV_COD_0042= "042";
	public static final String CSV_MSG_0042= "csv数据中存在非法面额值";
	public static final String CSV_COD_0043= "043";
	public static final String CSV_MSG_0043= "读取csv文件异常";
	public static final String CSV_COD_0044= "044";
	public static final String CSV_MSG_0044= "对象属性赋值错误";
	public static final String CSV_COD_0045= "045";
	public static final String CSV_MSG_0045= "上传的重复卡号太多，请重新检查";
	public static final String CSV_COD_0047= "047";
	public static final String CSV_MSG_0047= "未解析的充值卡数据表商品id更新失败";
	
	public static final String CSV_COD_0007 = "007";
	public static final String CSV_MSG_0007= "充值卡号上传成功，未解析";
	public static final String CSV_COD_0027= "027";
	public static final String CSV_MSG_0027= "充值卡号解析中，请稍后...";
	public static final String CSV_COD_0034= "034";
	public static final String CSV_MSG_0034= "充值卡号解析失败";
	public static final String CSV_COD_0046= "046";
	public static final String CSV_MSG_0046= "充值卡号解析成功";
	public static final String CSV_COD_0048= "048";
	public static final String CSV_MSG_0048= "批次数据插入失败，请勿解析数据，立即联系开发人员";
	
}
