package com.opengroup.longmao.gwcommon.entity.dto;

import java.io.Serializable;

import com.opengroup.longmao.gwcommon.entity.po.RoleAuthority;

/**
 * UserInfoDto，UserInfo继承类
 *
 * @version
 * @author zengjq 2017年04月14日 下午12:07:43
 */
public class AdminInfoDTO implements Serializable {

	/**
	 * Comments for <code>serialVersionUID</code>
	 * 【请在此输入描述文字】
	 */
	private static final long serialVersionUID = -512986873217971092L;

	private String userSexName;

	private String userRoleName;
	
	private String userStatusName;

	private RoleAuthority roleAuthority;

	public String getUserSexName() {
		return userSexName;
	}

	public void setUserSexName(String userSexName) {
		this.userSexName = userSexName;
	}

	public String getUserRoleName() {
		return userRoleName;
	}

	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}

	public String getUserStatusName() {
		return userStatusName;
	}

	public void setUserStatusName(String userStatusName) {
		this.userStatusName = userStatusName;
	}

	public RoleAuthority getRoleAuthority() {
		return roleAuthority;
	}

	public void setRoleAuthority(RoleAuthority roleAuthority) {
		this.roleAuthority = roleAuthority;
	}
	
}
