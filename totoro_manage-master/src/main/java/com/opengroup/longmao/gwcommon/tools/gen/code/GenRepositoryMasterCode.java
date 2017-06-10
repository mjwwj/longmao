package com.opengroup.longmao.gwcommon.tools.gen.code;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.opengroup.longmao.gwcommon.tools.gen.db.DBUtil;
import com.opengroup.longmao.gwcommon.tools.gen.util.Constant;
import com.opengroup.longmao.gwcommon.tools.gen.util.GenUtil;

/**
 * 自动生成RepositoryMaster接口
 * 
 * @author Hermit
 *
 */
public class GenRepositoryMasterCode {

	/*
	 * 构造函数
	 */
	public GenRepositoryMasterCode(String tablename, String describe) {
		// 首字母大写
		String bigTableName = DBUtil.bigTableName;
		// 首字母小写
		String smallTableName = DBUtil.smallTableName;
		
		// 开始生成repositoryMaster代码
		String repositoryMasterCode = repositoryMasterCode(bigTableName,describe);
		try {
			File directory = new File("");
			String outputPath = directory.getAbsolutePath() + "/src/main/java/"
					+ Constant.PACKAGE_OUTPATH_REPOSITORY_MASTER.replace(".", "/") + "/" + bigTableName + "RepositoryMaster.java";
			FileWriter fw = new FileWriter(outputPath);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(repositoryMasterCode);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("【"+tablename+"】表【RepositoryMaster接口】生成完毕！");
	}

	/**
	 * 功能：生成RepositoryMaster接口主体代码
	 * @param tablename
	 * @param bigTableName
	 * @param smallTableName
	 * @param describe
	 * @return
	 */
	private String repositoryMasterCode(String bigTableName, String describe) {
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
		sb.append("package " + Constant.PACKAGE_OUTPATH_REPOSITORY_MASTER + ";\r\n");
		sb.append("\r\n");
		sb.append("import com.opengroup.longmao.gwcommon.configuration.query.core.BaseRepository;\r\n");
		sb.append("import com.opengroup.longmao.gwcommon.entity.po."+bigTableName+";\r\n\r\n");

		// 注释部分
		sb.append("/**\r\n");
		sb.append(" * 【" + describe + "】 RepositoryMaster接口\r\n");
		sb.append(" *\r\n");
		sb.append(" * @version 1.0\r\n");
		sb.append(" * @author " + Constant.AUTHORNAME + " " + GenUtil.getDate() + "\r\n");
		sb.append(" */ \r\n");

		// 实体部分
		sb.append("public interface " + bigTableName + "RepositoryMaster extends BaseRepository<"+bigTableName+", Long> {\r\n\r\n");
		//生成所有方法
		processAllMethod(sb,bigTableName,describe);
		
		sb.append("}\r\n");

		return sb.toString();
	}


	/**
	 * 功能：生成所有方法
	 * @param sb
	 * @param bigTableName
	 * @param describe
	 */
	private void processAllMethod(StringBuffer sb,String bigTableName,String describe) {

	}

}