package com.opengroup.longmao.gwcommon.configuration.log;

/**
 * 
 * 全局常量定义
 *
 * @version 
 * @author liuyi  2016年4月15日 下午5:02:56
 *
 */
public final class GlobalConstant {
	
	public static final String SQL_TRACE = "SQLTRACE";
	
	public static final String ACCESS_TRACE = "ACCESSTRACE";
	
	public static final String GWS_LOG = "GWS";
	
	public static final String CON_QUOTE = "`";
	    
	public static  Boolean SHOW_SQL=false;
	
	public static ThreadLocal<AccessLog> accessLog= new ThreadLocal<AccessLog>();
	
	public static int SID_COOKIE_MAXAGE = 60*60*24*30;
	
	public static String SID_COOKIE_NAME ="sid";
	
}
