package com.opengroup.longmao.gwcommon.tools.gen.code;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.opengroup.longmao.gwcommon.tools.gen.db.DBUtil;
import com.opengroup.longmao.gwcommon.tools.gen.util.Constant;
import com.opengroup.longmao.gwcommon.tools.gen.util.GenUtil;

/**
 * 自动生成Service接口
 * 
 * @author Hermit
 *
 */
public class GenServiceCode {

	/*
	 * 构造函数
	 */
	public GenServiceCode(String tablename, String describe) {
		// 首字母大写
		String bigTableName = DBUtil.bigTableName;
		// 首字母小写
		String smallTableName = DBUtil.smallTableName;
		
		// 开始生成service接口代码
		String serviceCode = serviceCode(bigTableName, smallTableName, describe);
		try {
			File directory = new File("");
			String outputPath = directory.getAbsolutePath() + "/src/main/java/"
					+ Constant.PACKAGE_OUTPATH_SERVICE.replace(".", "/") + "/" + bigTableName + "Service.java";
			FileWriter fw = new FileWriter(outputPath);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(serviceCode);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("【"+tablename+"】表【Service接口】生成完毕！");
	}

	/**
	 * 功能：生成Service接口类主体代码
	 * @param tablename
	 * @param bigTableName
	 * @param smallTableName
	 * @param describe
	 * @return
	 */
	private String serviceCode(String bigTableName, String smallTableName, String describe) {
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
		sb.append("package " + Constant.PACKAGE_OUTPATH_SERVICE + ";\r\n");
		sb.append("\r\n");
		sb.append("import org.springframework.data.domain.Page;\r\n");
		sb.append("import com.opengroup.longmao.gwcommon.entity.po."+bigTableName+";\r\n\r\n");

		// 注释部分
		sb.append("/**\r\n");
		sb.append(" * 【" + describe + "】 service接口\r\n");
		sb.append(" *\r\n");
		sb.append(" * @version 1.0\r\n");
		sb.append(" * @author " + Constant.AUTHORNAME + " " + GenUtil.getDate() + "\r\n");
		sb.append(" */ \r\n");

		// 实体部分
		sb.append("public interface " + bigTableName + "Service {\r\n\r\n");
		
		//生成所有接口
		processAllAttrs(sb,bigTableName,smallTableName,describe);
		
		sb.append("}\r\n");

		return sb.toString();
	}

	/**
	 * 功能：生成所有属性
	 * @param sb
	 * @param bigTableName
	 * @param smallTableName
	 * @param describe
	 */
	private void processAllAttrs(StringBuffer sb,String bigTableName, String smallTableName,String describe) {
		
		//增加
		sb.append("	 /**\r\n");
		sb.append("	 * 【保存"+describe+"】\r\n");
		sb.append("	 * @param "+smallTableName+"\r\n");
		sb.append("	 * @return "+smallTableName+"\r\n");
		sb.append("	 * @version 1.0\r\n");
		sb.append("	 * @author " + Constant.AUTHORNAME + " " + GenUtil.getDate() + "\r\n");
		sb.append("	 */ \r\n");
		sb.append("	 "+ bigTableName + " save"+bigTableName+"("+bigTableName+" "+smallTableName+");\r\n");
		sb.append("\r\n");
		
		//删除
		sb.append("	 /**\r\n");
		sb.append("	 * 【删除"+describe+"】\r\n");
		sb.append("	 * @param id\r\n");
		sb.append("	 * @return void\r\n");
		sb.append("	 * @version 1.0\r\n");
		sb.append("	 * @author " + Constant.AUTHORNAME + " " + GenUtil.getDate() + "\r\n");
		sb.append("	 */ \r\n");
		sb.append("	 void delete"+ bigTableName + "(Long id);\r\n\r\n");
		sb.append("\r\n");
		
		//修改
		sb.append("	 /**\r\n");
		sb.append("	 * 【修改"+describe+"】\r\n");
		sb.append("	 * @param "+smallTableName+"\r\n");
		sb.append("	 * @return "+smallTableName+"\r\n");
		sb.append("	 * @version 1.0\r\n");
		sb.append("	 * @author " + Constant.AUTHORNAME + " " + GenUtil.getDate() + "\r\n");
		sb.append("	 */ \r\n");
		sb.append("	 "+ bigTableName + " update"+bigTableName+"("+bigTableName+" "+smallTableName+");\r\n");
		sb.append("\r\n");
		
		//id查询
		sb.append("	 /**\r\n");
		sb.append("	 * 【查询"+describe+"】\r\n");
		sb.append("	 * @param id\r\n");
		sb.append("	 * @return "+bigTableName+"\r\n");
		sb.append("	 * @version 1.0\r\n");
		sb.append("	 * @author " + Constant.AUTHORNAME + " " + GenUtil.getDate() + "\r\n");
		sb.append("	 */ \r\n");
		sb.append("	 "+bigTableName+" find"+ bigTableName + "(Long id);\r\n\r\n");
		sb.append("\r\n");
		
		//分页查询
		sb.append("	 /**\r\n");
		sb.append("	 * 【查询"+describe+"】\r\n");
		sb.append("	 * @param "+smallTableName+"\r\n");
		sb.append("	 * @param pageNo\r\n");
		sb.append("	 * @param pageSize\r\n");
		sb.append("	 * @param sortField\r\n");
		sb.append("	 * @return Page<"+bigTableName+">\r\n");
		sb.append("	 * @version 1.0\r\n");
		sb.append("	 * @author " + Constant.AUTHORNAME + " " + GenUtil.getDate() + "\r\n");
		sb.append("	 */ \r\n");
		sb.append("	 Page<"+bigTableName+"> find"+ bigTableName + "("+bigTableName+" "+smallTableName+",Integer pageNo,Integer pageSize,String sortField);\r\n\r\n");
		sb.append("\r\n");
	}


}