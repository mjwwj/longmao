package com.opengroup.longmao.gwcommon.entity.vo;

import java.io.Serializable;

/**
 * 【用户等级规则】 持久化对象
 *
 * @version
 * @author Hermit 2017年04月17日 上午11:56:26
 */
public class UserGradeRuleVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String gradeVO;

	private String empirical;

	private String aliasVO;

	private String noteVO;

	private String timeC;
	
	private String timeU;

	public String getGradeVO() {
		return gradeVO;
	}

	public void setGradeVO(String gradeVO) {
		this.gradeVO = gradeVO;
	}
	
	public String getEmpirical() {
		return empirical;
	}

	public void setEmpirical(String empirical) {
		this.empirical = empirical;
	}

	public String getAliasVO() {
		return aliasVO;
	}

	public void setAliasVO(String aliasVO) {
		this.aliasVO = aliasVO;
	}

	public String getNoteVO() {
		return noteVO;
	}

	public void setNoteVO(String noteVO) {
		this.noteVO = noteVO;
	}

	public String getTimeC() {
		return timeC;
	}

	public void setTimeC(String timeC) {
		this.timeC = timeC;
	}
	
	public String getTimeU() {
		return timeU;
	}

	public void setTimeU(String timeU) {
		this.timeU = timeU;
	}
	
}
