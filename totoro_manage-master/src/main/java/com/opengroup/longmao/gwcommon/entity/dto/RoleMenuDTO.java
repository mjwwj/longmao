package com.opengroup.longmao.gwcommon.entity.dto;

import java.io.Serializable;
import java.util.List;

import com.opengroup.longmao.gwcommon.entity.po.RoleMenu;

/**
 * RoleMenu 实体类
 *
 * @version
 * @author zengjq 2017年04月18日 下午15:10:22
 */
public class RoleMenuDTO implements Serializable {

	/**
	 * Comments for <code>serialVersionUID</code> 【请在此输入描述文字】
	 */
	private static final long serialVersionUID = -9084944933328789764L;

	private RoleMenuDTO parentMenu;
	private List<RoleMenu> subMenu;
	private boolean hasMenu = false;

	public RoleMenuDTO getParentMenu() {
		return parentMenu;
	}

	public List<RoleMenu> getSubMenu() {
		return subMenu;
	}

	public boolean isHasMenu() {
		return hasMenu;
	}

	public void setParentMenu(RoleMenuDTO parentMenu) {
		this.parentMenu = parentMenu;
	}

	public void setSubMenu(List<RoleMenu> subMenu) {
		this.subMenu = subMenu;
	}

	public void setHasMenu(boolean hasMenu) {
		this.hasMenu = hasMenu;
	}

}