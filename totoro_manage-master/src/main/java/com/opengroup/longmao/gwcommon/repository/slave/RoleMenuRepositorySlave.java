package com.opengroup.longmao.gwcommon.repository.slave;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.opengroup.longmao.gwcommon.configuration.query.core.BaseRepository;
import com.opengroup.longmao.gwcommon.entity.po.RoleMenu;

public interface RoleMenuRepositorySlave extends BaseRepository<RoleMenu, Integer> {

	/**
	 * 
	 * 查询所有的父菜单
	 * 
	 * @author zengjq 2017年4月18日
	 * @return
	 */
	@Query("select r from RoleMenu r where r.parentId is null and r.isDelete = 1  order by r.sort desc")
	 List<RoleMenu> listAllParentMenu();
	
	/**
	 * 
	 * 查询所有的子菜单
	 * 
	 * @author zengjq 2017年4月18日
	 * @return
	 */
	@Query("select r from RoleMenu r where r.parentId is not null and r.isDelete = 1")
	 List<RoleMenu> listAllSubMenu();

	/**
	 * 
	 *  根据父菜单id查询所有的子菜单信息 
	 * 
	 * @author zengjq 2017年4月12日
	 * @param isDelete
	 * @return
	 */
	@Query("select r from RoleMenu r where r.parentId = ?1 and r.isDelete = 1")
	 List<RoleMenu> listSubMenuByParentId(Integer parentId);
	
	/**
	 * 
	 * 根据名称查询菜单，返回对象
	 * 
	 * @author zengjq 2017年4月18日
	 * @param name
	 * @return
	 */
	@Query("select r from RoleMenu r where r.menuName=?1 and r.isDelete =1 ")
	RoleMenu getRoleMenuByName(String name);
	
	/**
	 * 
	 * 根据菜单id删除菜单
	 * 
	 * @author zengjq 2017年4月18日
	 * @param menuId
	 * @return
	 */
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("update RoleMenu r set r.isDelete = -1 where r.menuId=?1 or r.parentId=?1 ")
	Integer deleteMenuById(Integer menuId);
	
	/**
	 * 
	 *  查询表中最大id
	 * 
	 * @author zengjq 2017年4月17日
	 * @return
	 */
	@Query("select Max(r.menuId) from RoleMenu r")
	Integer getRoleMenuMaxId();
}
