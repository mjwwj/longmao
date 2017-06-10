package com.opengroup.longmao.gwcommon.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opengroup.longmao.gwcommon.configuration.annotation.JsonParam;
import com.opengroup.longmao.gwcommon.configuration.log.GwsLogger;
import com.opengroup.longmao.gwcommon.entity.po.AdminInfo;
import com.opengroup.longmao.gwcommon.entity.po.RoleAuthority;
import com.opengroup.longmao.gwcommon.entity.po.RoleMenu;
import com.opengroup.longmao.gwcommon.entity.vo.LoginVO;
import com.opengroup.longmao.gwcommon.enums.IsNormalEnum;
import com.opengroup.longmao.gwcommon.enums.IsOrNotEnum;
import com.opengroup.longmao.gwcommon.service.AdminInfoService;
import com.opengroup.longmao.gwcommon.service.RoleAuthorityService;
import com.opengroup.longmao.gwcommon.service.RoleMenuService;
import com.opengroup.longmao.gwcommon.tools.crypto.MD5Util;
import com.opengroup.longmao.gwcommon.tools.date.DateUtil;
import com.opengroup.longmao.gwcommon.tools.ip.GetIpAddr;
import com.opengroup.longmao.gwcommon.tools.result.CommConstant;
import com.opengroup.longmao.gwcommon.tools.result.RetResult;
import com.opengroup.longmao.gwcommon.tools.role.RightsHelper;
import com.opengroup.longmao.gwcommon.tools.role.SessionConstant;
/**
 * 
 * 登录Controller
 *
 * @version 
 * @author zengjq  2017年4月14日 上午11:05:11
 *
 */
@Controller
public class LoginController {

	@Autowired
	private AdminInfoService admininfoService;

	@Autowired
	private RoleMenuService menuService;
	
	@Autowired
	private RoleAuthorityService roleService;
	
	/**
	 * 
	 * 进入首页
	 * 
	 * @author zengjq 2017年4月15日
	 * @return
	 */
	@RequestMapping("/")
	public String page() {
		GwsLogger.info("进入首页");
		return "index";
	}
	
	/**
	 * 
	 * 进入首页
	 * 
	 * @author zengjq 2017年4月15日
	 * @return
	 */
	@RequestMapping("/index")
	public String index() {
		GwsLogger.info("进入首页");
		return "index";
	}
	
	/**
	 * 
	 * 进入登录页面
	 * 
	 * @author zengjq 2017年4月13日
	 * @return
	 */
	@RequestMapping("/login")
	public String login() {
		GwsLogger.info("管理员进入登录页面");
		return "login";
	}

	/**
	 * 
	 * 登录方法
	 * 
	 * @author zengjq 2017年4月13日
	 * @param session
	 * @param userName
	 * @param password
	 * @param vcode
	 * @return
	 */
	@RequestMapping(value = "/loginSubmit", method = RequestMethod.POST)
	public @ResponseBody RetResult loginSubmit(HttpSession session,@JsonParam LoginVO loginVO,HttpServletRequest request) {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("登录处理开始:code={},message={},loginVo={}",code,message,ToStringBuilder.reflectionToString(loginVO));
		AdminInfo adminInfo = null;
		// 得到code
		try {
//			String sessionCode = (String)  session.getAttribute(SessionConstant.SESSION_SECURITY_CODE);
//			if (StringUtils.isNoneBlank(sessionCode)&& sessionCode.equalsIgnoreCase(loginVo.getVcode())) {
				// 通过用户名查询用户对象
				adminInfo = admininfoService.getAdminInfoByName(loginVO.getUserName(),IsOrNotEnum.YES.getType(),IsNormalEnum.YES.getVal());
				// 判断用户对象是否存在
				if (adminInfo == null) {
					code = CommConstant.GWSCOD0010;
					message = CommConstant.GWSMSG0010;
					GwsLogger.info("用户不存在:code={},message={},adminInfo={}",code,message);
				} else {
					// 如果用户对象存在，再次检查密码是否匹配
					if (!adminInfo.getPassword().equals(MD5Util.MD5(loginVO.getPassword()))) {
						code = CommConstant.GWSCOD0011;
						message = CommConstant.GWSMSG0011;
						GwsLogger.error("用户密码错误:code={},message={},adminInfo.getPassword()={},MD5Util.MD5(loginVo.getPassword())={}",code,message,adminInfo.getPassword(),MD5Util.MD5(loginVO.getPassword()));
					} else {
						//得到用户ip
						String userLoginIp = GetIpAddr.getIpAddr(request);
						adminInfo.setUserLoginIp(userLoginIp);
						//登录时间
						adminInfo.setUserLoginTime(DateUtil.currentSecond());
						//修改用户登录信息
						AdminInfo newUserInfo = admininfoService.updateAdminInfo(adminInfo);
						if(newUserInfo==null){
							code = CommConstant.GWSCOD0001;
							message = CommConstant.GWSMSG0001;
							GwsLogger.error("更新用户登录信息失败:code={},message={}",code,message);
						}
						//保存用户信息到session
						session.setAttribute(SessionConstant.SESSION_USER,newUserInfo);
						GwsLogger.info("用户对象:code={},message={},adminInfo={}",code,message,ToStringBuilder.reflectionToString(newUserInfo));
					}
				}
//			} else {
//				code = CommConstant.GWSCOD0013;
//				message = CommConstant.GWSMSG0013;
//				GwsLogger.error("验证码错误:code={},message={}",code,message);
//			}
		} catch (Exception e) {
			e.printStackTrace();
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("登录异常:code={},message={},e={}",code,message,e);
		}
		GwsLogger.info("登录处理结束:code={},message={},adminInfo={}",code, message, ToStringBuilder.reflectionToString(adminInfo));
		return RetResult.setRetDate(code, message, adminInfo);
	}

