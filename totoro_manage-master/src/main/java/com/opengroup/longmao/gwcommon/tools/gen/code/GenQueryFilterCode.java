package com.opengroup.longmao.gwcommon.tools.gen.code;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.opengroup.longmao.gwcommon.tools.gen.db.DBUtil;
import com.opengroup.longmao.gwcommon.tools.gen.util.Constant;
import com.opengroup.longmao.gwcommon.tools.gen.util.GenUtil;

/**
 * 自动从数据库表反射出QueryFilter构造查询条件
 * 
 * @author Hermit
 *
 */
public class GenQueryFilterCode {

	/*
	 * 构造函数
	 */
	public GenQueryFilterCode(String tablename, String describe) {
		// 首字母大写
		String bigTableName = DBUtil.bigTableName;
		// 首字母小写
		String smallTableName = DBUtil.smallTableName;
		
		// 开始生成代码
		String queryFilterCode = queryFilterCode(tablename, bigTableName, smallTableName, describe);
		try {
			File directory = new File("");
			String outputPath = directory.getAbsolutePath() + "/src/main/java/"
					+ Constant.PACKAGE_OUTPATH_QUERY_FILTER.replace(".", "/") + "/" + bigTableName + "QueryFilter.java";
			FileWriter fw = new FileWriter(outputPath);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(queryFilterCode);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("【"+tablename+"】表【QueryFilter构造查询条件】生成完毕！");
	}

	/**
	 * 功能：生成主体代码
	 * @param tablename
	 * @param bigTableName
	 * @param smallTableName
	 * @param describe
	 * @return
	 */
	private String queryFilterCode(String tablename, String bigTableName, String smallTableName, String describe) {
		StringBuffer sb = new StringBuffer();

		// 注释部分
		// sb.append("/**\r\n");
		// sb.append(" *Copyright (C) 2016 HangZhou Yuti Technology Co.Ltd
		// Holdings Ltd. All rights reserved\r\n");
		// sb.append("*\r\n");
		// sb.append("*本代码版权归杭州育体科技所有，且受到相关的法律保护。\r\n");
		// sb.append("*没有经过版权所有者的书面同意，任何其他个人或组织均不得以任何形式将本文件或本文件的部分代码用于其他商业用途。\r\n");
		// sb.append(" * \r\n");
		// sb.append(" */ \r\n");

		// 导包
		sb.append("package " + Constant.PACKAGE_OUTPATH_QUERY_FILTER + ";\r\n");
		sb.append("\r\n");
		sb.append("import com.opengroup.longmao.gwcommon.configuration.query.Where;\r\n");
		sb.append("import com.opengroup.longmao.gwcommon.configuration.query.annotation.QBindAttrField;\r\n");
		sb.append("import com.opengroup.longmao.gwcommon.configuration.query.annotation.QBindEntity;\r\n");
		sb.append("import com.opengroup.longmao.gwcommon.configuration.query.core.BaseQuery;\r\n");
		sb.append("import java.math.BigDecimal;\r\n");
		sb.append("import java.util.Date;\r\n");
		sb.append("import com.opengroup.longmao.gwcommon.entity.po."+bigTableName+";\r\n\r\n");
		
		// 注释部分
		sb.append("/**\r\n");
		sb.append(" * 【" + describe + "】构造查询条件\r\n");
		sb.append(" *\r\n");
		sb.append(" * @version\r\n");
		sb.append(" * @author " + Constant.AUTHORNAME + " " + GenUtil.getDate() + "\r\n");
		sb.append(" */ \r\n");

		sb.append("@QBindEntity(entityClass = " + bigTableName + ".class)\r\n");
		
		// 主体部分
		sb.append("public class " + bigTableName + "QueryFilter extends BaseQuery {\r\n\r\n");
		//生成所有属性
		processAllAttrs(sb);
		//生成所有方法
		processAllMethod(sb);
		
		sb.append("}\r\n");

		return sb.toString();
	}

	/**
	 * 功能：生成所有属性
	 * @param sb
	 */
	private void processAllAttrs(StringBuffer sb) {
		for (int i = 0; i < DBUtil.colnames.length; i++) {
			// 处理列名
			String bigColname = GenUtil.strChange(DBUtil.colnames[i]);
			String queryType ="equal";
			if(bigColname.equals("beginTime")||bigColname.equals("startTime")){
				queryType ="greaterThanOrEqualTo";
			}
			if(bigColname.equals("endTime")){
				queryType ="lessThanOrEqualTo";
			}
			sb.append("\t@QBindAttrField(fieldName = \"" + bigColname + "\", where = Where."+queryType+")\r\n");
			sb.append("\tprivate " + GenUtil.sqlType2JavaType(DBUtil.colTypes[i]) + " " + bigColname + ";\r\n\r\n");
		}
		sb.append("\r\n");
	}

	/**
	 * 功能：生成所有方法
	 * @param sb
	 */
	private void processAllMethod(StringBuffer sb) {

		for (int i = 0; i < DBUtil.colnames.length; i++) {
			// 处理列名
			String bigColname = GenUtil.strChange(DBUtil.colnames[i]);
			sb.append("\tpublic " + GenUtil.sqlType2JavaType(DBUtil.colTypes[i]) + " get"+ GenUtil.initcap(bigColname) + "(){\r\n");
	
			sb.append("\t\treturn " + bigColname + ";\r\n");
			sb.append("\t}\r\n\r\n");
			sb.append("\tpublic void set" + GenUtil.initcap(bigColname) + "("+ GenUtil.sqlType2JavaType(DBUtil.colTypes[i]) + " " + bigColname + "){\r\n");
			sb.append("\t\tthis." + bigColname + " = " + bigColname + ";\r\n");
			sb.append("\t}\r\n\r\n");
		}

	}

}