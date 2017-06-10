package com.opengroup.longmao.gwcommon.entity.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.opengroup.longmao.gwcommon.entity.dto.TvIllegalReportDTO;

/**
 * 【举报情况表】 持久化对象
 *
 * @version
 * @author Hermit 2017年04月25日 上午11:08:03
 */ 
@Entity
@Table(name = "tv_illegal_report") 
public class TvIllegalReport extends TvIllegalReportDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "report_id")
	private Long reportId;

	@Column(name = "anchor_id")
	private Long anchorId;

	@Column(name = "room_id")
	private Long roomId;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "report_type")
	private Short reportType;

	@Column(name = "report_reason")
	private String reportReason;
	
	@Column(name = "deal_status")
	private Short dealStatus;

	@Column(name = "deal_remark")
	private String dealRemark;
	
	@Column(name = "deal_user")
	private Long dealUser;
	
	@Column(name = "deal_time")
	private Integer dealTime;

	@Column(name = "ctime")
	private Integer ctime;

	@Column(name = "utime")
	private Integer utime;

	@Column(name = "is_delete")
	private Short isDelete;


	public Long getReportId(){
		return reportId;
	}

	public void setReportId(Long reportId){
		this.reportId = reportId;
	}

	public Long getAnchorId(){
		return anchorId;
	}

	public void setAnchorId(Long anchorId){
		this.anchorId = anchorId;
	}

	public Long getRoomId(){
		return roomId;
	}

	public void setRoomId(Long roomId){
		this.roomId = roomId;
	}

	public Long getUserId(){
		return userId;
	}

	public void setUserId(Long userId){
		this.userId = userId;
	}

	public Short getReportType(){
		return reportType;
	}

	public void setReportType(Short reportType){
		this.reportType = reportType;
	}

	public String getReportReason(){
		return reportReason;
	}

	public void setReportReason(String reportReason){
		this.reportReason = reportReason;
	}

	
	public Short getDealStatus() {
		return dealStatus;
	}

	public void setDealStatus(Short dealStatus) {
		this.dealStatus = dealStatus;
	}

	public String getDealRemark() {
		return dealRemark;
	}

	public void setDealRemark(String dealRemark) {
		this.dealRemark = dealRemark;
	}

	public Long getDealUser() {
		return dealUser;
	}

	public void setDealUser(Long dealUser) {
		this.dealUser = dealUser;
	}

	public Integer getDealTime() {
		return dealTime;
	}

	public void setDealTime(Integer dealTime) {
		this.dealTime = dealTime;
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

