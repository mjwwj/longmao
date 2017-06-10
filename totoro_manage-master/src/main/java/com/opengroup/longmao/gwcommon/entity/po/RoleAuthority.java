package com.opengroup.longmao.gwcommon.entity.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RoleAuthority 实体类
*
 * @version
 * @author zengjq 2017年04月18日 下午15:10:22
 */ 
@Entity
@Table(name = "role_authority") 
public class RoleAuthority implements Serializable {

	/**
	 * Comments for <code>serialVersionUID</code>
	 * 【请在此输入描述文字】
	 */
	private static final long serialVersionUID = -4547496927704192036L;

	@Id
	@Column(name = "role_id")
	private Integer roleId;

	@Column(name = "role_name")
	private String roleName;

	@Column(name = "rights")
	private String rights;

	@Column(name = "is_delete")
	private Short isDelete;

	@Column(name = "ctime")
	private Integer ctime;

	@Column(name = "utime")
	private Integer utime;

	public String getRoleName() {
		return roleName;
	}

	public String getRights() {
		return rights;
	}

	public Short getIsDelete() {
		return isDelete;
	}

	public Integer getCtime() {
		return ctime;
	}

	public Integer getUtime() {
		return utime;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

	public void setIsDelete(Short isDelete) {
		this.isDelete = isDelete;
	}

	public void setCtime(Integer ctime) {
		this.ctime = ctime;
	}

	public void setUtime(Integer utime) {
		this.utime = utime;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

}

