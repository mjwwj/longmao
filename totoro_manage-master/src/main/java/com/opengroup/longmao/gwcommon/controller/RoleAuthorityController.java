package com.opengroup.longmao.gwcommon.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.opengroup.longmao.gwcommon.configuration.annotation.JsonParam;
import com.opengroup.longmao.gwcommon.configuration.log.GwsLogger;
import com.opengroup.longmao.gwcommon.entity.po.RoleAuthority;
import com.opengroup.longmao.gwcommon.entity.po.RoleMenu;
import com.opengroup.longmao.gwcommon.service.RoleAuthorityService;
import com.opengroup.longmao.gwcommon.service.RoleMenuService;
import com.opengroup.longmao.gwcommon.tools.date.DateUtil;
import com.opengroup.longmao.gwcommon.tools.result.CommConstant;
import com.opengroup.longmao.gwcommon.tools.result.RetResult;
import com.opengroup.longmao.gwcommon.tools.role.RightsHelper;
import com.opengroup.longmao.gwcommon.tools.role.Tools;

@Controller
public class RoleAuthorityController {

	@Autowired
	public RoleAuthorityService roleService;
	
	@Autowired
	public RoleMenuService menuService;

	/**
	 * 
	 *用户无权访问页面
	 * 
	 * @author zengjq 2016年4月13日
	 * @return
	 */
//	@RequestMapping("/noRights")
//	public String noRights() {
//		GwsLogger.info("用户无权访问页面");
//		return "common/noRights";
//	}
	
	/**
	 * 
	 * 进入角色管理页面
	 * 
	 * @author zengjq 2016年4月13日
	 * @return
	 */
	@RequestMapping("/roleInfos")
	public String roleInfo(Model model) {
//		model.addAttribute("position", "roleInfos");
		GwsLogger.info("进入角色管理页面:position=role");
		return "role/authority";
	}
	
	/**
	 * 
	 * 显示角色列表
	 * 
	 * @author zengjq 2016年5月18日
	 * @return
	 */
	@RequestMapping(value = "/roleAuthority")
	public @ResponseBody RetResult roleList() {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		
		GwsLogger.info("进入权限管理页面");
		
		//查询所有角色
		List<RoleAuthority> roleList = null;
		try {
			roleList = roleService.listAllRoles();
			GwsLogger.info("查询所有角色:size={}",roleList.size());
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("查询角色信息异常:code={},message={},e={}",code,message,e);
		}
		GwsLogger.info("查询角色信息结束,code={},message={},size={}",code,message,roleList.size());
		return RetResult.setRetDate(code, message, roleList);
	}

	/**
	 * 
	 * 新增角色信息
	 * 
	 * @author zengjq 2016年5月18日
	 * @param roleAuthority
	 * @return
	 */
	@RequestMapping(value = "/saveRole", method = RequestMethod.POST)
	public @ResponseBody RetResult saveRole(@JsonParam RoleAuthority roleAuthority) {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		
		GwsLogger.info("新增角色信息开始:code={},message={},userInfo={}",code,message,ToStringBuilder.reflectionToString(roleAuthority));
	
		// 保存角色后并返回角色对象
		RoleAuthority newRoleAuthority = null;
		if(roleAuthority!=null){
			try {
				newRoleAuthority = roleService.saveRole(roleAuthority);
				if(newRoleAuthority==null){
					code = CommConstant.GWSCOD0006;
					message = CommConstant.GWSMSG0006;
					GwsLogger.info("角色已存在:code={},message={}",code,message);
				}
			} catch (Exception e) {
				code = CommConstant.GWSCOD0001;
				message = CommConstant.GWSMSG0001;
				GwsLogger.error("角色新增异常:code={},message={},e={}",code,message,e);
			}
		}else{
			code = CommConstant.GWSCOD0003;
			message =CommConstant.GWSMSG0003;
			GwsLogger.info("接收客户端参数失败:code={},message={}",code,message);
		}
		GwsLogger.info("新增角色成功结束:code={},message={},newUserInfo={}",code,message,ToStringBuilder.reflectionToString(newRoleAuthority));
		return RetResult.setRetDate(code, message, newRoleAuthority);
	}
	
