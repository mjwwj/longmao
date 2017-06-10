package com.opengroup.longmao.gwcommon.entity.po;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.opengroup.longmao.gwcommon.entity.dto.GoodsCategoryInfoDTO;

/**
 * 【类别】 持久化对象
 *
 * @version
 * @author Hermit 2017年05月15日 下午16:35:03
 */ 
@Entity
@Table(name = "goods_category_info") 
public class GoodsCategoryInfo extends GoodsCategoryInfoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "goods_category_id")
	private Long goodsCategoryId;

	@Column(name = "goods_type_id")
	private Long goodsTypeId;

	@Column(name = "name")
	private String name;

	@Column(name = "price")
	private BigDecimal price;

	@Column(name = "is_enable")
	private Short isEnable;

	@Column(name = "ctime")
	private Integer ctime;

	@Column(name = "utime")
	private Integer utime;

	@Column(name = "is_delete")
	private Short isDelete;


	public Long getGoodsCategoryId(){
		return goodsCategoryId;
	}

	public void setGoodsCategoryId(Long goodsCategoryId){
		this.goodsCategoryId = goodsCategoryId;
	}

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

	public BigDecimal getPrice(){
		return price;
	}

	public void setPrice(BigDecimal price){
		this.price = price;
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

