package com.opengroup.longmao.gwcommon.tools.gen.code;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.opengroup.longmao.gwcommon.tools.gen.db.DBUtil;
import com.opengroup.longmao.gwcommon.tools.gen.util.Constant;
import com.opengroup.longmao.gwcommon.tools.gen.util.GenUtil;

/**
 * 自动生成Service接口实现
 * 
 * @author Hermit
 *
 */
public class GenServiceImplCode {
	/*
	 * 构造函数
	 */
	public GenServiceImplCode(String tablename,String describe) {
		// 首字母大写
		String bigTableName = DBUtil.bigTableName;
		// 首字母小写
		String smallTableName = DBUtil.smallTableName;

		//开始生成service接口实现代码
		String serviceImpl = serviceImpl(bigTableName,smallTableName,describe);
		try {
			File directory = new File("");
			String outputPath = directory.getAbsolutePath() + "/src/main/java/"+ Constant.PACKAGE_OUTPATH_SERVICE_IMPL.replace(".", "/") + "/" + bigTableName + "ServiceImpl.java";
			FileWriter fw = new FileWriter(outputPath);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(serviceImpl);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("【"+tablename+"】表【Service接口实现】生成完毕！");
	}

	/**
	 * 功能：生成Service接口实现主体代码
	 * @param tablename
	 * @param bigTableName
	 * @param smallTableName
	 * @param describe
	 * @return
	 */
	private String serviceImpl(String bigTableName,String smallTableName,String describe) {
		StringBuffer sb = new StringBuffer();

		// 注释部分
//		sb.append("/**\r\n");
//		sb.append(" *Copyright (C) 2016  HangZhou Yuti Technology Co.Ltd  Holdings Ltd. All rights reserved\r\n");
//		sb.append("*\r\n");
//		sb.append("*本代码版权归杭州育体科技所有，且受到相关的法律保护。\r\n");
//		sb.append("*没有经过版权所有者的书面同意，任何其他个人或组织均不得以任何形式将本文件或本文件的部分代码用于其他商业用途。\r\n");
//		sb.append(" * \r\n");
//		sb.append(" */ \r\n");

		// 导包
		sb.append("package " + Constant.PACKAGE_OUTPATH_SERVICE_IMPL + ";\r\n");
		sb.append("\r\n");

		sb.append("import org.apache.commons.lang3.StringUtils;\r\n");
		sb.append("import org.apache.commons.lang3.builder.ToStringBuilder;\r\n");
		sb.append("import org.springframework.beans.factory.annotation.Autowired;\r\n");
		sb.append("import org.springframework.data.domain.Page;\r\n");
		sb.append("import org.springframework.data.domain.PageRequest;\r\n");
		sb.append("import org.springframework.data.domain.Pageable;\r\n");
		sb.append("import org.springframework.data.domain.Sort;\r\n");
		sb.append("import org.springframework.data.domain.Sort.Direction;\r\n");
		sb.append("import org.springframework.stereotype.Service;\r\n");

		sb.append("import com.opengroup.longmao.gwcommon.configuration.log.GwsLogger;\r\n");
		sb.append("import com.opengroup.longmao.gwcommon.configuration.redis.cache.IdGlobalGenerator;\r\n");
		sb.append("import com.opengroup.longmao.gwcommon.entity.po."+ bigTableName +";\r\n");
		sb.append("import com.opengroup.longmao.gwcommon.repository.master."+ bigTableName +"RepositoryMaster;\r\n");
		sb.append("import com.opengroup.longmao.gwcommon.repository.slave."+ bigTableName +"RepositorySlave;\r\n");
		sb.append("import com.opengroup.longmao.gwcommon.service."+ bigTableName +"Service;\r\n");
		sb.append("import com.opengroup.longmao.gwcommon.repository.queryFilter."+ bigTableName +"QueryFilter;\r\n\r\n");
		
		// 注释部分
		sb.append("/**\r\n");
		sb.append(" * 【" + describe +"】 Service接口实现\r\n");
		sb.append(" *\r\n");
		sb.append(" * @version 1.0\r\n");
		sb.append(" * @author " + Constant.AUTHORNAME + " " + GenUtil.getDate() + "\r\n");
		sb.append(" */ \r\n");
		
		sb.append("@Service\r\n");
		// 实体部分
		sb.append("public class " + bigTableName + "ServiceImpl implements " + bigTableName + "Service{\r\n\r\n");
		
		//生成所有属性
		processAllAttrs(sb,bigTableName,smallTableName);
		//生成所有方法
		processAllMethod(sb,bigTableName,smallTableName,describe);
		
		sb.append("}\r\n");

		return sb.toString();
	}
	
	/**
	 * 功能：生成所有属性
	 * @param sb
	 * @param bigTableName
	 * @param smallTableName
	 */
	private void processAllAttrs(StringBuffer sb,String bigTableName,String smallTableName) {
		sb.append("	@Autowired\r\n");
		sb.append("	private " + bigTableName + "RepositoryMaster "+ smallTableName +"RepositoryMaster;\r\n\r\n");
		
		sb.append("	@Autowired\r\n");
		sb.append("	private " + bigTableName + "RepositorySlave "+ smallTableName +"RepositorySlave;\r\n\r\n");
		
		sb.append("	@Autowired\r\n");
		sb.append("	private IdGlobalGenerator idGlobalGenerator;\r\n\r\n");
	}
	
	/**
	 * 
	 * @param sb
	 * @param bigTableName
	 * @param smallTableName
	 * @param describe
	 */
	private void processAllMethod(StringBuffer sb,String bigTableName,String smallTableName,String describe) {
		//分页查询
		sb.append("	/**\r\n");
		sb.append("	* 【分页查询"+describe+"】\r\n");
		sb.append("	* @param "+smallTableName+"\r\n");
		sb.append("	* @param pageNo\r\n");
		sb.append("	* @param pageSize\r\n");
		sb.append("	* @param sortField\r\n");
		sb.append("	* @return "+smallTableName+"\r\n");
		sb.append("	* @version 1.0\r\n");
		sb.append("	* @author " + Constant.AUTHORNAME + " " + GenUtil.getDate() + "\r\n");
		sb.append("	*/ \r\n");
		sb.append("	@Override\r\n");
		sb.append("	public Page<"+bigTableName+"> find"+bigTableName+"("+bigTableName+" "+smallTableName+",Integer pageNo, Integer pageSize, String sortField){\r\n");
		sb.append("		// 组合查询语句\r\n");
		sb.append("		"+bigTableName+"QueryFilter query = new "+bigTableName+"QueryFilter();\r\n");
		sb.append("		//query.setIsId("+smallTableName+".getId());\r\n");
		sb.append("		//query.setIsDelete(IsDeleteEnum.NO.getVal());\r\n");
		sb.append("		//字段排序\r\n");
		sb.append("		Sort sort = new Sort(Direction.DESC, sortField);\r\n");
		sb.append("		// 分页\r\n");
		sb.append("		Pageable page = new PageRequest(pageNo, pageSize, sort);\r\n");
		sb.append("		// 查询分页数据\r\n");
		sb.append("		Page<"+bigTableName+"> pageList = "+smallTableName+"RepositorySlave.findAll(query, page);\r\n");
		sb.append("		return pageList;\r\n");
		sb.append("	}\r\n");
		
		//id查询
		sb.append("	/**\r\n");
		sb.append("	* 【根据id查询"+describe+"】\r\n");
		sb.append("	* @param id\r\n");
		sb.append("	* @return "+smallTableName+"\r\n");
		sb.append("	* @version 1.0\r\n");
		sb.append("	* @author " + Constant.AUTHORNAME + " " + GenUtil.getDate() + "\r\n");
		sb.append("	*/ \r\n");
		sb.append("	@Override\r\n");
		sb.append("	public "+bigTableName+" find"+bigTableName+"(Long id){\r\n");
		sb.append("	    "+bigTableName+" "+smallTableName+" = null;\r\n");
		sb.append("		if(StringUtils.isNotBlank(id.toString())){\r\n");
		sb.append("	      "+smallTableName+" = "+smallTableName+"RepositorySlave.findOne(id);\r\n");
		sb.append("		}else{\r\n");
		sb.append("		  GwsLogger.info(\"id不存在\");\r\n");
		sb.append("		}\r\n");
		sb.append("		return "+smallTableName+";\r\n");
		sb.append("	}\r\n");
		
		//保存
		sb.append("	/**\r\n");
		sb.append("	* 【保存"+describe+"】\r\n");
		sb.append("	* @param "+smallTableName+"\r\n");
		sb.append("	* @return "+smallTableName+"\r\n");
		sb.append("	* @version 1.0\r\n");
		sb.append("	* @author " + Constant.AUTHORNAME + " " + GenUtil.getDate() + "\r\n");
		sb.append("	*/ \r\n");
		sb.append("	@Override\r\n");
		sb.append("	public "+bigTableName+" save"+bigTableName+"("+bigTableName+" "+smallTableName+"){\r\n");
		sb.append("		//判断对象是否存在\r\n");
		sb.append("		if("+smallTableName+"!= null){\r\n");
		sb.append("		   //id统一生成\r\n");
		sb.append("		   //Long id = idGlobalGenerator.getSeqId("+bigTableName+".class);\r\n");
		sb.append("		   //"+smallTableName+".setId(id);\r\n");
		sb.append("		   "+smallTableName+" = "+smallTableName+"RepositoryMaster.save("+smallTableName+");\r\n");	
		sb.append("		   GwsLogger.info(\""+describe+"保存成功\");\r\n");
		sb.append("		}else{\r\n");
		sb.append("			GwsLogger.info(\""+describe+"对象不存在，保存失败:"+smallTableName+"={}\",ToStringBuilder.reflectionToString("+smallTableName+"));\r\n");
		sb.append("		    return null;\r\n");
		sb.append("		}\r\n");
		sb.append("		return "+smallTableName+";\r\n");
		sb.append("	}\r\n\r\n");
		
		//修改
		sb.append("	/**\r\n");
		sb.append("	* 【修改"+describe+"】\r\n");
		sb.append("	* @param "+smallTableName+"\r\n");
		sb.append("	* @return "+smallTableName+"\r\n");
		sb.append("	* @version 1.0\r\n");
		sb.append("	* @author " + Constant.AUTHORNAME + " " + GenUtil.getDate() + "\r\n");
		sb.append("	*/ \r\n");
		sb.append("	@Override\r\n");
		sb.append("	public "+bigTableName+" update"+bigTableName+"("+bigTableName+" "+smallTableName+"){\r\n");
		sb.append("		if(StringUtils.isNotBlank("+smallTableName+".getId())){\r\n");
		sb.append("		    //先从库中查出该对象\r\n");
		sb.append("	        "+bigTableName+" "+smallTableName+"Bean = "+smallTableName+"RepositorySlave.findOne("+smallTableName+".getId());\r\n");
		sb.append("		    //判断对象是否存在\r\n");
		sb.append("			if("+smallTableName+"Bean!= null){\r\n");
		sb.append("		       //该处数据填充代码请自行补全....\r\n");
		sb.append("			   "+smallTableName+" = "+smallTableName+"RepositoryMaster.save("+smallTableName+"Bean);\r\n");	
		sb.append("			   GwsLogger.info(\""+describe+"修改成功\");\r\n");
		sb.append("			}else{\r\n");
		sb.append("			    GwsLogger.info(\""+describe+"对象不存在，修改失败:"+smallTableName+"={}\",ToStringBuilder.reflectionToString("+smallTableName+"));\r\n");
		sb.append("		        return null;\r\n");
		sb.append("			}\r\n");
		sb.append("		}else{\r\n");
		sb.append("			 GwsLogger.error(\""+describe+"id不存在，修改失败:"+smallTableName+"={}\",ToStringBuilder.reflectionToString("+smallTableName+"));\r\n");
		sb.append("		     return null;\r\n");
		sb.append("		}\r\n");
		sb.append("		return "+smallTableName+";\r\n");
		sb.append("	}\r\n\r\n");
		
		//删除
		sb.append("	/**\r\n");
		sb.append("	 * 【根据id删除"+describe+"】\r\n");
		sb.append("	 * @param id\r\n");
		sb.append("	 * @return void\r\n");
		sb.append("	 * @version 1.0\r\n");
		sb.append("	 * @author " + Constant.AUTHORNAME + " " + GenUtil.getDate() + "\r\n");
		sb.append("	 */ \r\n");
		sb.append("	@Override\r\n");
		sb.append("	public void delete"+bigTableName+"(Long id){\r\n");
		sb.append("		//先从库中查出该对象\r\n");
		sb.append("		"+bigTableName+" "+smallTableName+" = "+smallTableName+"RepositorySlave.findOne(id);\r\n");
		sb.append("		//判断对象是否存在\r\n");
		sb.append("		if("+smallTableName+"!=null){\r\n");
		sb.append("			//将用户状态改为删除\r\n");
		sb.append("			//"+smallTableName+".setIsDelete(IsDeleteEnum.YES.getVal());\r\n");
		sb.append("			"+bigTableName+" new"+bigTableName+" = "+smallTableName+"RepositoryMaster.save("+smallTableName+");\r\n");
		sb.append("			//判断对象是否存在\r\n");
		sb.append("			if(new"+bigTableName+"!=null){\r\n");
		sb.append("				GwsLogger.info(\""+describe+"删除成功\");\r\n");
		sb.append("			}else{\r\n");
		sb.append("				GwsLogger.info(\""+describe+"删除失败:id={}\",id);\r\n");
		sb.append("			}\r\n");
		sb.append("		}else{\r\n");
		sb.append("			GwsLogger.info(\""+describe+"对象不存在:id={}\",id);\r\n");
		sb.append("		}\r\n");
		sb.append("	}\r\n\r\n");		
	}
}