	/**
	 * 
	 * 删除某个角色
	 * 
	 * @author zengjq 2016年5月20日
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value="/deleteRole")
	public @ResponseBody RetResult deleteRole(String roleId){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("删除角色开始:code={},message={},roleId={}",code,message,roleId);
		try {
			if(StringUtils.isNotBlank(roleId)){
				RoleAuthority roleAuthority  = roleService.deleteRoleById(Integer.valueOf(roleId));
				if(null==roleAuthority){
					code = CommConstant.GWSCOD0008;
					message = CommConstant.GWSMSG0008;
				   GwsLogger.info("该角色下有用户存在,禁止删除:code={},message={}",code,message);
				}
			}else{
				code = CommConstant.GWSCOD0003;
				message =CommConstant.GWSMSG0003;
				GwsLogger.info("roleId为空:code={},message={},roleId={}",code,message,roleId);
			}
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("删除角色异常:code={},message={},e={}",code,message,e);
		}
		GwsLogger.info("删除角色结束:code={},message={}",code,message);
		return RetResult.setRetDate(code, message,null);
	}
	
	/**
	 * 
	 * 进入角色修改页面，先查询角色信息返回到页面
	 * 
	 * @author zengjq 2016年4月13日
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/roleEdit")
	public  @ResponseBody RetResult roleEdit(String roleId) {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("角色信息修改开始:code={},message={},roleId={}",code,message,roleId);
		//初始化角色对象
		RoleAuthority roleAuthority =null;
		try {
			if (StringUtils.isNotBlank(roleId)) {
				//查询角色信息
				roleAuthority = roleService.getRoleById(Integer.valueOf(roleId));
				GwsLogger.info("通过角色id查询角色对象:userId={}",ToStringBuilder.reflectionToString(roleAuthority));
			}
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("角色id为空:code={},message={},e={}",code,message,e);
		}
		GwsLogger.info("角色信息修改结束:code={},message={},roleAuthority={}",code,message,ToStringBuilder.reflectionToString(roleAuthority));
		return RetResult.setRetDate(code, message, roleAuthority);
	}

	/**
	 * 
	 * 保存修改角色信息
	 * 
	 * @author zengjq 2016年5月18日
	 * @param roleAuthority
	 * @return
	 */
	@RequestMapping(value = "/updateRole", method = RequestMethod.POST)
	public @ResponseBody RetResult updateRole(@JsonParam RoleAuthority roleAuthority) {
		
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("修改角色信息开始:code={},message={},userInfo={}",code,message,ToStringBuilder.reflectionToString(roleAuthority));
		// 保存角色后并返回角色对象
		RoleAuthority newRoleAuthority = null;
		try {
			newRoleAuthority = roleService.updateRoleAuthority(roleAuthority);
			//如果为空,表示修改失败,说明对象已存在
			if(newRoleAuthority==null){
				code = CommConstant.GWSCOD0006;
				message = CommConstant.GWSMSG0006;
				GwsLogger.info("角色已存在:code={},message={}",code,message);
			}
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("角色信息修改异常:code={},message={},e={}",code,message,e);
		}
		GwsLogger.info("修改角色信息结束:code={},message={},newUserInfo={}",code,message,ToStringBuilder.reflectionToString(newRoleAuthority));
		return RetResult.setRetDate(code, message, newRoleAuthority);
	}
	
	/**
	 * 请求角色授权页面
	 * 
	 * @param roleId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/auth")
	public @ResponseBody RetResult auth(Integer roleId) {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("请求角色授权页面开始:code={},message={},roleId={}",code,message,roleId);
		Map<String,Object> map =null;
		String json ="";
		try{
			//查询所有菜单
			List<RoleMenu> menuList = menuService.listAllMenu();
			//根据角色id查询角色对象
			RoleAuthority role = roleService.getRoleById(roleId);
			//得到角色权限
			String roleRights = role.getRights();
			if (StringUtils.isNotEmpty(roleRights)) {
				for (RoleMenu menu : menuList) {
					//比较权限
					menu.setHasMenu(RightsHelper.testRights(roleRights,menu.getMenuId().intValue()));
					if (menu.isHasMenu()) {
						List<RoleMenu> subMenuList = menu.getSubMenu();
						for (RoleMenu sub : subMenuList) {
							sub.setHasMenu(RightsHelper.testRights(roleRights,sub.getMenuId().intValue()));
						}
					}
				}
			}
	//		JSONArray arr = JSONArray.fromObject(menuList);
	//		String json = arr.toString();
			json = JSON.toJSONString(menuList, true); 
			json = json.replaceAll("menuId", "id").replaceAll("menuName", "name").replaceAll("subMenu", "nodes").replaceAll("hasMenu", "checked");
	//		model.addAttribute("zTreeNodes", json);
	//		model.addAttribute("roleId", roleId);
			map = new HashMap<String,Object>();
			map.put("zTreeNodes", json);
			map.put("roleId", roleId);
		}catch(Exception e){
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("角色授权异常:code={},message={},e={}",code,message,e);
		}
//		return "authorization";
		GwsLogger.info("请求角色授权页面结束:code={},message={},json.length()={}",code,message,json.length());
		return RetResult.setRetDate(code, message, map);
	}

	/**
	 * 保存角色权限
	 * 
	 * @param roleId
	 * @param menuIds
	 * @param out
	 */
	@RequestMapping(value = "/saveAuth")
	public @ResponseBody RetResult saveAuth(Integer roleId,String menuIds) {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("保存角色权限开始:code={},message={},roleId={}",code,message,roleId);
		RoleAuthority ra = null;
		String rights =null;
		try{
			if(StringUtils.isNotBlank(menuIds)){
				rights = RightsHelper.sumRights(Tools.str2StrArray(menuIds)).toString();
			}
			RoleAuthority role = roleService.getRoleById(roleId);
			//插入角色权限
			role.setRights(rights);
			role.setUtime(DateUtil.currentSecond());
			ra = roleService.updateRoleRights(role);
		}catch(Exception e){
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("保存角色权限异常:code={},message={},e={}",code,message,e);
		}
		GwsLogger.info("保存角色权限结束:code={},message={},ra={}",code,message,ToStringBuilder.reflectionToString(ra));
		return RetResult.setRetDate(code, message, ra);
	}
}
