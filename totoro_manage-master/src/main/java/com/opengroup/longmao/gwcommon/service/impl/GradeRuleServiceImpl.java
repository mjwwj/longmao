package com.opengroup.longmao.gwcommon.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.opengroup.longmao.gwcommon.configuration.log.GwsLogger;
import com.opengroup.longmao.gwcommon.configuration.redis.cache.IdGlobalGenerator;
import com.opengroup.longmao.gwcommon.entity.po.UserGradeRule;
import com.opengroup.longmao.gwcommon.entity.vo.UserGradeRuleVO;
import com.opengroup.longmao.gwcommon.enums.IsDeleteEnum;
import com.opengroup.longmao.gwcommon.repository.master.GradeRuleRepositoryMaster;
import com.opengroup.longmao.gwcommon.repository.queryFilter.GradeRuleQueryFilter;
import com.opengroup.longmao.gwcommon.repository.slave.GradeRuleRepositorySlave;
import com.opengroup.longmao.gwcommon.service.GradeRuleService;
import com.opengroup.longmao.gwcommon.tools.date.DateUtil;
/**
 * 【用户等级规则】 Service接口实现
 *
 * @version 1.0
 * @author Hermit 2017年04月17日 上午11:56:26
 */ 
@Service
public class GradeRuleServiceImpl implements GradeRuleService{

	@Autowired
	private GradeRuleRepositoryMaster userGradeRuleRepositoryMaster;

	@Autowired
	private GradeRuleRepositorySlave userGradeRuleRepositorySlave;

	@Autowired
	private IdGlobalGenerator idGlobalGenerator;

	/**
	* 【分页查询用户等级规则】
	* @param userGradeRule
	* @param pageNo
	* @param pageSize
	* @param sortField
	* @return userGradeRule
	* @version 1.0
	* @author Hermit 2017年04月17日 上午11:56:26
	*/ 
	@Override
	public Page<UserGradeRule> findUserGradeRule(UserGradeRuleVO vo,Integer pageNo, Integer pageSize, String sortField){
		// 组合查询语句
		GradeRuleQueryFilter query = new GradeRuleQueryFilter();
		
		if(null != vo){
			if(StringUtils.isNotBlank(vo.getEmpirical())){
				query.setMinVal(Long.parseLong(vo.getEmpirical()));;
			}
			if(StringUtils.isNotBlank(vo.getEmpirical())){
				query.setMaxVal(Long.parseLong(vo.getEmpirical()));;
			}
			if(StringUtils.isNotBlank(vo.getGradeVO())){
				query.setGrade(Short.valueOf(vo.getGradeVO()));;
			}
			if(StringUtils.isNotBlank(vo.getAliasVO())){
				query.setAlias(vo.getAliasVO());;
			}
		}
		
		query.setIsDelete(IsDeleteEnum.UN_DELETE.getVal());
		//字段排序
		Sort sort = new Sort(Direction.ASC, sortField);
		// 分页
		Pageable page = new PageRequest(pageNo, pageSize, sort);
		// 查询分页数据
		Page<UserGradeRule> pageList = userGradeRuleRepositorySlave.findAll(query, page);
		if (CollectionUtils.isNotEmpty(pageList.getContent())) {
			List<UserGradeRule> uL = pageList.getContent();
			for (UserGradeRule u : uL) {
				u.setTimeC(DateUtil.timestampToDates(u.getCtime(), DateUtil.TIME_PATTON_DEFAULT));
				u.setTimeU(DateUtil.timestampToDates(u.getUtime(), DateUtil.TIME_PATTON_DEFAULT));
			}
		}
		return pageList;
	}
	/**
	* 【根据id查询用户等级规则】
	* @param id
	* @return userGradeRule
	* @version 1.0
	* @author Hermit 2017年04月17日 上午11:56:26
	*/ 
	@Override
	public UserGradeRule findUserGradeRule(Long id){
	    UserGradeRule userGradeRule = null;
		if(StringUtils.isNotBlank(id.toString())){
	      userGradeRule = userGradeRuleRepositorySlave.findOne(id);
		}else{
		  GwsLogger.info("id不存在");
		}
		return userGradeRule;
	}
	/**
	* 【保存用户等级规则】
	* @param userGradeRule
	* @return userGradeRule
	* @version 1.0
	* @author Hermit 2017年04月17日 上午11:56:26
	*/ 
	@Override
	public UserGradeRule saveUserGradeRule(UserGradeRule grade){
		UserGradeRule userGrade = null;
		//判断对象是否存在
		if(grade!= null){
		   //id统一生成
		   Long id = idGlobalGenerator.getSeqId(UserGradeRule.class);
		   grade.setId(id);
		   grade.setCtime(DateUtil.currentSecond());
		   grade.setIsDelete(IsDeleteEnum.UN_DELETE.getVal());
		   userGrade = userGradeRuleRepositoryMaster.save(grade);
		   GwsLogger.info("用户等级规则保存成功");
		}else{
			GwsLogger.info("用户等级规则对象不存在，保存失败:userGradeRule={}",ToStringBuilder.reflectionToString(grade));
		    return null;
		}
		return userGrade;
	}

	/**
	* 【修改用户等级规则】
	* @param userGradeRule
	* @return userGradeRule
	* @version 1.0
	* @author Hermit 2017年04月17日 上午11:56:26
	*/ 
	@Override
	public UserGradeRule updateUserGradeRule(UserGradeRule u){
		if(null != u){
		    //先从库中查出该对象
			UserGradeRule ub = userGradeRuleRepositorySlave.findOne(Long.valueOf(u.getId()));
		    //判断对象是否存在
			if(ub!= null){
				ub.setGrade(u.getGrade());
				ub.setMinVal(u.getMinVal());
				ub.setMaxVal(u.getMaxVal());
				ub.setNote(u.getNote());
				ub.setAlias(u.getAlias());
				ub.setIsMax(u.getIsMax());
				ub.setUtime(DateUtil.currentSecond());
				u = userGradeRuleRepositoryMaster.save(ub);
			   GwsLogger.info("用户等级规则修改成功");
			}else{
			    GwsLogger.info("用户等级规则对象不存在，修改失败:userGradeRule={}",ToStringBuilder.reflectionToString(u));
		        return null;
			}
		}else{
			 GwsLogger.error("用户等级规则id不存在，修改失败:userGradeRule={}",ToStringBuilder.reflectionToString(u));
		     return null;
		}
		return u;
	}

	/**
	 * 【根据id删除用户等级规则】
	 * @param id
	 * @return void
	 * @version 1.0
	 * @author Hermit 2017年04月17日 上午11:56:26
	 */ 
	@Override
	public void deleteUserGradeRule(Long id){
		//先从库中查出该对象
		UserGradeRule userGradeRule = userGradeRuleRepositorySlave.findOne(id);
		//判断对象是否存在
		if(userGradeRule!=null){
			//将用户状态改为删除
			//userGradeRule.setIsDelete(IsDeleteEnum.YES.getVal());
			UserGradeRule newUserGradeRule = userGradeRuleRepositoryMaster.save(userGradeRule);
			//判断对象是否存在
			if(newUserGradeRule!=null){
				GwsLogger.info("用户等级规则删除成功");
			}else{
				GwsLogger.info("用户等级规则删除失败:id={}",id);
			}
		}else{
			GwsLogger.info("用户等级规则对象不存在:id={}",id);
		}
	}

}

