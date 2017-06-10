package com.opengroup.longmao.gwcommon.entity.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.opengroup.longmao.gwcommon.entity.dto.TvFeedbackDTO;

/**
 * 【反馈意见表】 持久化对象
 *
 * @version
 * @author Yangst 2017年04月25日 上午11:12:07
 */ 
@Entity
@Table(name = "tv_feedback") 
public class TvFeedback extends TvFeedbackDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "feedback_id")
	private Long feedbackId;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "feedback_type")
	private Short feedbackType;

	@Column(name = "feedback_remark")
	private String feedbackRemark;
	
	@Column(name = "feedback_url")
	private String feedbackRrl;

	@Column(name = "contact_way")
	private String contactWay;

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
	
	@Column(name = "system_type")
	private String systemType;
	
	@Column(name = "system_version")
	private String systemVersion;
	
	@Column(name = "brand_type")
	private String brandType;
	
	@Column(name = "channel_num")
	private String channelNum;
	
	

	public String getSystemType() {
		return systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}

	public String getSystemVersion() {
		return systemVersion;
	}

	public void setSystemVersion(String systemVersion) {
		this.systemVersion = systemVersion;
	}

	public String getBrandType() {
		return brandType;
	}

	public void setBrandType(String brandType) {
		this.brandType = brandType;
	}

	public String getChannelNum() {
		return channelNum;
	}

	public void setChannelNum(String channelNum) {
		this.channelNum = channelNum;
	}

	public String getFeedbackRrl() {
		return feedbackRrl;
	}

	public void setFeedbackRrl(String feedbackRrl) {
		this.feedbackRrl = feedbackRrl;
	}

	public Long getFeedbackId(){
		return feedbackId;
	}

	public void setFeedbackId(Long feedbackId){
		this.feedbackId = feedbackId;
	}

	public Long getUserId(){
		return userId;
	}

	public void setUserId(Long userId){
		this.userId = userId;
	}

	public Short getFeedbackType(){
		return feedbackType;
	}

	public void setFeedbackType(Short feedbackType){
		this.feedbackType = feedbackType;
	}

	public String getFeedbackRemark(){
		return feedbackRemark;
	}

	public void setFeedbackRemark(String feedbackRemark){
		this.feedbackRemark = feedbackRemark;
	}

	public String getContactWay(){
		return contactWay;
	}

	public void setContactWay(String contactWay){
		this.contactWay = contactWay;
	}

	public Short getDealStatus(){
		return dealStatus;
	}

	public void setDealStatus(Short dealStatus){
		this.dealStatus = dealStatus;
	}

	public String getDealRemark(){
		return dealRemark;
	}

	public void setDealRemark(String dealRemark){
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

