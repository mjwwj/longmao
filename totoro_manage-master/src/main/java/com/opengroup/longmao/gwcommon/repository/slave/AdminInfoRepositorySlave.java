package com.opengroup.longmao.gwcommon.repository.slave;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.opengroup.longmao.gwcommon.configuration.query.core.BaseRepository;
import com.opengroup.longmao.gwcommon.entity.po.AdminInfo;

public interface AdminInfoRepositorySlave extends BaseRepository<AdminInfo, Long> {
	/**
	 * 
	 * 根据角色Id查询用户列表
	 * 
	 * @author zengjq 2017年4月21日
	 * @param roleId
	 * @return
	 */
	@Query("select u from AdminInfo u where u.roleId = ?1 and u.isDelete = 1")
	 List<AdminInfo> getAdminInfoByRoleId(Integer roleId);

	/**
	 * 
	 * 根据用户名查询，返回用户对象
	 * 
	 * @author zengjq 2017年4月12日
	 * @param userName
	 * @return
	 */
	@Query("select u from AdminInfo u where u.userName=?1 and u.userStatus =?2 and u.isDelete=?3 ")
	AdminInfo getAdminInfoByName(String userName,Short userStatus,Short isDelete);


	/**
	 * 
	 *  查询表中最大id
	 * 
	 * @author zengjq 2017年4月17日
	 * @return
	 */
	@Query("select Max(r.userId) from AdminInfo r")
	Integer getAdminInfoMaxId();
}
