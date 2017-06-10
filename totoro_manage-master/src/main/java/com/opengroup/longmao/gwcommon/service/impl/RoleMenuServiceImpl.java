package com.opengroup.longmao.gwcommon.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opengroup.longmao.gwcommon.entity.po.RoleMenu;
import com.opengroup.longmao.gwcommon.enums.IsNormalEnum;
import com.opengroup.longmao.gwcommon.repository.master.RoleMenuRepositoryMaster;
import com.opengroup.longmao.gwcommon.repository.slave.RoleMenuRepositorySlave;
import com.opengroup.longmao.gwcommon.service.RoleMenuService;
import com.opengroup.longmao.gwcommon.tools.date.DateUtil;

/**
 * 
 * 菜单管理service接口实现
 *
 * @version 
 * @author zengjq  2016年5月18日 下午4:01:48
 *
 */
@Service
public class RoleMenuServiceImpl implements RoleMenuService {

	@Autowired
	private RoleMenuRepositoryMaster roleMenuRepositoryMaster;

	@Autowired
	private RoleMenuRepositorySlave roleMenuRepositorySlave;

	/**
	 * 
	 * 查询所有的菜单，先查询所有的父菜单，再通过父菜单查询子菜单
	 * 
	 * (non-Javadoc)
	 * @see com.gws.services.RoleMenuService#listAllMenu()
	 */
	@Override
	public List<RoleMenu> listAllMenu() {
		List<RoleMenu> rmList = this.listAllParentMenu();
		for (RoleMenu roleMenu : rmList) {
			List<RoleMenu> subList = this.listSubMenuByParentId(roleMenu.getMenuId());
			roleMenu.setSubMenu(subList);
		}
		return rmList;
	}

	/**
	 * 
	 * 查询所有的父类菜单
	 * 
	 * (non-Javadoc)
	 * @see com.gws.services.RoleMenuService#listAllParentMenu()
	 */
	@Override
	public List<RoleMenu> listAllParentMenu() {
		return roleMenuRepositorySlave.listAllParentMenu();
	}

	/**
	 * 
	 * 查询所有的子菜单
	 * 
	 * (non-Javadoc)
	 * @see com.gws.services.RoleMenuService#listAllSubMenu()
	 */
	@Override
	public List<RoleMenu> listAllSubMenu() {
		return roleMenuRepositorySlave.listAllSubMenu();
	}
	
	/**
	 * 
	 * 根据父菜单id查询所有的子菜单
	 * 
	 * (non-Javadoc)
	 * @see com.gws.services.RoleMenuService#listSubMenuByParentId(java.lang.Integer)
	 */
	@Override
	public List<RoleMenu> listSubMenuByParentId(Integer parentId) {
		return roleMenuRepositorySlave.listSubMenuByParentId(parentId);
	}

	/**
	 * 
	 * 根据菜单id查询菜单对象
	 * 
	 * (non-Javadoc)
	 * @see com.gws.services.RoleMenuService#getMenuById(java.lang.Integer)
	 */
	@Override
	public RoleMenu getMenuById(Integer menuId) {
		return roleMenuRepositorySlave.findOne(menuId);
	}

	/**
	 * 
	 * 插入菜单信息
	 * 
	 * (non-Javadoc)
	 * @see com.gws.services.RoleMenuService#saveMenu(com.gws.entity.RoleMenu)
	 */
	@Override
	public RoleMenu saveMenu(RoleMenu menu) {
		RoleMenu newRoleMenu = null;
		Integer menuId = 0;
		//判断用户名是否为空
		if(StringUtils.isNotBlank(menu.getMenuName())){
			//先从库中查询对象信息是否存在
			RoleMenu roleMenuDate = roleMenuRepositorySlave.getRoleMenuByName(menu.getMenuName());
			//判断该对象是否存在
			if(roleMenuDate==null){
//				menu.setMenuId(idGlobalGenerator.getSeqId(RoleMenu.class));
				Integer dbId = roleMenuRepositorySlave.getRoleMenuMaxId();
				if(null != dbId){
					menuId = dbId + 1;
				}else{
					menuId = menuId + 1;
				}
				menu.setMenuId(menuId);
				menu.setSort(0);
				menu.setCtime(DateUtil.currentSecond());
				menu.setUtime(DateUtil.currentSecond());
				menu.setIsDelete(IsNormalEnum.YES.getVal());
				newRoleMenu = roleMenuRepositoryMaster.save(menu);
		    }
		}
		return newRoleMenu;
	}

	/**
	 * 
	 * 根据菜单id删除菜单(删除父菜单的同时要删除子菜单)
	 * 
	 * (non-Javadoc)
	 * @see com.gws.services.RoleMenuService#deleteMenuById(java.lang.Integer)
	 */
	@Override
	public Integer deleteMenuById(Integer menuId) {
		return roleMenuRepositorySlave.deleteMenuById(menuId);
	}
	
	/**
	 * 
	 * 菜单排序
	 * 
	 * (non-Javadoc)
	 * @see com.gws.services.RoleMenuService#deleteMenuById(java.lang.Integer)
	 */
	@Override
	public RoleMenu updateMenuSortById(Integer menuId,Integer sort) {
		RoleMenu newMenu = null;
		if (menuId != null &&menuId> 0) {
			//先从库中查询对象信息
			RoleMenu  menuDate = roleMenuRepositoryMaster.findOne(menuId);
			if(sort != null){
				//将填写的新值set进去
				menuDate.setSort(menuDate.getSort()+sort);
			}
			menuDate.setUtime(DateUtil.currentSecond());
			// 保存后并返回用户对象
			 newMenu = roleMenuRepositoryMaster.save(menuDate);
		}
		return newMenu;
	}

	/**
	 * 
	 * 根据名称查菜单
	 * 
	 * (non-Javadoc)
	 * @see com.gws.services.RoleMenuService#getRoleMenuByName(java.lang.String)
	 */
	@Override
	public RoleMenu getRoleMenuByName(String name) {
		return  roleMenuRepositorySlave.getRoleMenuByName(name);
	}

	/**
	 * 
	 * 修改菜单
	 * 
	 * (non-Javadoc)
	 * @see com.gws.services.RoleMenuService#updateRoleMenu(com.gws.entity.RoleMenu)
	 */
	@Override
	public RoleMenu updateRoleMenu(RoleMenu menu) {
		RoleMenu newMenu = null;
		if (menu.getMenuId() != null && menu.getMenuId()> 0) {
			//先从库中查询对象信息
			RoleMenu menuDate = roleMenuRepositoryMaster.findOne(menu.getMenuId());
			//判断该对象是否存在
			if(menuDate!=null){
				//将填写的新值set进去
				menuDate.setMenuName(menu.getMenuName());
				menuDate.setParentId(menu.getParentId());
				menuDate.setMenuUrl(menu.getMenuUrl());
				menuDate.setUtime(DateUtil.currentSecond());
				// 保存后并返回用户对象
				 newMenu = roleMenuRepositoryMaster.save(menuDate);
			}
		}
		return newMenu;
	}

}
