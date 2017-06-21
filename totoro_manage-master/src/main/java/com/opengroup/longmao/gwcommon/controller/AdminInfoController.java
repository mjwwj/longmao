package com.opengroup.longmao.gwcommon.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.opengroup.longmao.gwcommon.configuration.annotation.JsonParam;
import com.opengroup.longmao.gwcommon.configuration.log.GwsLogger;
import com.opengroup.longmao.gwcommon.entity.po.AdminInfo;
import com.opengroup.longmao.gwcommon.entity.po.RoleAuthority;
import com.opengroup.longmao.gwcommon.entity.po.RoleMenu;
import com.opengroup.longmao.gwcommon.entity.vo.AdminInfoVO;
import com.opengroup.longmao.gwcommon.service.AdminInfoService;
import com.opengroup.longmao.gwcommon.service.RoleAuthorityService;
import com.opengroup.longmao.gwcommon.service.RoleMenuService;
import com.opengroup.longmao.gwcommon.tools.ip.GetIpAddr;
import com.opengroup.longmao.gwcommon.tools.result.CommConstant;
import com.opengroup.longmao.gwcommon.tools.result.RetResult;
import com.opengroup.longmao.gwcommon.tools.role.RightsHelper;
import com.opengroup.longmao.gwcommon.tools.role.Tools;

/**
 * 
 * 
 * 用户管理Controller
 *
 * @version 
 * @author zengjq  2017年4月14日 上午11:05:36
 *
 */
@Controller
public class AdminInfoController {
	
	private String name;

	@Autowired
	private AdminInfoService adminInfoService;
	
	@Autowired
	private RoleAuthorityService roleService;
	
	@Autowired
	private RoleMenuService menuService;

	/**
	 * 
	 * 进入用户管理页面
	 * 
	 * @author zengjq 2017年4月13日
	 * @return
	 */
	@RequestMapping("/adminInfos")
	public String adminInfos() {
		GwsLogger.info("进入用户管理页面");
		return "admin/adminInfo";
	}

	/**
	 * 
	 * 查询所有未删除的用户
	 * 
	 * @author zengjq 2017年4月14日
	 *  @param adminInfo
	 * @return
	 */
	@RequestMapping(value = "/findAdminInfos")
	public @ResponseBody RetResult findAllAdminInfos(@JsonParam AdminInfoVO adminInfoVo,Integer pageNo,Integer pageSize) {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;

		GwsLogger.info("查询所有用户开始:code={},message={},adminInfoVo={},pageNo={},pageSize={}",code,message,ToStringBuilder.reflectionToString(adminInfoVo),pageNo,pageSize);
		//查询所有未删除的用户
		Page<AdminInfo> adminInfos = null;
		try {
			adminInfos = adminInfoService.findAllAdminInfos(adminInfoVo, pageNo-1,pageSize,CommConstant.SORT_FIELD_CTIME);
			GwsLogger.info("查询所有未删除的用户:size={}",adminInfos.getContent().size());
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("查询用户信息异常:code={},message={},e={}",code,message,e);
		}
		GwsLogger.info("查询所有用户结束,code={},message={},size={}",code,message,adminInfos.getContent().size());
		return RetResult.setRetDate(code, message, adminInfos);
	}

	/**
	 * 
	 * 进入用户设置修改页面，先查询用户信息返回到页面
	 * 
	 * @author zengjq 2017年4月13日
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/adminInfoSet")
	public  @ResponseBody RetResult adminInfoSet(String userId) {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("用户信息修改开始:code={},message={},userId={}",code,message,userId);
		//初始化用户对象
		AdminInfo adminInfo =null;
		List<RoleAuthority> roleList = null;
		try {
			if (StringUtils.isNotBlank(userId)) {
				//查询用户信息
				adminInfo = adminInfoService.getAdminInfoById(Long.valueOf(userId));
				GwsLogger.info("通过用户id查询用户对象:userId={}",ToStringBuilder.reflectionToString(adminInfo));
                //查询所有角色
				roleList = roleService.listAllRoles();
				GwsLogger.info("查询所有角色:roleList.size={}",roleList.size());
			}
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("用户id为空:code={},message={},e={}",code,message,e);
		}
		GwsLogger.info("用户信息修改结束:code={},message={},map.size={}",code,message,ToStringBuilder.reflectionToString(adminInfo));
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("adminInfo", adminInfo);
		map.put("roleList", roleList);
		return RetResult.setRetDate(code, message, map);
	}

	/**
	 * 请求用户授权页面
	 * 
	 * @param userId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/adminAuth")
	public @ResponseBody RetResult  adminAuth(Long userId) {
		
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("请求用户授权页面开始:code={},message={},userId={}",code,message,userId);
		Map<String,Object> map =null;
		String json ="";
		try{
			//查询所有菜单
			List<RoleMenu> menuList = menuService.listAllMenu();
			//根据用户id查询用户对象
			AdminInfo adminInfo = adminInfoService.getAdminInfoById(userId);
			//得到用户权限
			String adminRights = adminInfo.getRights();
			if (StringUtils.isNotEmpty(adminRights)) {
				for (RoleMenu menu : menuList) {
					menu.setHasMenu(RightsHelper.testRights(adminRights,	menu.getMenuId().intValue()));
					if (menu.isHasMenu()) {
						List<RoleMenu> subRightsList = menu.getSubMenu();
						for (RoleMenu sub : subRightsList) {
							sub.setHasMenu(RightsHelper.testRights(adminRights,sub.getMenuId().intValue()));
						}
					}
				}
			}
	//		JSONArray arr = JSONArray.fromObject(menuList);
	//		String json = arr.toString();
			json = JSON.toJSONString(menuList, true); 
			json = json.replaceAll("menuId", "id").replaceAll("menuName", "name").replaceAll("subMenu", "nodes").replaceAll("hasMenu", "checked");
		}catch(Exception e){
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("用户授权异常:code={},message={},e={}",code,message,e);
		}
		map = new HashMap<String,Object>();
		map.put("zTreeNodes", json);
		map.put("userId", userId);
//		return "authorization";
		GwsLogger.info("请求用户授权页面结束:code={},message={},json.length()={}",code,message,json.length());
		return RetResult.setRetDate(code, message, map);
	}
	
	/**
	 * 保存用户授权权限
	 * 
	 * @param roleId
	 * @param menuIds
	 * @param out
	 */
	@RequestMapping(value = "/saveAdminAuth")
	public @ResponseBody RetResult saveAdminAuth(Long userId,String menuIds) {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("保存用户授权权限开始:code={},message={},roleId={}",code,message,userId);
		AdminInfo adminInfo = null;
		String rights =null;
		try{
			if(StringUtils.isNotBlank(menuIds)){
				rights = RightsHelper.sumRights(Tools.str2StrArray(menuIds)).toString();
			}
			adminInfo = adminInfoService.saveAdminAuth(userId,rights);
		}catch(Exception e){
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("保存用户授权权限异常:code={},message={},e={}",code,message,e);
		}
		GwsLogger.info("保存用户授权权限结束:code={},message={}",code,message);
		return RetResult.setRetDate(code, message, adminInfo);
	}
	
