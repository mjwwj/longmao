package com.opengroup.longmao.gwcommon.entity.vo;

import java.io.Serializable;

/**
 * LoginVo
 *
 * @version
 * @author zengjq 2017年04月14日 下午12:07:43
 */
public class LoginVO implements Serializable {

	/**
	 * Comments for <code>serialVersionUID</code>
	 * 【请在此输入描述文字】
	 */
	private static final long serialVersionUID = -4014150453247225196L;
	private String userName;
	private String password;
	private String vcode;

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getVcode() {
		return vcode;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

}
