package com.opengroup.longmao.gwcommon.entity.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 【活动配置表】 持久化对象
 *
 * @version
 * @author Hermit 2017年04月26日 上午11:11:50
 */ 
@Entity
@Table(name = "tv_activity") 
public class TvActivity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "activity_id")
	private Long activityId;

	@Column(name = "activity_name")
	private String activityName;

	@Column(name = "activity_pic_url")
	private String activityPicUrl;

	@Column(name = "activity_url")
	private String activityUrl;

	@Column(name = "up_time")
	private Integer upTime;

	@Column(name = "down_time")
	private Integer downTime;

	@Column(name = "sortNum")
	private Short sortNum;

	@Column(name = "activity_status")
	private Short activityStatus;

	@Column(name = "ctime")
	private Integer ctime;

	@Column(name = "utime")
	private Integer utime;

	@Column(name = "is_delete")
	private Short isDelete;


	public Long getActivityId(){
		return activityId;
	}

	public void setActivityId(Long activityId){
		this.activityId = activityId;
	}

	public String getActivityName(){
		return activityName;
	}

	public void setActivityName(String activityName){
		this.activityName = activityName;
	}

	public String getActivityPicUrl(){
		return activityPicUrl;
	}

	public void setActivityPicUrl(String activityPicUrl){
		this.activityPicUrl = activityPicUrl;
	}

	public String getActivityUrl(){
		return activityUrl;
	}

	public void setActivityUrl(String activityUrl){
		this.activityUrl = activityUrl;
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

	public Short getActivityStatus(){
		return activityStatus;
	}

	public void setActivityStatus(Short activityStatus){
		this.activityStatus = activityStatus;
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

