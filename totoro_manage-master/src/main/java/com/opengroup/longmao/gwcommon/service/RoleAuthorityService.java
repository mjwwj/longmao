package com.opengroup.longmao.gwcommon.service;

import java.util.List;

import com.opengroup.longmao.gwcommon.entity.po.RoleAuthority;

/**
 * 
 * 角色管理
 *
 * @version 
 * @author zengjq  2016年4月18日 上午11:42:06
 *
 */
public interface RoleAuthorityService {
	/**
	 * 
	 * 查询所有的角色
	 * 
	 * @author zengjq 2016年4月18日
	 * @return
	 */
	 List<RoleAuthority> listAllRoles();

	/**
	 * 
	 * 根据角色id查询角色
	 * 
	 * @author zengjq 2016年4月18日
	 * @param roleId
	 * @return
	 */
	RoleAuthority getRoleById(Integer roleId);

	/**
	 * 
	 * 插入角色
	 * 
	 * @author zengjq 2016年4月18日
	 * @param role
	 * @return
	 */
	RoleAuthority saveRole(RoleAuthority role);

	/**
	 * 
	 * 修改角色和权限
	 * 
	 * @author zengjq 2016年4月18日
	 * @param role
	 * @return
	 */
	RoleAuthority updateRoleAuthority(RoleAuthority role);

	/**
	 * 
	 *  根据角色id删除角色
	 * 
	 * @author zengjq 2016年4月18日
	 * @param roleId
	 * @return 
	 */
	RoleAuthority deleteRoleById(Integer roleId);

	/**
	 * 
	 * 根据名称查询角色
	 * 
	 * @author zengjq 2016年4月18日
	 * @param name
	 * @return
	 */
	RoleAuthority getRoleAuthorityByName(String name);
	
	/**
	 * 
	 * 修改权限
	 * 
	 * @author zengjq 2016年4月18日
	 * @param role
	 * @return
	 */
	RoleAuthority updateRoleRights(RoleAuthority role);
}