	/**
	 * 
	 * 退出登录销毁session
	 * 
	 * @author zengjq 2017年4月13日
	 * @param session
	 * @return
	 * 
	 */
	@RequestMapping(value = "/loginOut", method = RequestMethod.POST)
	public @ResponseBody RetResult loginOut(HttpSession session) {
		//销毁用户信息
		session.removeAttribute(SessionConstant.SESSION_USER);
		//销毁角色权限
		session.removeAttribute(SessionConstant.SESSION_ROLE_RIGHTS);
		//销毁用户权限
		session.removeAttribute(SessionConstant.SESSION_USER_RIGHTS);
		GwsLogger.info("登录退出");
//		return "redirect:/login";
		return RetResult.setRetDate(CommConstant.GWSCOD0000, CommConstant.GWSMSG0000, null);
	}
	
	/**
	 * 访问系统菜单
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/leftRight", method = RequestMethod.POST)
	public @ResponseBody RetResult leftRight(HttpSession session) {

		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		
		//初始化权限菜单
		List<RoleMenu> menuList  = null;
		
		//得到当前用户信息
		AdminInfo adminInfo = (AdminInfo) session.getAttribute(SessionConstant.SESSION_USER);
//		adminInfo = admininfoService.getUserInfoById(adminInfo.getUserId());
		
		//得到用户角色
		RoleAuthority role = roleService.getRoleById(adminInfo.getRoleId());
		String roleRights = role != null ? role.getRights() : "";
		String userRights = adminInfo.getRights();
		
		// 避免每次拦截用户操作时查询数据库，以下将用户所属角色权限、用户权限限都存入session
		session.setAttribute(SessionConstant.SESSION_ROLE_RIGHTS, roleRights); // 将角色权限存入session
		session.setAttribute(SessionConstant.SESSION_USER_RIGHTS, userRights); // 将用户权限存入session

		//得到所有的菜单
		 menuList =  menuService.listAllMenu();
		 //判断用户权限和角色权限是否为空
		 if (StringUtils.isNotEmpty(userRights) || StringUtils.isNotEmpty(roleRights)) {
			 for (RoleMenu menu : menuList) {
				menu.setHasMenu(RightsHelper.testRights(userRights,menu.getMenuId().intValue())|| RightsHelper.testRights(roleRights, menu.getMenuId().intValue()));
				if (menu.isHasMenu()) {
					List<RoleMenu> subMenuList = menu.getSubMenu();
					for (RoleMenu sub : subMenuList) {
						sub.setHasMenu(RightsHelper.testRights(userRights,	sub.getMenuId().intValue())|| RightsHelper.testRights(roleRights,sub.getMenuId().intValue()));
					}
				}
			}
		}else{
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("用户角色或权限为空:code={},message={}",code,message);
		}
		GwsLogger.info("获取用户角色和权限:code={},message={},menuList.size={}",code,message,menuList.size());
		return RetResult.setRetDate(code, message, menuList);
	}	
}
