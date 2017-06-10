package com.opengroup.longmao.gwcommon.entity.vo;

import java.io.Serializable;

/**
 * 【用户信息表】 VO对象
 *
 * @version
 * @author Hermit 2017年03月15日 上午09:59:49
 */
public class AnchorVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userId;
	private Short anchorType;
	private String grade;
	private String userType;
	private String alipayId;
	private String realName;
	private String idCard;
	private String mobile;
	private String ratio;
	private String remark;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public Short getAnchorType() {
		return anchorType;
	}

	public void setAnchorType(Short anchorType) {
		this.anchorType = anchorType;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getAlipayId() {
		return alipayId;
	}

	public void setAlipayId(String alipayId) {
		this.alipayId = alipayId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRatio() {
		return ratio;
	}

	public void setRatio(String ratio) {
		this.ratio = ratio;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
