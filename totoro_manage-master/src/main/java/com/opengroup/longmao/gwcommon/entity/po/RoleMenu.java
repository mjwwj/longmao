package com.opengroup.longmao.gwcommon.entity.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.opengroup.longmao.gwcommon.entity.dto.RoleMenuDTO;

/**
 * RoleMenu 实体类
*
 * @version
 * @author zengjq 2016年05月18日 下午15:10:22
 */ 
@Entity
@Table(name = "role_menu") 
public class RoleMenu extends RoleMenuDTO implements Serializable {

	/**
	 * Comments for <code>serialVersionUID</code>
	 * 【请在此输入描述文字】
	 */
	private static final long serialVersionUID = -9084944933328789764L;

	@Id
	@Column(name = "menu_id")
	private Integer menuId;

	@Column(name = "menu_name")
	private String menuName;

	@Column(name = "menu_url")
	private String menuUrl;

	@Column(name = "parent_id")
	private Integer parentId;
	
	@Column(name = "sort")
	private Integer sort;

	@Column(name = "is_delete")
	private Short isDelete;

	@Column(name = "ctime")
	private Integer ctime;

	@Column(name = "utime")
	private Integer utime;

	public String getMenuName(){
		return menuName;
	}

	public void setMenuName(String menuName){
		this.menuName=menuName;
	}

	public String getMenuUrl(){
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl){
		this.menuUrl=menuUrl;
	}

	public Short getIsDelete(){
		return isDelete;
	}

	public void setIsDelete(Short isDelete){
		this.isDelete=isDelete;
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

	public Integer getMenuId() {
		return menuId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}