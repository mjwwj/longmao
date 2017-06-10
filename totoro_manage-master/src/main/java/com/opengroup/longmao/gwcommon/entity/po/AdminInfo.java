package com.opengroup.longmao.gwcommon.entity.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.opengroup.longmao.gwcommon.entity.dto.AdminInfoDTO;

/**
 * AdminInfo 实体类
*
 * @version
 * @author zengjq 2017年04月20日 下午20:33:35
 */ 
@Entity
@Table(name = "admin_info") 
public class AdminInfo extends AdminInfoDTO implements Serializable {

	/**
	 * Comments for <code>serialVersionUID</code>
	 * 【请在此输入描述文字】
	 */
	private static final long serialVersionUID = -5356738700572099339L;

	@Id
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "user_alias")
	private String userAlias;

	@Column(name = "user_sex")
	private Short userSex;

	@Column(name = "user_age")
	private String userAge;

	@Column(name = "user_mobile")
	private String userMobile;

	@Column(name = "user_email")
	private String userEmail;

	@Column(name = "user_login_time")
	private Integer userLoginTime;

	@Column(name = "user_login_ip")
	private String userLoginIp;

	@Column(name = "user_status")
	private Short userStatus;

	@Column(name = "ctime")
	private Integer ctime;

	@Column(name = "utime")
	private Integer utime;

	@Column(name = "is_delete")
	private Short isDelete;

	@Column(name = "rights")
	private String rights;
	
	@Column(name = "role_id")
	private Integer roleId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserAlias() {
		return userAlias;
	}

	public void setUserAlias(String userAlias) {
		this.userAlias = userAlias;
	}

	public Short getUserSex() {
		return userSex;
	}

	public void setUserSex(Short userSex) {
		this.userSex = userSex;
	}

	public String getUserAge() {
		return userAge;
	}

	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Integer getUserLoginTime() {
		return userLoginTime;
	}

	public void setUserLoginTime(Integer userLoginTime) {
		this.userLoginTime = userLoginTime;
	}

	public String getUserLoginIp() {
		return userLoginIp;
	}

	public void setUserLoginIp(String userLoginIp) {
		this.userLoginIp = userLoginIp;
	}

	public Short getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Short userStatus) {
		this.userStatus = userStatus;
	}

	public Integer getCtime() {
		return ctime;
	}

	public void setCtime(Integer ctime) {
		this.ctime = ctime;
	}

	public Integer getUtime() {
		return utime;
	}

	public void setUtime(Integer utime) {
		this.utime = utime;
	}

	public Short getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Short isDelete) {
		this.isDelete = isDelete;
	}

	public String getRights() {
		return rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

}