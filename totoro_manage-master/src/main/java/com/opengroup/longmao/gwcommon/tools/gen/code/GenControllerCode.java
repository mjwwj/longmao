package com.opengroup.longmao.gwcommon.tools.gen.code;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.opengroup.longmao.gwcommon.tools.gen.db.DBUtil;
import com.opengroup.longmao.gwcommon.tools.gen.util.Constant;
import com.opengroup.longmao.gwcommon.tools.gen.util.GenUtil;

/**
 * 自动生成Controller控制层
 * 
 * @author Hermit
 *
 */
public class GenControllerCode {
	/*
	 * 构造函数
	 */
	public GenControllerCode(String tablename,String describe) {
		// 首字母大写
		String bigTableName = DBUtil.bigTableName;
		// 首字母小写
		String smallTableName = DBUtil.smallTableName;

		//开始生成controller
		String controllerCode = controllerCode(tablename,bigTableName,smallTableName,describe);
		try {
			File directory = new File("");
			String outputPath = directory.getAbsolutePath() + "/src/main/java/"+ Constant.PACKAGE_OUTPATH_CONTROLLER.replace(".", "/") + "/" + bigTableName + "Controller.java";
			FileWriter fw = new FileWriter(outputPath);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(controllerCode);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("【"+tablename+"】表【Controller控制层】生成完毕！");
	}

	/**
	 * 功能：生成控制类主体代码
	 * @param tablename
	 * @param bigTableName
	 * @param smallTableName
	 * @param describe
	 * @return
	 */
	private String controllerCode(String tablename,String bigTableName,String smallTableName,String describe) {
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
		sb.append("package " + Constant.PACKAGE_OUTPATH_CONTROLLER + ";\r\n");
		sb.append("\r\n");
		sb.append("import org.springframework.beans.factory.annotation.Autowired;\r\n");
		sb.append("import org.springframework.web.bind.annotation.RequestMapping;\r\n");
		sb.append("import org.springframework.web.bind.annotation.RequestMethod;\r\n");
		sb.append("import org.springframework.web.bind.annotation.RestController;\r\n");
		
		sb.append("import org.apache.commons.lang3.StringUtils;\r\n");
		sb.append("import org.apache.commons.lang3.builder.ToStringBuilder;\r\n");
		sb.append("import org.springframework.data.domain.Page;\r\n");

		sb.append("import com.opengroup.longmao.gwcommon.configuration.log.GwsLogger;\r\n");
		sb.append("import com.opengroup.longmao.gwcommon.tools.result.CommConstant;\r\n");
		sb.append("import com.opengroup.longmao.gwcommon.tools.result.RetResult;\r\n");

		sb.append("import io.swagger.annotations.Api;\r\n");
		sb.append("import io.swagger.annotations.ApiImplicitParam;\r\n");
		sb.append("import io.swagger.annotations.ApiImplicitParams;\r\n");
		sb.append("import io.swagger.annotations.ApiOperation;\r\n");
		sb.append("import springfox.documentation.swagger2.annotations.EnableSwagger2;\r\n");

		sb.append("import com.opengroup.longmao.gwcommon.annotation.JsonParam;\r\n");
		sb.append("import javax.servlet.http.HttpServletRequest;\r\n");
		sb.append("import com.opengroup.longmao.gwcommon.tools.EntityDtoConverter;\r\n");
		sb.append("import com.opengroup.longmao.gwcommon.entity.po." + bigTableName + ";\r\n");
		sb.append("import com.opengroup.longmao.gwcommon.entity.dto." + bigTableName + "DTO;\r\n");
		sb.append("import com.opengroup.longmao.gwcommon.entity.vo." + bigTableName + "VO;\r\n");
		sb.append("import com.opengroup.longmao.gwcommon.service." + bigTableName + "Service;\r\n\r\n");
		
		// 注释部分
		sb.append("/**\r\n");
		sb.append(" * 【" + describe +"】 控制类\r\n");
		sb.append(" *\r\n");
		sb.append(" * @version 1.0\r\n");
		sb.append(" * @author " + Constant.AUTHORNAME + " " + GenUtil.getDate() + "\r\n");
		sb.append(" */ \r\n");
		
		sb.append("@RestController\r\n");
		sb.append("@EnableSwagger2\r\n");
		sb.append("@Api(value= \"" + describe + "\",tags=\""+ smallTableName +"\")\r\n");
		sb.append("@RequestMapping(value = {\"/" + smallTableName + "\"})\r\n");
		// 实体部分
		sb.append("public class " + bigTableName + "Controller {\r\n\r\n");
		//生成所有属性
		processAllAttrs(sb,bigTableName,smallTableName);
		//生成所有方法
		processAllMethod(sb,bigTableName,smallTableName,describe);
		
		sb.append("}\r\n");

		// System.out.println(sb.toString());
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
		sb.append("	private " + bigTableName + "Service "+ smallTableName +"Service;\r\n\r\n");
	}
	
	/**
	 * 功能：生成所有方法
	 * @param sb
	 * @param bigTableName
	 * @param smallTableName
	 * @param describe
	 */
	private void processAllMethod(StringBuffer sb,String bigTableName,String smallTableName,String describe) {
		//保存
		sb.append("	/**\r\n");
		sb.append("	* 【保存"+describe+"】\r\n");
		sb.append("	* @param "+smallTableName+"VO\r\n");
		sb.append("	* @param request\r\n");
		sb.append("	* @return RetResult\r\n");
		sb.append("	* @version 1.0\r\n");
		sb.append("	* @author " + Constant.AUTHORNAME + " " + GenUtil.getDate() + "\r\n");
		sb.append("	*/ \r\n");
		sb.append("	@ApiOperation(value = \"保存"+describe+"\",notes=\"保存"+describe+"\")\r\n");
		sb.append("	@ApiImplicitParams({\r\n");
		sb.append("		@ApiImplicitParam(name =\""+smallTableName+"VO\",value =\"详细实体"+smallTableName+"\", required = true, dataType =\""+bigTableName+"\"),\r\n");
		sb.append("		@ApiImplicitParam(name =\"request\",value =\"HttpServletRequest\", required = true, dataType =\"HttpServletRequest\") })\r\n");
		sb.append("	@RequestMapping(value = \"/save"+bigTableName+"\",method =RequestMethod.POST)\r\n");
		sb.append("	public RetResult save"+bigTableName+"(@JsonParam "+bigTableName+"VO "+smallTableName+"VO,HttpServletRequest request){\r\n");
		sb.append("		String code = CommConstant.GWSCOD0000;\r\n");
		sb.append("		String message = CommConstant.GWSMSG0000;\r\n");
		sb.append("		GwsLogger.info(\""+describe+"保存开始:code={},message={},"+smallTableName+"VO\",code,message,ToStringBuilder.reflectionToString("+smallTableName+"VO));\r\n");
		sb.append("		"+bigTableName+"DTO "+smallTableName+"DTO = null;\r\n");
		sb.append("		try {\r\n");
		sb.append("			if("+smallTableName+"VO!=null){\r\n");
		sb.append("			   "+bigTableName+" "+smallTableName+" = ("+bigTableName+") EntityDtoConverter.copy("+smallTableName+"VO);\r\n");
		sb.append("			   "+smallTableName+" = "+smallTableName+"Service.save"+bigTableName+"("+smallTableName+");\r\n");	
		sb.append("			   "+smallTableName+"DTO = ("+bigTableName+"DTO) EntityDtoConverter.copy("+smallTableName+");\r\n");
		sb.append("			   GwsLogger.info(\""+describe+"保存成功:code={},message={},\",code,message);\r\n");
		sb.append("			}else{\r\n");
		sb.append("			   code = CommConstant.GWSCOD0001;\r\n");	
		sb.append("			   message = CommConstant.GWSMSG0001;\r\n");
		sb.append("			   GwsLogger.info(\""+describe+"保存失败:code={},message={},"+smallTableName+"={}\",code,message,ToStringBuilder.reflectionToString("+smallTableName+"VO));\r\n");
		sb.append("			}\r\n");
		sb.append("		} catch (Exception e) {\r\n");	
		sb.append("			code = CommConstant.GWSCOD0001;\r\n");	
		sb.append("			message = CommConstant.GWSMSG0001;\r\n");
		sb.append("			GwsLogger.error(\""+describe+"保存异常:"+smallTableName+"VO={},e={}\",ToStringBuilder.reflectionToString("+smallTableName+"VO)"+",e);\r\n");
		sb.append("		}\r\n");	
		sb.append("		GwsLogger.info(\""+describe+"保存结束:code={},message={}\",code,message);\r\n");
		sb.append("		return RetResult.setRetDate(code, message, "+smallTableName+"DTO);\r\n");
		sb.append("	}\r\n\r\n");
		
		//根据id删除对象
		sb.append("	/**\r\n");
		sb.append("	* 【根据id删除"+describe+"】\r\n");
		sb.append("	* @param id\r\n");
		sb.append("	* @return RetResult\r\n");
		sb.append("	* @version 1.0\r\n");
		sb.append("	* @author " + Constant.AUTHORNAME + " " + GenUtil.getDate() + "\r\n");
		sb.append("	*/ \r\n");
		sb.append("	@ApiOperation(value = \"根据id删除"+describe+"\",notes=\"根据id删除"+describe+"\")\r\n");
		sb.append("	@ApiImplicitParam(name =\"id\",value =\"主键id\", required = true, dataType =\"Long\")\r\n");
		sb.append("	@RequestMapping(value = \"/delete"+bigTableName+"\",method =RequestMethod.GET)\r\n");
		sb.append("	public RetResult delete"+bigTableName+"(Long id){\r\n");
		sb.append("		String code = CommConstant.GWSCOD0000;\r\n");
		sb.append("		String message = CommConstant.GWSMSG0000;\r\n");
		sb.append("		GwsLogger.info(\"根据id删除"+describe+"开始:code={},message={},id={}\",code,message,id);\r\n");
		sb.append("		//根据id删除"+describe+"\r\n");
		sb.append("		try {\r\n");	
		sb.append("			if(StringUtils.isNotBlank(id.toString())){\r\n");
		sb.append("			   "+smallTableName +"Service.delete"+bigTableName+"(id);\r\n");	
		sb.append("			   GwsLogger.info(\"根据id删除"+describe+"成功:code={},message={},id={}\",code,message,id);\r\n");
		sb.append("			}else{\r\n");
		sb.append("			   code = CommConstant.GWSCOD0001;\r\n");	
		sb.append("			   message = CommConstant.GWSMSG0001;\r\n");
		sb.append("			   GwsLogger.info(\"根据id删除"+describe+"失败:code={},message={},id={}\",code,message,id);\r\n");
		sb.append("			}\r\n");
		sb.append("		} catch (Exception e) {\r\n");	
		sb.append("			code = CommConstant.GWSCOD0001;\r\n");	
		sb.append("			message = CommConstant.GWSMSG0001;\r\n");
		sb.append("			GwsLogger.error(\"根据id删除"+describe+"异常:code={},message={},id={},e={}\",code,message,id,e);\r\n");
		sb.append("		}\r\n");	
		sb.append("		GwsLogger.info(\"根据id删除"+describe+"结束:code={},message={},id={}\",code,message,id);\r\n");
		sb.append("		return RetResult.setRetDate(code, message, null);\r\n");
		sb.append("	}\r\n\r\n");
		
		//根据id查询对象
		sb.append(" /**\r\n");
		sb.append("	* 【根据id查询"+describe+"】\r\n");
		sb.append("	* @param id\r\n");
		sb.append("	* @return RetResult\r\n");
		sb.append("	* @version 1.0\r\n");
		sb.append("	* @author " + Constant.AUTHORNAME + " " + GenUtil.getDate() + "\r\n");
		sb.append("	*/ \r\n");
		sb.append("	@ApiOperation(value = \"根据id查询"+describe+"\",notes=\"根据id查询"+describe+"\")\r\n");
		sb.append("	@ApiImplicitParam(name =\"id\",value =\"主键id\", required = true, dataType =\"Long\")\r\n");
		sb.append("	@RequestMapping(value = \"/find"+bigTableName+"\",method =RequestMethod.GET)\r\n");
		sb.append("	public RetResult find"+bigTableName+"(Long id){\r\n");
		sb.append("		String code = CommConstant.GWSCOD0000;\r\n");
		sb.append("		String message = CommConstant.GWSMSG0000;\r\n");
		sb.append("		GwsLogger.info(\"根据id查询"+describe+"开始:code={},message={},id={}\",code,message,id);\r\n");
		sb.append("		//根据id查询"+describe+"\r\n");
		sb.append("		"+bigTableName+"DTO "+smallTableName+"DTO = null;\r\n");	
		sb.append("		try {\r\n");	
		sb.append("			"+bigTableName+" "+smallTableName+" = "+smallTableName+"Service.find"+bigTableName+"(id);\r\n");
		sb.append("			"+smallTableName+"DTO = ("+bigTableName+"DTO) EntityDtoConverter.copy("+smallTableName+");\r\n");
		sb.append("		} catch (Exception e) {\r\n");	
		sb.append("			code = CommConstant.GWSCOD0001;\r\n");	
		sb.append("			message = CommConstant.GWSMSG0001;\r\n");
		sb.append("			GwsLogger.error(\"根据id查询"+describe+"异常:code={},message={},id={},e={}\",code,message,id,e);\r\n");
		sb.append("		}\r\n");	
		sb.append("		GwsLogger.info(\"根据id查询"+describe+"结束:code={},message={}\",code,message);\r\n");
		sb.append("		return RetResult.setRetDate(code, message, "+smallTableName+"DTO);\r\n");
		sb.append("	}\r\n\r\n");
		
		//查询所有
		sb.append("	/**\r\n");
		sb.append("	* 【查询所有"+describe+"】\r\n");
		sb.append("	* @param "+smallTableName+"VO\r\n");
		sb.append("	* @param pageNo\r\n");
		sb.append("	* @param pageSize\r\n");
		sb.append("	* @return RetResult\r\n");
		sb.append("	* @version 1.0\r\n");
		sb.append("	* @author " + Constant.AUTHORNAME + " " + GenUtil.getDate() + "\r\n");
		sb.append("	*/ \r\n");
		sb.append("	@ApiOperation(value = \"查询所有"+describe+"\",notes=\"查询所有"+describe+"\")\r\n");
		sb.append("	@ApiImplicitParams({\r\n");
		sb.append("		@ApiImplicitParam(name =\""+smallTableName+"VO\",value =\"详细实体"+smallTableName+"\", required = true, dataType =\""+bigTableName+"\"),\r\n");
		sb.append("		@ApiImplicitParam(name =\"pageNo\",value =\"第几页\", required = true, dataType =\"Long\"),\r\n");
		sb.append("		@ApiImplicitParam(name =\"pageSize\",value =\"每页条数\", required = true, dataType =\"String\")\r\n");
		sb.append("		})\r\n");
		sb.append("	@RequestMapping(value = \"/find"+bigTableName+"\",method =RequestMethod.GET)\r\n");
		sb.append("	public RetResult find"+bigTableName+"(@JsonParam "+bigTableName+" "+smallTableName+",Integer pageNo,Integer pageSize){\r\n");
		sb.append("		String code = CommConstant.GWSCOD0000;\r\n");
		sb.append("		String message = CommConstant.GWSMSG0000;\r\n");
		sb.append("		GwsLogger.info(\"查询所有"+describe+"开始:pageNo={},pageSize={},"+smallTableName+"\",pageNo,pageSize,ToStringBuilder.reflectionToString("+smallTableName+"));\r\n");
		sb.append("		//查询所有"+describe+"\r\n");
		sb.append("		Page<"+bigTableName+"> page = null;\r\n");	
		sb.append("		try {\r\n");	
		sb.append("			page = "+smallTableName +"Service.find"+bigTableName+"("+smallTableName+",pageNo-1,pageSize,CommConstant.SORT_FIELD_CTIME);\r\n");	
		sb.append("		} catch (Exception e) {\r\n");	
		sb.append("			code = CommConstant.GWSCOD0001;\r\n");	
		sb.append("			message = CommConstant.GWSMSG0001;\r\n");
		sb.append("			GwsLogger.error(\"查询所有"+describe+"异常:code={},message={},"+smallTableName+"={},pageNo={},pageSize={},softField={},e={}\",code,message,ToStringBuilder.reflectionToString("+smallTableName+"),pageNo-1,pageSize,e);\r\n");
		sb.append("		}\r\n");	
		sb.append("		GwsLogger.info(\"查询所有"+describe+"结束:pageNo={},pageSize={},softField={}\",pageNo-1,pageSize);\r\n");
		sb.append("		return RetResult.setRetDate(code, message, page);\r\n");
		sb.append("	}\r\n\r\n");
		
		//修改
		sb.append("	/**\r\n");
		sb.append("	* 【修改"+describe+"】\r\n");
		sb.append("	* @param "+smallTableName+"VO\r\n");
		sb.append("	* @return RetResult\r\n");
		sb.append("	* @version 1.0\r\n");
		sb.append("	* @author " + Constant.AUTHORNAME + " " + GenUtil.getDate() + "\r\n");
		sb.append("	*/ \r\n");
		sb.append("	@ApiOperation(value = \"修改"+describe+"\",notes=\"修改"+describe+"\")\r\n");
		sb.append("	@ApiImplicitParam(name =\""+smallTableName+"VO\",value =\"详细实体"+smallTableName+"\", required = true, dataType =\""+bigTableName+"\")\r\n");
		sb.append("	@RequestMapping(value = \"/update"+bigTableName+"\",method =RequestMethod.GET)\r\n");
		sb.append("	public RetResult update"+bigTableName+"(@JsonParam "+bigTableName+"VO "+smallTableName+"VO){\r\n");
		sb.append("		String code = CommConstant.GWSCOD0000;\r\n");
		sb.append("		String message = CommConstant.GWSMSG0000;\r\n");
		sb.append("		GwsLogger.info(\"修改"+describe+"开始:"+smallTableName+"\",ToStringBuilder.reflectionToString("+smallTableName+"VO));\r\n");
		sb.append("		//修改"+describe+"\r\n");
		sb.append("		"+bigTableName+"DTO "+smallTableName+"DTO = null;\r\n");	
		sb.append("		try {\r\n");	
		sb.append("			"+bigTableName+" "+smallTableName+" = ("+bigTableName+") EntityDtoConverter.copy("+smallTableName+"VO);\r\n");
		sb.append("			"+smallTableName+" = "+smallTableName +"Service.update"+bigTableName+"("+smallTableName+");\r\n");
		sb.append("			"+smallTableName+"DTO = ("+bigTableName+"DTO) EntityDtoConverter.copy("+smallTableName+");\r\n");
		sb.append("		} catch (Exception e) {\r\n");	
		sb.append("			code = CommConstant.GWSCOD0001;\r\n");	
		sb.append("			message = CommConstant.GWSMSG0001;\r\n");
		sb.append("			GwsLogger.error(\"修改"+describe+"异常:code={},message={},"+smallTableName+"={},e={}\",code,message,ToStringBuilder.reflectionToString("+smallTableName+"VO),e);\r\n");
		sb.append("		}\r\n");	
		sb.append("		GwsLogger.info(\"修改"+describe+"结束\");\r\n");
		sb.append("		return RetResult.setRetDate(code, message, "+smallTableName+"DTO);\r\n");
		sb.append("	}\r\n");
	}

}