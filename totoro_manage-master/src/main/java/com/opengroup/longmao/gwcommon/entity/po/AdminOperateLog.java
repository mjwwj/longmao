package com.opengroup.longmao.gwcommon.entity.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AdminOperateLog 实体类
*
 * @version
 * @author zengjq 2017年04月20日 下午20:33:35
 */ 
@Entity
@Table(name = "user_operate_log") 
public class AdminOperateLog implements Serializable {

	/**
	 * Comments for <code>serialVersionUID</code>
	 * 【请在此输入描述文字】
	 */
	private static final long serialVersionUID = -7476239140091307331L;

	@Id
	@Column(name = "log_id")
	private Long logId;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "user_op_type")
	private Short userOpType;

	@Column(name = "user_op_content")
	private String userOpContent;

	@Column(name = "opt_date")
	private String optDate;

	@Column(name = "ctime")
	private Integer ctime;

	@Column(name = "utime")
	private Integer utime;

	@Column(name = "is_delete")
	private Short isDelete;


	public Long getLogId(){
		return logId;
	}

	public void setLogId(Long logId){
		this.logId=logId;
	}

	public Long getUserId(){
		return userId;
	}

	public void setUserId(Long userId){
		this.userId=userId;
	}

	public Short getUserOpType(){
		return userOpType;
	}

	public void setUserOpType(Short userOpType){
		this.userOpType=userOpType;
	}

	public String getUserOpContent(){
		return userOpContent;
	}

	public void setUserOpContent(String userOpContent){
		this.userOpContent=userOpContent;
	}

	public String getOptDate(){
		return optDate;
	}

	public void setOptDate(String optDate){
		this.optDate=optDate;
	}

	public Integer getCtime(){
		return ctime;
	}

	public void setCtime(Integer ctime){
		this.ctime=ctime;
	}

	public Integer getUtime(){
		return utime;
	}

	public void setUtime(Integer utime){
		this.utime=utime;
	}

	public Short getIsDelete(){
		return isDelete;
	}

	public void setIsDelete(Short isDelete){
		this.isDelete=isDelete;
	}

}

