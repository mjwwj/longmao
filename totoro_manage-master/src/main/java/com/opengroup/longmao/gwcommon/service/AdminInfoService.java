package com.opengroup.longmao.gwcommon.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.opengroup.longmao.gwcommon.entity.po.AdminInfo;
import com.opengroup.longmao.gwcommon.entity.vo.AdminInfoVO;

/**
 * 
 * 用户管理service接口
 *
 * @version 
 * @author zengjq  2016年4月18日 上午11:50:55
 *
 */
public interface AdminInfoService {

	/**
	 * 
	 * 通过条件批量查询用户，返回泛型List
	 * 
	 * @author zengjq 2016年4月12日
	 * @return List<AdminInfo>
	 */
	Page<AdminInfo> findAllAdminInfos(AdminInfoVO adminInfoVo, Integer pageNo,Integer pageSize,String sortField);
	
	/**
	 * 
	 * 根据用户名查询，返回用户对象
	 * 
	 * @author zengjq 2016年4月11日
	 * @param userName
	 * @return AdminInfo
	 */
	AdminInfo getAdminInfoByName(String userName,Short userStatus,Short isDelete);
	
	/**
	 * 
	 * 根据用户Id查询，返回用户对象
	 * 
	 * @author zengjq 2016年4月12日
	 * @param id
	 * @return AdminInfo
	 */
	AdminInfo getAdminInfoById(Long id);
	
	/**
	 * 
	 * 根据角色Id查询用户列表
	 * 
	 * @author zengjq 2016年7月21日
	 * @param roleId
	 * @return
	 */
	List<AdminInfo> getAdminInfoByRoleId(Integer roleId);
	
	/**
	 * 
	 * 保存用户信息，并返回该条数据
	 * 
	 * @author zengjq 2016年4月12日
	 * @param AdminInfo
	 * @return AdminInfo
	 */
	AdminInfo saveAdminInfo(AdminInfo adminInfo);
	
	/**
	 * 
	 * 【保存用户权限】
	 * 
	 * @author Hermit 2017年5月9日
	 * @param userId
	 * @param rights
	 * @return
	 */
	AdminInfo saveAdminAuth(Long userId,String rights);
	
	/**
	 * 
	 * 修改用户信息，并返回该条数据
	 * 
	 * @author zengjq 2016年4月12日
	 * @param AdminInfo
	 * @return AdminInfo
	 */
	AdminInfo updateAdminInfo(AdminInfo adminInfo);

	/**
	 * 
	 * 根据ID删除单条数据(逻辑删除，非物理删除)
	 * 
	 * @author zengjq 2016年4月12日
	 * @param AdminInfo
	 * @return void
	 */
	void deleteById(Long userId);

}
