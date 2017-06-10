package com.opengroup.longmao.gwcommon.service;

import java.util.List;

import com.opengroup.longmao.gwcommon.entity.po.RoleMenu;

public interface RoleMenuService {
	/**
	 * 
	 * 查询所有的菜单信息
	 * 
	 * @author zengjq 2016年4月18日
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<RoleMenu> listAllMenu();

	/**
	 * 
	 * 查询所有的父菜单信息
	 * 
	 * @author zengjq 2016年4月18日
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<RoleMenu> listAllParentMenu();

	/**
	 * 
	 * 查询所有的子菜单
	 * 
	 * @author zengjq 2016年4月18日
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<RoleMenu> listAllSubMenu();
	
	/**
	 * 
	 * 根据parentId查询所有的子菜单信息 
	 * 
	 * @author zengjq 2016年4月18日
	 * @param parentId
	 * @return
	 */
	List<RoleMenu> listSubMenuByParentId(Integer parentId);

	/**
	 * 
	 * 根据menuId查询对象
	 * 
	 * @author zengjq 2016年4月18日
	 * @param menuId
	 * @return
	 */
	RoleMenu getMenuById(Integer menuId);
	
	/**
	 * 
	 * 根据名称查询菜单
	 * 
	 * @author zengjq 2016年4月18日
	 * @param name
	 * @return
	 */
	RoleMenu getRoleMenuByName(String name);

	/**
	 * 
	 * 保存菜单信息
	 * 
	 * @author zengjq 2016年4月18日
	 * @param menu
	 */
	RoleMenu saveMenu(RoleMenu menu);
	
	/**
	 * 
	 * 修改菜单信息
	 * 
	 * @author zengjq 2016年4月18日
	 * @param menu
	 * @return
	 */
	RoleMenu updateRoleMenu(RoleMenu menu);

	/**
	 * 
	 * 根据menuId删除对象
	 * 
	 * @author zengjq 2016年4月18日
	 * @param menuId
	 */
	Integer deleteMenuById(Integer menuId);

	/**
	 * 
	 * 菜单排序
	 * 
	 * @author zengjq 2016年6月17日
	 * @param sort
	 * @param menuId
	 * @return
	 */
	RoleMenu updateMenuSortById(Integer menuId,Integer sort);

}
