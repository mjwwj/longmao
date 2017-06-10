package com.opengroup.longmao.gwcommon.entity.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.opengroup.longmao.gwcommon.entity.dto.LiveH5DTO;

/**
 * 【直播间H5活动入口】 持久化对象
 *
 * @version
 * @author Hermit 2017年05月12日 下午16:09:19
 */ 
@Entity
@Table(name = "live_h5") 
public class LiveH5 extends LiveH5DTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "active_name")
	private String activeName;

	@Column(name = "active_link")
	private String activeLink;

	@Column(name = "active_ico")
	private String activeIco;

	@Column(name = "directions")
	private String directions;

	@Column(name = "soft_num")
	private Short softNum;

	@Column(name = "up_time")
	private Integer upTime;

	@Column(name = "down_time")
	private Integer downTime;

	@Column(name = "is_enable")
	private Short isEnable;

	@Column(name = "ctime")
	private Integer ctime;

	@Column(name = "utime")
	private Integer utime;

	@Column(name = "is_delete")
	private Short isDelete;


	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public String getActiveName(){
		return activeName;
	}

	public void setActiveName(String activeName){
		this.activeName = activeName;
	}

	public String getActiveLink(){
		return activeLink;
	}

	public void setActiveLink(String activeLink){
		this.activeLink = activeLink;
	}

	public String getActiveIco(){
		return activeIco;
	}

	public void setActiveIco(String activeIco){
		this.activeIco = activeIco;
	}

	public String getDirections(){
		return directions;
	}

	public void setDirections(String directions){
		this.directions = directions;
	}

	public Short getSoftNum(){
		return softNum;
	}

	public void setSoftNum(Short softNum){
		this.softNum = softNum;
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

	public Short getIsEnable(){
		return isEnable;
	}

	public void setIsEnable(Short isEnable){
		this.isEnable = isEnable;
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