	/**
	 * 
	 * 保存修改用户信息
	 * 
	 * @author zengjq 2017年4月13日
	 * @param adminInfo
	 * @return
	 */
	@RequestMapping(value = "/updateAdmin", method = RequestMethod.POST)
	public @ResponseBody RetResult updateAdminInfo(@JsonParam AdminInfo adminInfo) {
		
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("保存修改的用户信息开始:code={},message={},adminInfo={}",code,message,ToStringBuilder.reflectionToString(adminInfo));
		AdminInfo newAdminInfo =  null;
		try {
			if(null!=adminInfo){
				// 保存用户后并返回用户对象
				newAdminInfo = adminInfoService.updateAdminInfo(adminInfo);
			}else{
				code = CommConstant.GWSCOD0003;
				message = CommConstant.GWSMSG0003;
			}
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("用户信息修改异常:code={},message={},e={}",code,message,e);
		}
		GwsLogger.info("保存修改的用户信息结束:code={},message={},newAdminInfo={}",code,message,ToStringBuilder.reflectionToString(newAdminInfo));
		return RetResult.setRetDate(code, message, newAdminInfo);
	}
	
	/**
	 * 
	 * 进入新增用户页面
	 * 
	 * @author zengjq 2017年4月13日
	 * @return
	 */
//	@RequestMapping("/addAdminInfo")
//	public String addAdminInfo() {
//		GwsLogger.info("进入新增用户页面息");
//		return "admin/add";
//	}
	
	/**
	 * 
	 * 新增用户信息
	 * 
	 * @author zengjq 2017年4月13日
	 * @param adminInfo
	 * @return
	 */
	@RequestMapping(value = "/saveAdminInfo", method = RequestMethod.POST)
	public @ResponseBody RetResult saveAdminInfo(@JsonParam AdminInfo adminInfo,HttpServletRequest request) {
		
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("新增用户信息开始:code={},message={},adminInfo={}",code,message,ToStringBuilder.reflectionToString(adminInfo));
		// 保存用户后并返回用户对象
		AdminInfo newAdminInfo = null;
		try {
			if(adminInfo!=null){
				String userLoginIp = GetIpAddr.getIpAddr(request);
				adminInfo.setUserLoginIp(userLoginIp);
				GwsLogger.info("得到用户ip:userLoginIp={}",userLoginIp);
				newAdminInfo = adminInfoService.saveAdminInfo(adminInfo);
				if(newAdminInfo==null){
					code = CommConstant.GWSCOD0007;
					message = CommConstant.GWSMSG0007;
					GwsLogger.info("用户账号已存在:code={},message={}",code,message);
				}
			}else{
				code = CommConstant.GWSCOD0003;
				message =CommConstant.GWSMSG0003;
				GwsLogger.info("接收客户端参数失败:code={},message={}",code,message);
			}
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("用户新增异常:code={},message={},e={}",code,message,e);
		}
		GwsLogger.info("新增用户信息成功结束:code={},message={},newAdminInfo={}",code,message,ToStringBuilder.reflectionToString(newAdminInfo));
		return RetResult.setRetDate(code, message, newAdminInfo);
	}
	
	/**
	 * 
	 * 根据ID删除数据，页面reload
	 * 
	 * @author zengjq 2017年4月14日
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteAdminById")
	public @ResponseBody RetResult deleteAdminById(String userId) {
		
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("根据ID删除数据开始:code={},message={},userId={}",code,message,userId);
		try {
			if(StringUtils.isNotBlank(userId)){
				adminInfoService.deleteById(Long.valueOf(userId));
				GwsLogger.info("删除用户成功:userId={}",userId);
			}else{
				code = CommConstant.GWSCOD0001;
				message = CommConstant.GWSMSG0001;
				GwsLogger.info("用户信息删除失败,userId为空:code={},message={},userId={}",code,message,userId);
			}
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("用户信息删除异常:code={},message={},e={}",code,message,e);
		}
		GwsLogger.info("根据ID删除数据结束:code={},message={}",code,message);
		return RetResult.setRetDate(code, message,null);
	}
	
}
