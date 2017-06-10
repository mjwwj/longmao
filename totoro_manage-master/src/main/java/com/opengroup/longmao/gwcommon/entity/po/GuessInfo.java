package com.opengroup.longmao.gwcommon.entity.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.opengroup.longmao.gwcommon.entity.dto.GuessInfoDTO;

/**
 * 【竞猜开盘记录】 持久化对象
 *
 * @version
 * @author Hermit 2017年04月05日 下午18:51:25
 */ 
@Entity
@Table(name = "guess_info") 
public class GuessInfo extends GuessInfoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "guess_id")
	private Long guessId;

	@Column(name = "anchor_id")
	private Long anchorId;

	@Column(name = "is_custom")
	private Short isCustom;

	@Column(name = "sub_id")
	private Long subId;

	@Column(name = "guess_title")
	private String guessTitle;

	@Column(name = "guess_content")
	private String guessContent;

	@Column(name = "option_one")
	private String optionOne;

	@Column(name = "option_two")
	private String optionTwo;

	@Column(name = "option_three")
	private String optionThree;

	@Column(name = "option_four")
	private String optionFour;

	@Column(name = "dis_time")
	private Integer disTime;

	@Column(name = "stop_bet_time")
	private Integer stopBetTime;

	@Column(name = "is_rob")
	private Short isRob;
	
	@Column(name = "rob_status")
	private Short robStatus;
	
	@Column(name = "stop_rob_time")
	private Integer stopRobTime;
	
	@Column(name = "final_rob_user_id")
	private Long finalRobUserId;

	@Column(name = "pool_bean")
	private Integer poolBean;
	
	@Column(name = "rob_profit")
	private Integer robProfit;

	@Column(name = "status")
	private Short status;

	@Column(name = "final_result")
	private Short finalResult;

	@Column(name = "is_enable")
	private Short isEnable;

	@Column(name = "ctime")
	private Integer ctime;

	@Column(name = "utime")
	private Integer utime;

	@Column(name = "is_delete")
	private Short isDelete;

	public Long getGuessId() {
		return guessId;
	}

	public void setGuessId(Long guessId) {
		this.guessId = guessId;
	}

	public Long getAnchorId() {
		return anchorId;
	}

	public void setAnchorId(Long anchorId) {
		this.anchorId = anchorId;
	}

	public Short getIsCustom() {
		return isCustom;
	}

	public void setIsCustom(Short isCustom) {
		this.isCustom = isCustom;
	}

	public Long getSubId() {
		return subId;
	}

	public void setSubId(Long subId) {
		this.subId = subId;
	}

	public String getGuessTitle() {
		return guessTitle;
	}

	public void setGuessTitle(String guessTitle) {
		this.guessTitle = guessTitle;
	}

	public String getGuessContent() {
		return guessContent;
	}

	public void setGuessContent(String guessContent) {
		this.guessContent = guessContent;
	}

	public String getOptionOne() {
		return optionOne;
	}

	public void setOptionOne(String optionOne) {
		this.optionOne = optionOne;
	}

	public String getOptionTwo() {
		return optionTwo;
	}

	public void setOptionTwo(String optionTwo) {
		this.optionTwo = optionTwo;
	}

	public String getOptionThree() {
		return optionThree;
	}

	public void setOptionThree(String optionThree) {
		this.optionThree = optionThree;
	}

	public String getOptionFour() {
		return optionFour;
	}

	public void setOptionFour(String optionFour) {
		this.optionFour = optionFour;
	}

	public Integer getDisTime() {
		return disTime;
	}

	public void setDisTime(Integer disTime) {
		this.disTime = disTime;
	}

	public Integer getStopBetTime() {
		return stopBetTime;
	}

	public void setStopBetTime(Integer stopBetTime) {
		this.stopBetTime = stopBetTime;
	}

	public Short getIsRob() {
		return isRob;
	}

	public void setIsRob(Short isRob) {
		this.isRob = isRob;
	}

	public Short getRobStatus() {
		return robStatus;
	}

	public void setRobStatus(Short robStatus) {
		this.robStatus = robStatus;
	}

	public Integer getStopRobTime() {
		return stopRobTime;
	}

	public void setStopRobTime(Integer stopRobTime) {
		this.stopRobTime = stopRobTime;
	}

	public Long getFinalRobUserId() {
		return finalRobUserId;
	}

	public void setFinalRobUserId(Long finalRobUserId) {
		this.finalRobUserId = finalRobUserId;
	}

	public Integer getPoolBean() {
		return poolBean;
	}

	public void setPoolBean(Integer poolBean) {
		this.poolBean = poolBean;
	}

	public Integer getRobProfit() {
		return robProfit;
	}

	public void setRobProfit(Integer robProfit) {
		this.robProfit = robProfit;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Short getFinalResult() {
		return finalResult;
	}

	public void setFinalResult(Short finalResult) {
		this.finalResult = finalResult;
	}

	public Short getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Short isEnable) {
		this.isEnable = isEnable;
	}

	public Integer getCtime() {
		return ctime;
	}

	public void setCtime(Integer ctime) {
		this.ctime = ctime;
	}

	public Integer getUtime() {
		return utime;
	}

	public void setUtime(Integer utime) {
		this.utime = utime;
	}

	public Short getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Short isDelete) {
		this.isDelete = isDelete;
	}

}