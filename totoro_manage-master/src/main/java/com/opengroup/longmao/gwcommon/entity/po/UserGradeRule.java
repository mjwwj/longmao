package com.opengroup.longmao.gwcommon.entity.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.opengroup.longmao.gwcommon.entity.vo.UserGradeRuleVO;

/**
 * 【用户等级规则】 持久化对象
 *
 * @version
 * @author Hermit 2017年04月17日 上午11:56:26
 */ 
@Entity
@Table(name = "user_grade_rule") 
public class UserGradeRule extends UserGradeRuleVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "grade")
	private Short grade;

	@Column(name = "min_val")
	private Long minVal;

	@Column(name = "max_val")
	private Long maxVal;

	@Column(name = "alias")
	private String alias;

	@Column(name = "note")
	private String note;
	
	@Column(name = "is_max")
	private Short isMax;

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

	public Short getGrade(){
		return grade;
	}

	public void setGrade(Short grade){
		this.grade = grade;
	}

	public Long getMinVal(){
		return minVal;
	}

	public void setMinVal(Long minVal){
		this.minVal = minVal;
	}

	public Long getMaxVal(){
		return maxVal;
	}

	public void setMaxVal(Long maxVal){
		this.maxVal = maxVal;
	}

	public String getAlias(){
		return alias;
	}

	public void setAlias(String alias){
		this.alias = alias;
	}

	public String getNote(){
		return note;
	}

	public void setNote(String note){
		this.note = note;
	}
	
	public Short getIsMax() {
		return isMax;
	}

	public void setIsMax(Short isMax) {
		this.isMax = isMax;
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

