package com.opengroup.longmao.gwcommon.tools.gen.util;



public class Constant {
	// 数据库连接
	public static final String URL = "jdbc:mysql://192.168.2.8:3360/totorosports";
	public static final String NAME = "root";
	public static final String PASS = "Ytkj@7!9";
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	
	public static final String PACKAGE_OUTPATH_PO= "com.opengroup.longmao.gwcommon.entity.po";
	public static final String PACKAGE_OUTPATH_DTO= "com.opengroup.longmao.gwcommon.entity.dto";
	public static final String PACKAGE_OUTPATH_VO = "com.opengroup.longmao.gwcommon.entity.vo";
	public static final String PACKAGE_OUTPATH_REPOSITORY_MASTER = "com.opengroup.longmao.gwcommon.repository.master";
	public static final String PACKAGE_OUTPATH_REPOSITORY_SLAVE = "com.opengroup.longmao.gwcommon.repository.slave";
	public static final String PACKAGE_OUTPATH_SERVICE = "com.opengroup.longmao.gwcommon.service";
	public static final String PACKAGE_OUTPATH_SERVICE_IMPL = "com.opengroup.longmao.gwcommon.service.impl";
	public static final String PACKAGE_OUTPATH_CONTROLLER = "com.opengroup.longmao.gwcommon.controller";
	public static final String PACKAGE_OUTPATH_QUERY_FILTER = "com.opengroup.longmao.gwcommon.repository.queryFilter";
	
	public static final String AUTHORNAME = "Hermit";// 作者名字
	public static final boolean F_UTIL = true; // 是否需要导入包java.util.*
	public static final boolean F_SQL = false; // 是否需要导入包java.sql.*
	public static final boolean F_BIGDECIMAL = false; // 是否需要导入包java.math.BigDecimal
	public static final boolean F_JPA = true; // 是否需要生成基于注解的JPA实体对象
	
}
