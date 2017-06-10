package com.opengroup.longmao.gwcommon.entity.vo;

import java.io.Serializable;

/**
 * 【用户信息表】 VO对象
 *
 * @version
 * @author Hermit 2017年03月15日 上午09:59:49
 */ 
public class UserVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer attentionNum;//关注其他人

	private Integer attentionedNum;//其他人关注
	
	private String photoUrl;//头像地址

	public Integer getAttentionNum() {
		return attentionNum;
	}

	public void setAttentionNum(Integer attentionNum) {
		this.attentionNum = attentionNum;
	}

	public Integer getAttentionedNum() {
		return attentionedNum;
	}

	public void setAttentionedNum(Integer attentionedNum) {
		this.attentionedNum = attentionedNum;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
}

