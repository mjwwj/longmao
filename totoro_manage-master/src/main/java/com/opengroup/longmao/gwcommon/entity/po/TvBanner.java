package com.opengroup.longmao.gwcommon.entity.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.opengroup.longmao.gwcommon.entity.dto.TvBannerDTO;

/**
 * 【banner配置表】 持久化对象
 *
 * @version
 * @author Hermit 2017年04月26日 上午11:11:17
 */ 
@Entity
@Table(name = "tv_banner") 
public class TvBanner extends TvBannerDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "banner_id")
	private Long bannerId;

	@Column(name = "banner_name")
	private String bannerName;

	@Column(name = "banner_pic_url")
	private String bannerPicUrl;

	@Column(name = "banner_url")
	private String bannerUrl;

	@Column(name = "up_time")
	private Integer upTime;

	@Column(name = "down_time")
	private Integer downTime;

	@Column(name = "sortNum")
	private Short sortNum;

	@Column(name = "banner_status")
	private Short bannerStatus;

	@Column(name = "ctime")
	private Integer ctime;

	@Column(name = "utime")
	private Integer utime;

	@Column(name = "is_delete")
	private Short isDelete;


	public Long getBannerId(){
		return bannerId;
	}

	public void setBannerId(Long bannerId){
		this.bannerId = bannerId;
	}

	public String getBannerName(){
		return bannerName;
	}

	public void setBannerName(String bannerName){
		this.bannerName = bannerName;
	}

	public String getBannerPicUrl(){
		return bannerPicUrl;
	}

	public void setBannerPicUrl(String bannerPicUrl){
		this.bannerPicUrl = bannerPicUrl;
	}

	public String getBannerUrl(){
		return bannerUrl;
	}

	public void setBannerUrl(String bannerUrl){
		this.bannerUrl = bannerUrl;
	}

	public Integer getUpTime(){
		return upTime;
	}

	public void setUpTime(Integer upTime){
		this.upTime = upTime;
	}

	public Integer getDownTime(){
		return downTime;
	}

	public void setDownTime(Integer downTime){
		this.downTime = downTime;
	}

	public Short getSortNum() {
		return sortNum;
	}

	public void setSortNum(Short sortNum) {
		this.sortNum = sortNum;
	}

	public Short getBannerStatus(){
		return bannerStatus;
	}

	public void setBannerStatus(Short bannerStatus){
		this.bannerStatus = bannerStatus;
	}

	public Integer getCtime(){
		return ctime;
	}

	public void setCtime(Integer ctime){
		this.ctime = ctime;
	}

	public Integer getUtime(){
		return utime;
	}

	public void setUtime(Integer utime){
		this.utime = utime;
	}

	public Short getIsDelete(){
		return isDelete;
	}

	public void setIsDelete(Short isDelete){
		this.isDelete = isDelete;
	}

}

