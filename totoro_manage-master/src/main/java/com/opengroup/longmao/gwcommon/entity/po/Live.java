package com.opengroup.longmao.gwcommon.entity.po;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 【主播直播信息】 持久化对象
 *
 * @version
 * @author Hermit 2017年05月19日 下午19:00:20
 */ 
@Entity
@Table(name = "li_live") 
public class Live implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "live_title")
	private String liveTitle;

	@Column(name = "live_cover")
	private String liveCover;

	@Column(name = "live_label")
	private Short liveLabel;

	@Column(name = "live_topic")
	private String liveTopic;

	@Column(name = "live_video_url")
	private String liveVideoUrl;

	@Column(name = "stats")
	private Integer stats;

	@Column(name = "lng")
	private Double lng;

	@Column(name = "lat")
	private Double lat;

	@Column(name = "city")
	private String city;

	@Column(name = "partic_num")
	private Integer particNum;

	@Column(name = "robot")
	private Integer robot;

	@Column(name = "tag")
	private String tag;

	@Column(name = "gmt_create")
	private Date gmtCreate;

	@Column(name = "gmt_create_user")
	private String gmtCreateUser;

	@Column(name = "gmt_modified")
	private Date gmtModified;

	@Column(name = "gmt_modified_user")
	private String gmtModifiedUser;

	@Column(name = "room_id")
	private String roomId;


	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getUserId(){
		return userId;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getLiveTitle(){
		return liveTitle;
	}

	public void setLiveTitle(String liveTitle){
		this.liveTitle = liveTitle;
	}

	public String getLiveCover(){
		return liveCover;
	}

	public void setLiveCover(String liveCover){
		this.liveCover = liveCover;
	}

	public Short getLiveLabel(){
		return liveLabel;
	}

	public void setLiveLabel(Short liveLabel){
		this.liveLabel = liveLabel;
	}

	public String getLiveTopic(){
		return liveTopic;
	}

	public void setLiveTopic(String liveTopic){
		this.liveTopic = liveTopic;
	}

	public String getLiveVideoUrl(){
		return liveVideoUrl;
	}

	public void setLiveVideoUrl(String liveVideoUrl){
		this.liveVideoUrl = liveVideoUrl;
	}

	public Integer getStats(){
		return stats;
	}

	public void setStats(Integer stats){
		this.stats = stats;
	}

	public Double getLng(){
		return lng;
	}

	public void setLng(Double lng){
		this.lng = lng;
	}

	public Double getLat(){
		return lat;
	}

	public void setLat(Double lat){
		this.lat = lat;
	}

	public String getCity(){
		return city;
	}

	public void setCity(String city){
		this.city = city;
	}

	public Integer getParticNum(){
		return particNum;
	}

	public void setParticNum(Integer particNum){
		this.particNum = particNum;
	}

	public Integer getRobot(){
		return robot;
	}

	public void setRobot(Integer robot){
		this.robot = robot;
	}

	public String getTag(){
		return tag;
	}

	public void setTag(String tag){
		this.tag = tag;
	}

	public Date getGmtCreate(){
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate){
		this.gmtCreate = gmtCreate;
	}

	public String getGmtCreateUser(){
		return gmtCreateUser;
	}

	public void setGmtCreateUser(String gmtCreateUser){
		this.gmtCreateUser = gmtCreateUser;
	}

	public Date getGmtModified(){
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified){
		this.gmtModified = gmtModified;
	}

	public String getGmtModifiedUser(){
		return gmtModifiedUser;
	}

	public void setGmtModifiedUser(String gmtModifiedUser){
		this.gmtModifiedUser = gmtModifiedUser;
	}

	public String getRoomId(){
		return roomId;
	}

	public void setRoomId(String roomId){
		this.roomId = roomId;
	}

}

