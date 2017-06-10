package com.opengroup.longmao.gwcommon.service.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opengroup.longmao.gwcommon.entity.po.AdminInfo;
import com.opengroup.longmao.gwcommon.entity.po.RoleAuthority;
import com.opengroup.longmao.gwcommon.enums.IsNormalEnum;
import com.opengroup.longmao.gwcommon.repository.master.RoleAuthorityRepositoryMaster;
import com.opengroup.longmao.gwcommon.repository.slave.RoleAuthorityRepositorySlave;
import com.opengroup.longmao.gwcommon.service.AdminInfoService;
import com.opengroup.longmao.gwcommon.service.RoleAuthorityService;
import com.opengroup.longmao.gwcommon.tools.date.DateUtil;

/**
 * 
 * 角色管理service接口实现
 *
 * @version 
 * @author zengjq  2016年5月18日 下午12:09:21
 *
 */
@Service
public class RoleAuthorityServiceImpl implements RoleAuthorityService {

	@Autowired
	private RoleAuthorityRepositoryMaster roleAuthorityRepositoryMaster;

	@Autowired
	private RoleAuthorityRepositorySlave roleAuthorityRepositorySlave;
	
	@Autowired
	private AdminInfoService adminInfoService;

	/**
	 * 
	 * 查询所有的角色
	 * 
	 * (non-Javadoc)
	 * @see com.gws.services.RoleAuthorityService#listAllRoles()
	 */
	@Override
	public  List<RoleAuthority> listAllRoles() {
		return roleAuthorityRepositorySlave.listAllRoles();
	}

	/**
	 * 
	 *  根据角色id删除角色
	 * 
	 * (non-Javadoc)
	 * @see com.gws.services.RoleAuthorityService#deleteRoleById(java.lang.Integer)
	 */
	@Override
	public RoleAuthority deleteRoleById(Integer roleId) {
		RoleAuthority newRoleAuthority = null;
		List<AdminInfo> AdminInfo = adminInfoService.getAdminInfoByRoleId(roleId);
		if(CollectionUtils.isEmpty(AdminInfo)){
			//通过id查询对象
			RoleAuthority roleAuthority  =roleAuthorityRepositoryMaster.findOne(roleId) ;
			//将状态改为删除
			roleAuthority.setIsDelete(IsNormalEnum.NO.getVal());
			//做修改操作
			newRoleAuthority = roleAuthorityRepositoryMaster.save(roleAuthority);
		}
		return newRoleAuthority;
	}
	
	/**
	 * 
	 * 根据角色id查询角色
	 * 
	 * (non-Javadoc)
	 * @see com.gws.services.RoleAuthorityService#getRoleById(java.lang.Integer)
	 */
	public RoleAuthority getRoleById(Integer roleId) {
		return roleAuthorityRepositoryMaster.findOne(roleId);
	}

	/**
	 * 根据名称查询，返回用户对象
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.gws.services.AdminInfoService#getAdminInfoByName(java.lang.String)
	 */
	@Override
	public RoleAuthority getRoleAuthorityByName(String name) {
		return  roleAuthorityRepositorySlave.getRoleAuthorityByName(name);
	}
	
	/**
	 * 
	 *  插入角色
	 * 
	 * (non-Javadoc)
	 * @see com.gws.services.RoleAuthorityService#insertRole(com.gws.entity.RoleAuthority)
	 */
	@Override
	public RoleAuthority saveRole(RoleAuthority role) {
		RoleAuthority newRole = null;
		if(StringUtils.isNotBlank(role.getRoleName())){
			//先从库中查询对象信息
			RoleAuthority roleDate = roleAuthorityRepositorySlave.getRoleAuthorityByName(role.getRoleName());
			//判断该对象是否存在
			if(roleDate==null){
				Integer roleId = roleAuthorityRepositorySlave.getRoleAuthorityMaxId()+1;
				role.setRoleId(roleId);
				role.setCtime(DateUtil.currentSecond());
				role.setUtime(DateUtil.currentSecond());
				role.setIsDelete(IsNormalEnum.YES.getVal());
				newRole = roleAuthorityRepositoryMaster.save(role);
		    }
		}
		return newRole;
	}

	/**
	 * 
	 * 修改角色
	 * 
	 * (non-Javadoc)
	 * @see com.gws.services.RoleAuthorityService#updateRoleAuthority(com.gws.entity.RoleAuthority)
	 */
	@Override
	public RoleAuthority updateRoleAuthority(RoleAuthority role) {
		RoleAuthority newRole= null;
		if(null!=role){
			if(StringUtils.isNotBlank(role.getRoleName())){
				//先从库中查询对象信息
				RoleAuthority ra = roleAuthorityRepositorySlave.getRoleAuthorityByName(role.getRoleName());
				if(null==ra){
					//先从库中查询对象信息
					RoleAuthority  roleDate = roleAuthorityRepositoryMaster.findOne(role.getRoleId());
					//将填写的新值set进去
					roleDate.setRoleName(role.getRoleName());
					roleDate.setUtime(DateUtil.currentSecond());
					// 保存后并返回用户对象
					newRole = roleAuthorityRepositoryMaster.save(roleDate);
				}
			}
		}
		return newRole;
	}
	
	/**
	 * 
	 * 修改权限
	 * 
	 * (non-Javadoc)
	 * @see com.gws.services.RoleAuthorityService#updateRoleRights(com.gws.entity.RoleAuthority)
	 */
	@Override
	public RoleAuthority updateRoleRights(RoleAuthority role) {
		RoleAuthority newRole= null;
		if(null!=role){
			// 保存后并返回用户对象
			newRole = roleAuthorityRepositoryMaster.save(role);
		}
		return newRole;
	}
}
