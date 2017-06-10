package com.opengroup.longmao.gwcommon.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opengroup.longmao.gwcommon.configuration.annotation.JsonParam;
import com.opengroup.longmao.gwcommon.configuration.log.GwsLogger;
import com.opengroup.longmao.gwcommon.entity.po.UserGradeRule;
import com.opengroup.longmao.gwcommon.entity.vo.UserGradeRuleVO;
import com.opengroup.longmao.gwcommon.service.GradeRuleService;
import com.opengroup.longmao.gwcommon.tools.result.CommConstant;
import com.opengroup.longmao.gwcommon.tools.result.RetResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 【用户等级规则】 控制类
 *
 * @version 1.0
 * @author Hermit 2017年04月17日 上午11:56:26
 */ 
@Controller
@EnableSwagger2
@Api(value= "用户等级规则",tags="userGradeRule")
public class GradeRuleController {

	@Autowired
	private GradeRuleService userGradeRuleService;
	
	@RequestMapping("/grade.html")
	public String gradeInfo() {
		return "grade/gradelist";
	}

	/**
	* 【保存用户等级规则】
	* @param userGradeRule
	* @param request
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年04月17日 上午11:56:26
	*/ 
	@ApiOperation(value = "保存用户等级规则",notes="保存用户等级规则")
	@ApiImplicitParams({
		@ApiImplicitParam(name ="grade",value ="详细实体userGradeRule", required = true, dataType ="UserGradeRule")})
	@RequestMapping(value = "/saveUserGradeRule",method =RequestMethod.POST)
	public @ResponseBody RetResult saveUserGradeRule(@JsonParam UserGradeRule grade){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("用户等级规则保存开始:code={},message={},userGradeRule",code,message,ToStringBuilder.reflectionToString(grade));
		try {
			if(null != grade){
				grade = userGradeRuleService.saveUserGradeRule(grade);
			   GwsLogger.info("用户等级规则保存成功:code={},message={},",code,message);
			}else{
			   code = CommConstant.GWSCOD0001;
			   message = CommConstant.GWSMSG0001;
			   GwsLogger.info("用户等级规则保存失败:code={},message={},userGradeRule={}",code,message,ToStringBuilder.reflectionToString(grade));
			}
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("用户等级规则保存异常:userGradeRule={},e={}",ToStringBuilder.reflectionToString(grade),e);
		}
		GwsLogger.info("用户等级规则保存结束:code={},message={}",code,message);
		return RetResult.setRetDate(code, message, grade);
	}

	/**
	* 【根据id删除用户等级规则】
	* @param id
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年04月17日 上午11:56:26
	*/ 
	@ApiOperation(value = "根据id删除用户等级规则",notes="根据id删除用户等级规则")
	@ApiImplicitParam(name ="id",value ="主键id", required = true, dataType ="Long")
	@RequestMapping(value = "/deleteUserGradeRule",method =RequestMethod.POST)
	public @ResponseBody RetResult deleteUserGradeRule(Long id){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("根据id删除用户等级规则开始:code={},message={},id={}",code,message,id);
		//根据id删除用户等级规则
		try {
			if(StringUtils.isNotBlank(id.toString())){
			   userGradeRuleService.deleteUserGradeRule(id);
			   GwsLogger.info("根据id删除用户等级规则成功:code={},message={},id={}",code,message,id);
			}else{
			   code = CommConstant.GWSCOD0001;
			   message = CommConstant.GWSMSG0001;
			   GwsLogger.info("根据id删除用户等级规则失败:code={},message={},id={}",code,message,id);
			}
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("根据id删除用户等级规则异常:code={},message={},id={},e={}",code,message,id,e);
		}
		GwsLogger.info("根据id删除用户等级规则结束:code={},message={},id={}",code,message,id);
		return RetResult.setRetDate(code, message, null);
	}

 /**
	* 【根据id查询用户等级规则】
	* @param id
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年04月17日 上午11:56:26
	*/ 
	@ApiOperation(value = "根据id查询用户等级规则",notes="根据id查询用户等级规则")
	@ApiImplicitParam(name ="id",value ="主键id", required = true, dataType ="String")
	@RequestMapping(value = "/findGradeRule",method =RequestMethod.POST)
	public @ResponseBody RetResult findGradeRule(@RequestParam("id") String id){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("根据id查询用户等级规则开始:code={},message={},id={}",code,message,id);
		//根据id查询用户等级规则
		UserGradeRule userGradeRule = null;
		try {
			userGradeRule = userGradeRuleService.findUserGradeRule(Long.valueOf(id));
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("根据id查询用户等级规则异常:code={},message={},id={},e={}",code,message,id,e);
		}
		GwsLogger.info("根据id查询用户等级规则结束:code={},message={}",code,message);
		return RetResult.setRetDate(code, message, userGradeRule);
	}

	/**
	* 【查询所有用户等级规则】
	* @param userGradeRule
	* @param pageNo
	* @param pageSize
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年04月17日 上午11:56:26
	*/ 
	@ApiOperation(value = "查询所有用户等级规则",notes="查询所有用户等级规则")
	@ApiImplicitParams({
		@ApiImplicitParam(name ="userGradeRule",value ="详细实体userGradeRule", required = true, dataType ="UserGradeRuleVO"),
		@ApiImplicitParam(name ="pageNo",value ="第几页", required = true, dataType ="Long"),
		@ApiImplicitParam(name ="pageSize",value ="每页条数", required = true, dataType ="String")
		})
	@RequestMapping(value = "/findUserGradeRule",method =RequestMethod.POST)
	public @ResponseBody RetResult findUserGradeRule(@JsonParam UserGradeRuleVO userGradeRule,Integer pageNo,Integer pageSize){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("查询所有用户等级规则开始:pageNo={},pageSize={},userGradeRule",pageNo,pageSize,ToStringBuilder.reflectionToString(userGradeRule));
		//查询所有用户等级规则
		Page<UserGradeRule> page = null;
		try {
			page = userGradeRuleService.findUserGradeRule(userGradeRule,pageNo-1,pageSize,"grade");
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("查询所有用户等级规则异常:code={},message={},userGradeRule={},pageNo={},pageSize={},softField={},e={}",code,message,ToStringBuilder.reflectionToString(userGradeRule),pageNo-1,pageSize,e);
		}
		GwsLogger.info("查询所有用户等级规则结束:pageNo={},pageSize={},softField={}",pageNo-1,pageSize);
		return RetResult.setRetDate(code, message, page);
	}

	/**
	* 【修改用户等级规则】
	* @param userGradeRule
	* @return RetResult
	* @version 1.0
	* @author Hermit 2017年04月17日 上午11:56:26
	*/ 
	@ApiOperation(value = "修改用户等级规则",notes="修改用户等级规则")
	@ApiImplicitParam(name ="grade",value ="详细实体userGradeRule", required = true, dataType ="UserGradeRule")
	@RequestMapping(value = "/updateUserGradeRule",method =RequestMethod.POST)
	public @ResponseBody RetResult updateUserGradeRule(@JsonParam UserGradeRule grade){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("修改用户等级规则开始:userGradeRule",ToStringBuilder.reflectionToString(grade));
		//修改用户等级规则
		try {
			grade = userGradeRuleService.updateUserGradeRule(grade);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("修改用户等级规则异常:code={},message={},userGradeRule={},e={}",code,message,ToStringBuilder.reflectionToString(grade),e);
		}
		GwsLogger.info("修改用户等级规则结束");
		return RetResult.setRetDate(code, message, grade);
	}
}

