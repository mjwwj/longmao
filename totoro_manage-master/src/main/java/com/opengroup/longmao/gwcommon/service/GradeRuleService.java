package com.opengroup.longmao.gwcommon.service;

import org.springframework.data.domain.Page;
import com.opengroup.longmao.gwcommon.entity.po.UserGradeRule;
import com.opengroup.longmao.gwcommon.entity.vo.UserGradeRuleVO;

/**
 * 【用户等级规则】 service接口
 *
 * @version 1.0
 * @author Hermit 2017年04月17日 上午11:56:26
 */ 
public interface GradeRuleService {

	 /**
	 * 【保存用户等级规则】
	 * @param userGradeRule
	 * @return userGradeRule
	 * @version 1.0
	 * @author Hermit 2017年04月17日 上午11:56:26
	 */ 
	 UserGradeRule saveUserGradeRule(UserGradeRule userGradeRule);

	 /**
	 * 【删除用户等级规则】
	 * @param id
	 * @return void
	 * @version 1.0
	 * @author Hermit 2017年04月17日 上午11:56:26
	 */ 
	 void deleteUserGradeRule(Long id);


	 /**
	 * 【修改用户等级规则】
	 * @param userGradeRule
	 * @return userGradeRule
	 * @version 1.0
	 * @author Hermit 2017年04月17日 上午11:56:26
	 */ 
	 UserGradeRule updateUserGradeRule(UserGradeRule userGradeRule);

	 /**
	 * 【查询用户等级规则】
	 * @param id
	 * @return UserGradeRule
	 * @version 1.0
	 * @author Hermit 2017年04月17日 上午11:56:26
	 */ 
	 UserGradeRule findUserGradeRule(Long id);


	 /**
	 * 【查询用户等级规则】
	 * @param UserGradeRuleVO
	 * @param pageNo
	 * @param pageSize
	 * @param sortField
	 * @return Page<UserGradeRule>
	 * @version 1.0
	 * @author Hermit 2017年04月17日 上午11:56:26
	 */ 
	 Page<UserGradeRule> findUserGradeRule(UserGradeRuleVO userGradeRule,Integer pageNo,Integer pageSize,String sortField);


}

