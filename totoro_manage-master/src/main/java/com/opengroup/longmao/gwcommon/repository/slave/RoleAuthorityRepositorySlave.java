package com.opengroup.longmao.gwcommon.repository.slave;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.opengroup.longmao.gwcommon.configuration.query.core.BaseRepository;
import com.opengroup.longmao.gwcommon.entity.po.RoleAuthority;

public interface RoleAuthorityRepositorySlave extends BaseRepository<RoleAuthority, Integer> {
	/**
	 * 
	 * 查询所有的角色
	 * 
	 * @author zengjq 2017年4月17日
	 * @return
	 */
	@Query("select r from RoleAuthority r where r.isDelete = 1")
	List<RoleAuthority> listAllRoles();

	/**
	 * 
	 * 根据名称查询，返回对象
	 * 
	 * @author zengjq 2017年4月18日
	 * @param name
	 * @return
	 */
	@Query("select r from RoleAuthority r where r.roleName=?1 and r.isDelete =1 ")
	RoleAuthority getRoleAuthorityByName(String name);
	
	/**
	 * 
	 *  查询表中最大id
	 * 
	 * @author zengjq 2017年4月17日
	 * @return
	 */
	@Query("select Max(r.roleId) from RoleAuthority r")
	Integer getRoleAuthorityMaxId();
}
