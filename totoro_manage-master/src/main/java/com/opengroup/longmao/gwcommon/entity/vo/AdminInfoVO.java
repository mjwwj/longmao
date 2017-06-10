/**
 *Copyright (C) 2016  HangZhou YuShi Technology Co.Ltd  Holdings Ltd. All rights reserved
 *
 *本代码版权归杭州宇石科技所有，且受到相关的法律保护。
 *没有经过版权所有者的书面同意，任何其他个人或组织均不得以任何形式将本文件或本文件的部分代码用于其他商业用途。
 * 
 */
package com.opengroup.longmao.gwcommon.entity.vo;

import java.io.Serializable;

/**
 * UserInfoVo
 *
 * @version
 * @author zengjq 2016年04月14日 下午12:07:43
 */
public class AdminInfoVO implements Serializable {

	/**
	 * Comments for <code>serialVersionUID</code>
	 * 【请在此输入描述文字】
	 */
	private static final long serialVersionUID = 3688739249422851865L;
	private Long userId;
	private String userName;

	public Long getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
