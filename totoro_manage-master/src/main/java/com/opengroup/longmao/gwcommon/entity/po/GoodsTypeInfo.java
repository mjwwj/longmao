package com.opengroup.longmao.gwcommon.entity.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.opengroup.longmao.gwcommon.entity.dto.GoodsTypeInfoDTO;

/**
 * 【类型】 持久化对象
 *
 * @version
 * @author Hermit 2017年05月15日 下午16:34:49
 */ 
@Entity
@Table(name = "goods_type_info") 
public class GoodsTypeInfo extends GoodsTypeInfoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "goods_type_id")
	private Long goodsTypeId;

	@Column(name = "name")
	private String name;

	@Column(name = "is_enable")
	private Short isEnable;

	@Column(name = "ctime")
	private Integer ctime;

	@Column(name = "utime")
	private Integer utime;

	@Column(name = "is_delete")
	private Short isDelete;


	public Long getGoodsTypeId(){
		return goodsTypeId;
	}

	public void setGoodsTypeId(Long goodsTypeId){
		this.goodsTypeId = goodsTypeId;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
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

