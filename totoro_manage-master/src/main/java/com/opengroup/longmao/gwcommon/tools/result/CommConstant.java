package com.opengroup.longmao.gwcommon.tools.result;



public class CommConstant {

	// http请求返回信息
	public static final String GWSSUCC = "success";
	public static final String GWS1001 = "访问资源不存在，或者服务器异常";
	public static final String GWS1002 = "服务器拒绝连接，或者通讯超时";
	public static final String PARAMERR = "参数非法";

	// http请求返回信息
	public static final String GWSCOD0000 = "000";
	public static final String GWSMSG0000 = "success";
	public static final String GWSCOD0001 = "001";
	public static final String GWSMSG0001 = "系统响应异常,操作失败";
	public static final String GWSCOD0002 = "002";
	public static final String GWSMSG0002 = "数据获取失败,非法请求,token不正确";
	public static final String GWSCOD0003 = "003";
	public static final String GWSMSG0003 = "参数非法";
	public static final String GWSCOD0004 = "004";
	public static final String GWSMSG0004 = "请求非法";
	public static final String GWSCOD0005 = "005";
	public static final String GWSMSG0005 = "验证码不正确，请重新校验！";
	
	public static final String GWSCOD0006 = "006";
	public static final String GWSMSG0006 = "角色已存在！";
	
	public static final String GWSCOD0007 = "007";
	public static final String GWSMSG0007 = "账号已存在！";
	
	public static final String GWSCOD0008 = "008";
	public static final String GWSMSG0008 = "该角色下有用户存在,禁止删除！";
	
	public static final String GWSCOD0009 = "009";
	public static final String GWSMSG0009 = "菜单已存在！";
	
	public static final String GWSCOD0010 = "010";
	public static final String GWSMSG0010 = "用户不存在！";
	
	public static final String GWSCOD0011 = "011";
	public static final String GWSMSG0011 = "用户名或密码错误！";
	
	public static final String CTIME = "ctime";
	public static final String UTIME = "utime";
	public static final String IS_DELETE = "isDelete";
	
	 //排序
    public static final String SORT_FIELD_CTIME = "ctime";
    
    //方法名常量
    public static final String METHODNAME = "methodName";
    
    //微信公众号充值类型商品前缀
    public static final String WX_PUB_RECHARGE_GOODS_TYPE_PREFIX = "WX_PUB_RECHARGE_GOODS_TYPE_PREFIX_";
    
    //开播主播前缀
    public static final String LIVE_HEART_USER = "LIVE_HEART_USER_";
	
    //开播信息ID前缀
    public static final String LIVE_HEART_ID = "LIVE_HEART_ID_";
    
    //微信公众号卖家id
  	public static final Long WX_PUB_SELLER_ID = 102L;
  	
    //提现导出定义头
    public static final String [] CSV_HEADER = {"提现编号","主播ID","提现金额(元)","平台分成(元)","提现时间","提现转账信息","提现状态"};
    
    //竞猜导出定义头
    public static final String [] GUESS_CSV_HEADER = {"竞猜编号","主播ID","竞猜标题","竞猜内容","庄家收益","最终抢庄成功者","注池金额","竞猜状态","竞猜结果","创建时间"};

}
