package com.opengroup.longmao.gwcommon.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opengroup.longmao.gwcommon.configuration.annotation.JsonParam;
import com.opengroup.longmao.gwcommon.configuration.log.GwsLogger;
import com.opengroup.longmao.gwcommon.entity.po.RoleMenu;
import com.opengroup.longmao.gwcommon.service.RoleMenuService;
import com.opengroup.longmao.gwcommon.tools.result.CommConstant;
import com.opengroup.longmao.gwcommon.tools.result.RetResult;

@Controller
public class RoleMenuController {

	@Autowired
	private RoleMenuService menuService;

	/**
	 * 
	 * 进入菜单管理页面
	 * 
	 * @author zengjq 2016年4月13日
	 * @return
	 */
	@RequestMapping("/menuInfos")
	public String orderinfos(Model model) {
		model.addAttribute("position", "menuInfos");
		GwsLogger.info("进入菜单管理页面:position=menu");
		return "role/menu";
	}
	
	/**
	 * 显示菜单列表 和 请求新增菜单页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/prentMenu")
	public @ResponseBody RetResult menuList() {
	
	    GwsLogger.info("父菜单查询开始");
	    
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		
		//查询所有菜单
		List<RoleMenu> menuList = null;
		try {
			menuList = menuService.listAllParentMenu();
			GwsLogger.info("查询所有父菜单:size={}",menuList.size());
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("查询父菜单信息异常:code={},message={},e={}",code,message,e);
		}
		GwsLogger.info("父菜单查询结束,code={},message={},size={}",code,message,menuList.size());
		return RetResult.setRetDate(code, message, menuList);
	}

	/**
	 * 
	 * 新增菜单信息
	 * 
	 * @author zengjq 2016年6月8日
	 * @param roleMenu
	 * @return
	 */
	@RequestMapping(value = "/saveMenu")
	public @ResponseBody RetResult saveMenu(@JsonParam RoleMenu roleMenu) {
		
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("保存菜单信息开始:roleMenu={}",ToStringBuilder.reflectionToString(roleMenu));
		
		RoleMenu menu = null;
		try{
			menu = menuService.saveMenu(roleMenu);
			if(null==menu){
				code = CommConstant.GWSCOD0009;
				message = CommConstant.GWSMSG0009;
				GwsLogger.info("菜单已存在:code={},message={}",code,message);
			}
		}catch(Exception e){
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("保存菜单信息异常:code={},message={},e={}",code,message,e);
		}
		GwsLogger.info("保存菜单信息结束:code={},message={},roleMenu={}",code,message,ToStringBuilder.reflectionToString(roleMenu));
		return RetResult.setRetDate(code, message, menu);
	}
	
	/**
	 * 请求编辑菜单页面
	 * 
	 * @param menuId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editMenu")
	public @ResponseBody RetResult  editMenu( Integer menuId) {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		
		GwsLogger.info("请求编辑菜单页面开始:menuId={}",menuId);
		Map<String, Object> map = new HashMap<String, Object>();
		
		try{
			RoleMenu menu = menuService.getMenuById(menuId);
			map.put("menu", menu);
			if (menu.getParentId() != null && menu.getParentId()> 0) {
				List<RoleMenu> menuList = menuService.listAllParentMenu();
				map.put("menuList", menuList);
			}
		}catch(Exception e){
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("请求编辑菜单页面异常:code={},message={},e={}",code,message,e);
		}
		GwsLogger.info("请求编辑菜单页面结束,code={},message={},size={}",code,message,map.size());
		return RetResult.setRetDate(code, message, map);
	}

	/**
	 * 
	 * 菜单修改保存信息
	 * 
	 * @author zengjq 2016年6月8日
	 * @param roleMenu
	 * @return
	 */
	@RequestMapping(value = "/updateRoleMenu")
	public @ResponseBody RetResult updateRoleMenu(@JsonParam RoleMenu roleMenu) {
		
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("菜单修改保存信息开始:roleMenu={}",ToStringBuilder.reflectionToString(roleMenu));
		
		RoleMenu menu = null;
		try{
			menu = menuService.updateRoleMenu(roleMenu);
			if(null==menu){
				code = CommConstant.GWSCOD0009;
				message = CommConstant.GWSMSG0009;
				GwsLogger.info("菜单不存在:code={},message={}",code,message);
			}
		}catch(Exception e){
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("菜单修改保存信息异常:code={},message={},e={}",code,message,e);
		}
		GwsLogger.info("菜单修改保存信息结束:code={},message={},roleMenu={}",code,message,ToStringBuilder.reflectionToString(roleMenu));
		return RetResult.setRetDate(code, message, menu);
	}

	/**
	 * 获取当前菜单的所有子菜单
	 * 
	 * @param menuId
	 * @param response
	 */
	@RequestMapping(value = "/subList")
	public @ResponseBody RetResult subList(Integer menuId) {
		
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("获取当前菜单的所有子菜单开始:menuId={}",menuId);
		
		List<RoleMenu> subMenu = null;
		try {
			subMenu = menuService.listSubMenuByParentId(menuId);
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("获取当前菜单的所有子菜单异常:code={},message={},e={}",code,message,e);
		}
		GwsLogger.info("获取当前菜单的所有子菜单结束:code={},message={},subMenu.szie(0={}",code,message,subMenu.size());
		return RetResult.setRetDate(code, message, subMenu);
	}

	/**
	 * 删除菜单
	 * 
	 * @param menuId
	 * @param out
	 */
	@RequestMapping(value = "/delMenu")
	public @ResponseBody RetResult delMenu(Integer menuId) {
		
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("删除菜单:menuId={}",menuId);
		
		RoleMenu roleMenu = null;
		try {
			Integer i = menuService.deleteMenuById(menuId);
			if(i==null||i<=0){
				code = CommConstant.GWSCOD0001;
				message = CommConstant.GWSMSG0001;
				GwsLogger.info("删除菜单失败:code={},message={}",code,message);
			}
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("删除菜单异常:code={},message={},e={}",code,message,e);
		}
		GwsLogger.info("删除菜单结束:code={},message={}",code,message);
		return RetResult.setRetDate(code, message, roleMenu);
	}
	
	/**
	 * 
	 * 菜单排序
	 * 
	 * @author zengjq 2016年6月17日
	 * @param menuId
	 * @param soft
	 * @return
	 */
	@RequestMapping(value = "/menuSort", method = RequestMethod.POST)
	public @ResponseBody RetResult menuSort(Integer menuId,Integer sort) {
		
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("菜单排序开始:menuId={},sort={}",menuId,sort);
		
		RoleMenu menu = null;
		try{
			if(menuId==null||menuId<=0){
				code = CommConstant.GWSCOD0003;
				message = CommConstant.GWSMSG0003;
				GwsLogger.info("菜单id不存在:code={},message={}",code,message);
				return RetResult.setRetDate(code, message, null);
			}
			if(sort==null){
				code = CommConstant.GWSCOD0003;
				message = CommConstant.GWSMSG0003;
				GwsLogger.info("排序参数不存在:code={},message={}",code,message);
				return RetResult.setRetDate(code, message, null);
			}
			//设置排序
			menu = menuService.updateMenuSortById(menuId,sort);
		}catch(Exception e){
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("菜单排序修改异常:code={},message={},e={}",code,message,e);
		}
		GwsLogger.info("菜单排序结束:code={},message={},menuId={},sort={}",code,message,menuId,sort);
		return RetResult.setRetDate(code, message, menu);
	}
}
